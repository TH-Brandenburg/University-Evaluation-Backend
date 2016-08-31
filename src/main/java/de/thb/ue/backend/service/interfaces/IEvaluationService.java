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

package de.thb.ue.backend.service.interfaces;


import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import de.thb.ue.backend.exception.AggregatedAnswerException;
import de.thb.ue.backend.exception.DBEntryDoesNotExistException;
import de.thb.ue.backend.exception.EvaluationException;
import de.thb.ue.backend.exception.ParticipantException;
import de.thb.ue.backend.model.Evaluation;
import de.thb.ue.backend.model.Vote;
import de.thb.ue.backend.util.SemesterType;

public interface IEvaluationService {
    String add(int semester, int students, String tutor, int subject, SemesterType type,
               String revision) throws ParticipantException, EvaluationException;

    boolean isClosed(String evaluationUID) throws DBEntryDoesNotExistException;

    /**
     * @param evaluationUID from Evaluation
     * @return evaluation
     * @throws EvaluationException          if evaluation is closed (type 2)
     * @throws DBEntryDoesNotExistException if there is no evaluation with given uid
     */
    Evaluation getByUID(String evaluationUID) throws EvaluationException, DBEntryDoesNotExistException;

    List<BufferedImage> getVoteTokenQRCList(String evaluationUID);

    void save(Evaluation evaluation);

    void close(String evaluationUID) throws AggregatedAnswerException;

    String getQuestionRevisionDate(String evaluationUID) throws DBEntryDoesNotExistException;

    void addVote(Vote vote, String evaluationUID);

    File getQRCodeFile(String evaluationUID) throws EvaluationException, DBEntryDoesNotExistException;

    File getSummaryFile(String evaluationUID) throws EvaluationException, DBEntryDoesNotExistException;

    File getImageFile(String evaluationUID, int voteID) throws EvaluationException, DBEntryDoesNotExistException;

    Boolean imageExists(String evaluationUID, int voteID);

}
