/*
 * Copyright 2016 Max Gregor
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.thb.ue.backend.service;

import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import de.thb.ue.backend.exception.AggregatedAnswerException;
import de.thb.ue.backend.exception.DBEntryDoesNotExistException;
import de.thb.ue.backend.exception.EvaluationException;
import de.thb.ue.backend.exception.ParticipantException;
import de.thb.ue.backend.model.AggregatedSCAnswer;
import de.thb.ue.backend.model.Evaluation;
import de.thb.ue.backend.model.SingleChoiceQuestion;
import de.thb.ue.backend.model.Participant;
import de.thb.ue.backend.model.TextQuestion;
import de.thb.ue.backend.model.QuestionRevision;
import de.thb.ue.backend.model.Subject;
import de.thb.ue.backend.model.Tutor;
import de.thb.ue.backend.model.Vote;
import de.thb.ue.backend.repository.IEvaluation;
import de.thb.ue.backend.repository.IQuestionRevision;
import de.thb.ue.backend.service.interfaces.IAggregatedMCAnswerService;
import de.thb.ue.backend.service.interfaces.IEvaluationService;
import de.thb.ue.backend.service.interfaces.IParticipantService;
import de.thb.ue.backend.service.interfaces.ISubjectService;
import de.thb.ue.backend.service.interfaces.ITutorService;
import de.thb.ue.backend.util.EvaluationExcelFileGenerator;
import de.thb.ue.backend.util.QRCGeneration;
import de.thb.ue.backend.util.SemesterType;
import de.thb.ue.backend.util.ZipHelper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Service
public class EvaluationService implements IEvaluationService {

    @Value("${working-directory:}")
    private String workingDirectoryPath;

    @Value("${hostaddress:}")
    private String hostAddress;

    @Autowired
    private IParticipantService participantService;

    @Autowired
    private IAggregatedMCAnswerService aggregatedMCAnswerService;

    @Autowired
    private ITutorService tutorService;

    @Autowired
    private IEvaluation evaluationRepo;

    @Autowired
    private ISubjectService subjectService;

    @Autowired
    private IQuestionRevision questionRevisionRepo;

    @Override
    public String add(int semester, int students, String tutor, int subject, SemesterType type, String revisionName) throws ParticipantException, EvaluationException {

        Subject subjectToEvaluate = subjectService.getByID(subject);

        List<Tutor> tutorsForEvaluation = tutorService.getByFamilyName(tutor);
        if (tutorsForEvaluation == null || tutorsForEvaluation.isEmpty()) {
            log.error("Tutor was unknown!");
        }

        String uid = UUID.randomUUID().toString();

        QuestionRevision questionRevision = questionRevisionRepo.findByName(revisionName).get(0);
        Evaluation evaluation = evaluationRepo.save(new Evaluation(uid, LocalDateTime.now(), semester, tutorsForEvaluation, subjectToEvaluate, type, false,
                questionRevision, null, students, 0, null));
        participantService.add(students, evaluation);
        return uid;
    }

    @Override
    public void addVote(Vote vote, String evaluationUID) {
        Evaluation evaluation = evaluationRepo.findByUID(evaluationUID);
        List<Vote> votes = evaluation.getVotes();
        if (votes == null) {
            votes = new ArrayList<>(1);
        }
        votes.add(vote);
        evaluation.setStudentsVoted(evaluation.getStudentsVoted() + 1);
        evaluationRepo.save(evaluation);
    }

    @Override
    public List<BufferedImage> getVoteTokenQRCList(String evaluationUID) {
        List<BufferedImage> out = new ArrayList<>();
        Evaluation evaluation = evaluationRepo.findByUID(evaluationUID);
        for (Participant participant : participantService.getByEvaluation(evaluation)) {
            try {
                out.add(QRCGeneration.generateQRC("{\"host\":\"" + hostAddress + "\", \"token\":\"" + participant.getVoteToken() + " \"}",
                        QRCGeneration.SIZE_LARGE, ErrorCorrectionLevel.L, QRCGeneration.ENCODING_UTF_8));
            } catch (IOException | WriterException e) {
                log.error("Error while generating Token list for Evaluation: " + evaluationUID);
            }
        }
        return out;
    }

    @Override
    public Evaluation getByUID(String uid) throws EvaluationException, DBEntryDoesNotExistException {
        Evaluation evaluation = evaluationRepo.findByUID(uid);
        if (evaluation == null) {
            throw new DBEntryDoesNotExistException("Evaluation with given uid does not exist");
        } else if (evaluation.getClosed()) {
            throw new EvaluationException(EvaluationException.ALREADY_CLOSED);
        }
        return evaluation;
    }

    @Override
    public void save(Evaluation evaluation) {
        evaluationRepo.save(evaluation);
    }

    @Override
    public void close(String evaluationUID) throws AggregatedAnswerException {
        Evaluation evaluation = evaluationRepo.findByUID(evaluationUID);
        List<AggregatedSCAnswer> aggregatedSCAnswers;
        try {
            File workingDirectory = new File((workingDirectoryPath.isEmpty() ? "" : (workingDirectoryPath + File.separatorChar)) + evaluationUID);
            File qrCodesFile = new File(workingDirectory, "qrcodes.pdf");
            List<Vote> votes = evaluation.getVotes();
            if (votes != null && !votes.isEmpty()) {
                aggregatedSCAnswers = aggregatedMCAnswerService.aggregate(votes, evaluation.getQuestionRevision().getName());

                List<String> tutors = evaluation.getTutors().stream().map(tutor -> tutor.getName() + " " + tutor.getFamilyName()).collect(Collectors.toList());
                List<String> mcQuestions = evaluation.getQuestionRevision().getSingleChoiceQuestions().stream().map(SingleChoiceQuestion::getText).collect(Collectors.toList());
                List<String> textualQuestions = evaluation.getQuestionRevision().getQuestions().stream().map(TextQuestion::getText).collect(Collectors.toList());
                new EvaluationExcelFileGenerator(evaluationUID, aggregatedSCAnswers, tutors, mcQuestions,
                        textualQuestions, evaluation.getVotes(), evaluation.getSubject().getName(),
                        evaluation.getSemesterType(), evaluation.getDateOfEvaluation(), evaluation.getStudentsAll(),
                        evaluation.getStudentsVoted()).generateExcelFile();
                try {
                    File imageFolder = new File(workingDirectory, "images");
                    String[] images = imageFolder.list();
                    int offset = votes.get(0).getId() - 1;
                    if (images != null && images.length > 0) {
                        for (String image : images) {
                            int name = Integer.parseInt(image.substring(0, image.length() - 4));
                            String newName = name - offset + "";
                            File tmpFile = new File(imageFolder, image);
                            if (!tmpFile.isDirectory() && FilenameUtils.getExtension(image).equals("zip")) {
                                FileUtils.moveFile(tmpFile, new File(workingDirectory, newName + ".zip"));
                            }
                        }
                        if (imageFolder.exists()) {
                            imageFolder.delete();
                        }
                    }
                    ZipHelper.folderToZipFile(workingDirectory, new File(workingDirectory, "result.zip"));
                } catch (IOException e) {
                    log.error("Error while zipping results for: " + evaluationUID);
                }
                evaluation.setClosed(true);
                evaluationRepo.save(evaluation);
                participantService.deleteByEvaluation(evaluation);
            } else {
                participantService.deleteByEvaluation(evaluation);
                evaluationRepo.delete(evaluation);
            }
            if (qrCodesFile.exists()) {
                qrCodesFile.delete();
            }

        } catch (DBEntryDoesNotExistException e) {
            log.error("Evaluation not closed: " + e.getMessage());
        }

    }

    @Override
    public String getQuestionRevisionDate(String evaluationUID) throws DBEntryDoesNotExistException {
        Evaluation evaluation = evaluationRepo.findByUID(evaluationUID);
        String out;
        if (evaluation != null) {
            out = evaluation.getQuestionRevision().getName();
        } else {
            throw new DBEntryDoesNotExistException("Evaluation with id: " + evaluationUID + " not found.");
        }

        return out;
    }

    @Override
    public boolean isClosed(String evaluationUID) throws DBEntryDoesNotExistException {
        Evaluation evaluation = evaluationRepo.findByUID(evaluationUID);
        boolean out;
        if (evaluation == null) {
            throw new DBEntryDoesNotExistException("Can't find evaluation with given uid");
        } else {
            out = evaluation.getClosed();
        }
        return out;
    }

    @Override
    public File getQRCodeFile(String evaluationUID) throws EvaluationException, DBEntryDoesNotExistException {
        Evaluation evaluation = evaluationRepo.findByUID(evaluationUID);
        File out;
        File workingDirectory = new File((workingDirectoryPath.isEmpty() ? "" : (workingDirectoryPath + File.separatorChar)) + evaluationUID);

        if (evaluation != null && !evaluation.getClosed()) {
            File file = new File(workingDirectory, "qrcodes.pdf");
            if (file.exists() && file.canRead()) {
                out = file;
            } else {
                throw new EvaluationException(EvaluationException.READ_QRC_FILE, "PDF file does not exist or can't be read");
            }

        } else {
            throw new DBEntryDoesNotExistException("Evaluation with uid: " + evaluationUID + " does not exist");
        }
        return out;
    }

    @Override
    public File getSummaryFile(String evaluationUID) throws
            EvaluationException, DBEntryDoesNotExistException {
        Evaluation evaluation = evaluationRepo.findByUID(evaluationUID);
        File out;
        File workingDirectory = new File((workingDirectoryPath.isEmpty() ? "" : (workingDirectoryPath + File.separatorChar)) + evaluationUID);

        if (evaluation != null && evaluation.getClosed()) {
            File file = new File(workingDirectory, "result.zip");
            if (file.exists() && file.canRead()) {
                out = file;
            } else {
                throw new EvaluationException(EvaluationException.READ_RESULT_FILE, "Result file does not exist or can't be read ");
            }
        } else {
            throw new DBEntryDoesNotExistException("Evaluation with uid: " + evaluationUID + " does not exist");
        }
        return out;
    }
}
