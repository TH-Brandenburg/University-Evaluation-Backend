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

import de.thb.ue.backend.exception.DBEntryDoesNotExistException;
import de.thb.ue.backend.exception.EvaluationException;
import de.thb.ue.backend.exception.ValidationExeption;
import de.thb.ue.backend.model.*;
import de.thb.ue.backend.repository.*;
import de.thb.ue.backend.service.interfaces.IChoiceService;
import de.thb.ue.backend.service.interfaces.IVoteService;
import de.thb.ue.dto.AnswersDTO;
import de.thb.ue.dto.util.ChoiceDTO;
import de.thb.ue.dto.util.MultipleChoiceAnswerDTO;
import de.thb.ue.dto.util.TextAnswerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
@Service
public class VoteService implements IVoteService {

    @Autowired
    private IChoiceService choiceService;

    @Autowired
    private ITextQuestion questionRepo;

    @Autowired
    private ISCQuestion mcQuestionRepo;

    @Autowired
    private IVote voteRepo;

    @Autowired
    private IAnswer answerRepo;

    @Autowired
    private ISCAnswer mcAnswerRepo;

    @Autowired
    private StudyPathService studyPathService;

    @Autowired
    private EvaluationService evaluationService;


    @Override
    public Vote addAnswers(AnswersDTO answersDTO, String evaluationUID) throws EvaluationException, DBEntryDoesNotExistException, ValidationExeption {
        List<TextAnswer> textAnswers = new ArrayList<>();
        List<SingleChoiceAnswer> singleChoiceAnswers = new ArrayList<>();
        List<StudyPath> studyPaths = studyPathService.getStudyPathByEvaluationUID(evaluationUID);
        StudyPath studyPath = null;
        //Add textAnswers

        Evaluation evaluation = evaluationService.getByUID(evaluationUID);

        for (TextAnswerDTO answer : answersDTO.getTextAnswers()) {
            textAnswers.add(new TextAnswer((TextQuestion) questionRepo.findByText(answer.getQuestionText()).get(0), answer.getAnswerText()));
        }
        /*
        textAnswers.addAll(answersDTO.getTextAnswers().stream().map(answerDTO ->
                new TextAnswer(questionRepo.findByText(answerDTO.getQuestionText()).get(0),
                        answerDTO.getAnswerText())).collect(Collectors.toList()));
*/
        //Add study path
        for (StudyPath tempStudyPath : studyPaths) {
            if (tempStudyPath.getName().equals(answersDTO.getStudyPath())) {
                studyPath = tempStudyPath;
            }
        }
        if (studyPath == null) {
            throw new DBEntryDoesNotExistException("No study path with name: " + answersDTO.getStudyPath());
        }

        //Add singleChoiceAnswers
        for (MultipleChoiceAnswerDTO multipleChoiceAnswerDTO : answersDTO.getMcAnswers()) {
            ChoiceDTO choiceDTO = multipleChoiceAnswerDTO.getChoice();
            SingleChoiceAnswer singleChoiceAnswer;

            if (choiceDTO != null && multipleChoiceAnswerDTO.getQuestionText() != null && !multipleChoiceAnswerDTO.getQuestionText().isEmpty()) {
                singleChoiceAnswer = new SingleChoiceAnswer((SingleChoiceQuestion) mcQuestionRepo.findByTextAndQuestionRevision(multipleChoiceAnswerDTO.getQuestionText(), evaluation.getQuestionRevision().getId()), choiceService.get(choiceDTO.getChoiceText(), choiceDTO.getGrade()));
            } else {
                throw new ValidationExeption(ValidationExeption.OBJECT_INVALID,
                        "Given MultipleChoiceAnswerDTO was invalid.");
            }

            singleChoiceAnswers.add(singleChoiceAnswer);
            mcAnswerRepo.save(singleChoiceAnswer);
        }
        answerRepo.save(textAnswers);
        return voteRepo.save(new Vote(studyPath, textAnswers, singleChoiceAnswers));
    }


}
