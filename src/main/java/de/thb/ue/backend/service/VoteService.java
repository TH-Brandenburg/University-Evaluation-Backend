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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import de.fhb.ca.dto.AnswersDTO;
import de.fhb.ca.dto.util.ChoiceDTO;
import de.fhb.ca.dto.util.MultipleChoiceAnswerDTO;
import de.thb.ue.backend.exception.DBEntryDoesNotExistException;
import de.thb.ue.backend.exception.EvaluationException;
import de.thb.ue.backend.exception.ValidationExeption;
import de.thb.ue.backend.model.Answer;
import de.thb.ue.backend.model.MCAnswer;
import de.thb.ue.backend.model.StudyPath;
import de.thb.ue.backend.model.Vote;
import de.thb.ue.backend.repository.IAnswer;
import de.thb.ue.backend.repository.IMCAnswer;
import de.thb.ue.backend.repository.IMCQuestion;
import de.thb.ue.backend.repository.IQuestion;
import de.thb.ue.backend.repository.IVote;
import de.thb.ue.backend.service.interfaces.IChoiceService;
import de.thb.ue.backend.service.interfaces.IVoteService;

@Component
@Service
public class VoteService implements IVoteService {

    @Autowired
    private IChoiceService choiceService;

    @Autowired
    private IQuestion questionRepo;

    @Autowired
    private IMCQuestion mcQuestionRepo;

    @Autowired
    private IVote voteRepo;

    @Autowired
    private IAnswer answerRepo;

    @Autowired
    private IMCAnswer mcAnswerRepo;

    @Autowired
    private StudyPathService studyPathService;


    @Override
    public Vote addAnswers(AnswersDTO answersDTO, String evaluationUID) throws EvaluationException, DBEntryDoesNotExistException, ValidationExeption {
        List<Answer> answers = new ArrayList<>();
        List<MCAnswer> mcAnswers = new ArrayList<>();
        List<StudyPath> studyPaths = studyPathService.getStudyPathByEvaluationUID(evaluationUID);
        StudyPath studyPath = null;
        //Add answers
        answers.addAll(answersDTO.getTextAnswers().stream().map(answerDTO ->
                new Answer(questionRepo.findByText(answerDTO.getQuestionText()).get(0),
                        answerDTO.getAnswerText())).collect(Collectors.toList()));

        //Add study path
        for (StudyPath tempStudyPath : studyPaths) {
            if (tempStudyPath.getName().equals(answersDTO.getStudyPath())) {
                studyPath = tempStudyPath;
            }
        }
        if (studyPath == null) {
            throw new DBEntryDoesNotExistException("No study path with name: " + answersDTO.getStudyPath());
        }

        //Add mcAnswers
        for (MultipleChoiceAnswerDTO multipleChoiceAnswerDTO : answersDTO.getMcAnswers()) {
            ChoiceDTO choiceDTO = multipleChoiceAnswerDTO.getChoice();
            MCAnswer mcAnswer;

            if (choiceDTO != null && multipleChoiceAnswerDTO.getQuestionText() != null && !multipleChoiceAnswerDTO.getQuestionText().isEmpty()) {
                mcAnswer = new MCAnswer(mcQuestionRepo.findByText(multipleChoiceAnswerDTO.getQuestionText()), choiceService.get(choiceDTO.getChoiceText(), choiceDTO.getGrade()));
            } else {
                throw new ValidationExeption(ValidationExeption.OBJECT_INVALID,
                        "Given MultipleChoiceAnswerDTO was invalid.");
            }

            mcAnswers.add(mcAnswer);
            mcAnswerRepo.save(mcAnswer);
        }
        answerRepo.save(answers);
        return voteRepo.save(new Vote(studyPath, answers, mcAnswers));
    }


}
