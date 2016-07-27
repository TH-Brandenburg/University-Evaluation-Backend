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

import de.thb.ue.backend.exception.AggregatedAnswerException;
import de.thb.ue.backend.exception.DBEntryDoesNotExistException;
import de.thb.ue.backend.model.*;
import de.thb.ue.backend.repository.IAggregatedSCAnswer;
import de.thb.ue.backend.repository.IQuestionRevision;
import de.thb.ue.backend.service.interfaces.IAggregatedMCAnswerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@Service
public class AggregatedMCAnswerService implements IAggregatedMCAnswerService {

    @Autowired
    private IAggregatedSCAnswer aggregatedMCAnswerRepo;

    @Autowired
    private IQuestionRevision questionRevisionRepo;

    /**
     * This Method aggregates a list of votes (singleChoiceAnswers). For aggregation the mean value is calculated for all
     * choices with grades grater then 0.
     *
     * @return a list with aggregatedMCAnswers
     */
    @Override
    public List<AggregatedSingleChoiceAnswer> aggregate(List<Vote> votes, String questionRevisionName) throws AggregatedAnswerException, DBEntryDoesNotExistException {
        List<QuestionRevision> questionRevisions = questionRevisionRepo.findByName(questionRevisionName);
        int mcQuestionCount;
        if (questionRevisions != null && !questionRevisions.isEmpty()) {
            int i = 0;
            for (Question question : questionRevisions.get(0).getQuestions()) {
                if (question instanceof SingleChoiceQuestion) {
                    i++;
                }
            }
            mcQuestionCount = i;
        } else {
            throw new DBEntryDoesNotExistException("There ist no db entry for: " + questionRevisionName);
        }
        if (votes != null && votes.size() > 0) {
            List<AggregatedSingleChoiceAnswer> aggregatedSingleChoiceAnswers = new ArrayList<>(mcQuestionCount);
            for (int i = 0; i < mcQuestionCount; i++) {
                AggregatedSingleChoiceAnswer aggregatedSingleChoiceAnswer = new AggregatedSingleChoiceAnswer();
                int summedGrades = 0;
                int voteCount = 0;
                for (int j = 0; j < votes.size(); j++) {
                    List<SingleChoiceAnswer> singleChoiceAnswers = votes.get(j).getSingleChoiceAnswers();
                    if (singleChoiceAnswers != null && singleChoiceAnswers.size() > 0) {
                        SingleChoiceAnswer singleChoiceAnswer = singleChoiceAnswers.get(i);
                        if (j == 0) {
                            aggregatedSingleChoiceAnswer.setQuestion(singleChoiceAnswer.getQuestion());
                        }
                        if (singleChoiceAnswer.getChoice() != null && singleChoiceAnswer.getChoice().getGrade() > 0) {
                            summedGrades += singleChoiceAnswer.getChoice().getGrade();
                            voteCount += 1;
                        }

                    } else {
                        log.error("Aggregation of textAnswers without textAnswers");
                    }
                }
                if (voteCount > 0) {
                    aggregatedSingleChoiceAnswer.setMeanGrade((double) summedGrades / voteCount);
                } else {
                    aggregatedSingleChoiceAnswer.setMeanGrade(0);
                }
                aggregatedSingleChoiceAnswers.add(aggregatedSingleChoiceAnswer);
            }
            aggregatedMCAnswerRepo.save(aggregatedSingleChoiceAnswers);
            return aggregatedSingleChoiceAnswers;
        } else {
            throw new AggregatedAnswerException("There are no votes/textAnswers to aggregate");
        }
    }
}
