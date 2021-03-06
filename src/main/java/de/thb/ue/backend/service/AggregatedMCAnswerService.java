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

import de.thb.ue.backend.exception.AggregatedAnswerException;
import de.thb.ue.backend.exception.DBEntryDoesNotExistException;
import de.thb.ue.backend.model.AggregatedMCAnswer;
import de.thb.ue.backend.model.MCAnswer;
import de.thb.ue.backend.model.QuestionRevision;
import de.thb.ue.backend.model.Vote;
import de.thb.ue.backend.repository.IAggregatedMCAnswer;
import de.thb.ue.backend.repository.IQuestionRevision;
import de.thb.ue.backend.service.interfaces.IAggregatedMCAnswerService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Service
public class AggregatedMCAnswerService implements IAggregatedMCAnswerService {

    @Autowired
    private IAggregatedMCAnswer aggregatedMCAnswerRepo;

    @Autowired
    private IQuestionRevision questionRevisionRepo;

    /**
     * This Method aggregates a list of votes (mcAnswers). For aggregation the mean value is calculated for all
     * choices with grades grater then 0.
     *
     * @return a list with aggregatedMCAnswers
     */
    @Override
    public List<AggregatedMCAnswer> aggregate(List<Vote> votes, String questionRevisionName) throws AggregatedAnswerException, DBEntryDoesNotExistException {
        List<QuestionRevision> questionRevisions = questionRevisionRepo.findByName(questionRevisionName);
        int mcQuestionCount;
        if (questionRevisions != null && !questionRevisions.isEmpty()) {
            mcQuestionCount = questionRevisions.get(0).getMcQuestions().size();
        } else {
            throw new DBEntryDoesNotExistException("There ist no db entry for: " + questionRevisionName);
        }
        if (votes != null && votes.size() > 0) {
            List<AggregatedMCAnswer> aggregatedMCAnswers = new ArrayList<>(mcQuestionCount);
            for (int i = 0; i < mcQuestionCount; i++) {
                AggregatedMCAnswer aggregatedMCAnswer = new AggregatedMCAnswer();
                int summedGrades = 0;
                int voteCount = 0;
                for (int j = 0; j < votes.size(); j++) {
                    List<MCAnswer> mcAnswers = votes.get(j).getMcAnswers();
                    if (mcAnswers != null && mcAnswers.size() > 0) {
                        MCAnswer mcAnswer = mcAnswers.get(i);
                        if (j == 0) {
                            aggregatedMCAnswer.setQuestion(mcAnswer.getQuestion());
                        }
                        if (mcAnswer.getChoice() != null && mcAnswer.getChoice().getGrade() > 0) {
                            summedGrades += mcAnswer.getChoice().getGrade();
                            voteCount += 1;
                        }

                    } else {
                        log.error("Aggregation of answers without answers");
                    }
                }
                if (voteCount > 0) {
                    aggregatedMCAnswer.setMeanGrade((double) summedGrades / voteCount);
                } else {
                    aggregatedMCAnswer.setMeanGrade(0);
                }
                aggregatedMCAnswers.add(aggregatedMCAnswer);
            }
            aggregatedMCAnswerRepo.save(aggregatedMCAnswers);
            return aggregatedMCAnswers;
        } else {
            throw new AggregatedAnswerException("There are no votes/answers to aggregate");
        }
    }
}
