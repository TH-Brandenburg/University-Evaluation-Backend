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

package de.thb.ue.backend.repository;

import de.thb.ue.backend.model.Choice;
import de.thb.ue.backend.model.SingleChoiceAnswer;
import de.thb.ue.backend.model.Vote;
import de.thb.ue.backend.util.AggregateEvaluationHelper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

import de.thb.ue.backend.model.Evaluation;

@RepositoryDefinition(domainClass = Evaluation.class, idClass = Integer.class)
@Transactional(readOnly = true)
public interface IEvaluation extends CrudRepository<Evaluation, Serializable> {

    List<Evaluation> findAll();

    @Query("SELECT e FROM Evaluation e WHERE e.uid = :uid")
    Evaluation findByUID(@Param(value = "uid") String uid);

    @Query("SELECT e FROM Evaluation e inner join e.questionRevision as question_revision WHERE question_revision.id = :qrid")
    List<Evaluation> findByQuestionRevisionId(@Param(value = "qrid") int qrid);

    @Query("SELECT e.votes FROM Evaluation e WHERE e.id = :id")
    List<Vote> findVotesForEvaluation(@Param(value = "id") int id);


    @Query("SELECT" +
            " new de.thb.ue.backend.util.AggregateEvaluationHelper(AVG(c.grade) as rating, COUNT(c.grade) as numberOfRatings)" +
            " FROM" +
            " de.thb.ue.backend.model.Choice c," +
            " de.thb.ue.backend.model.SingleChoiceAnswer sca," +
            " de.thb.ue.backend.model.Question q," +
            " Evaluation e" +
            " WHERE" +
            " q.id = :questionId and" +
            " e.id = :evalId and" +
            " sca.choice = c and" +
            " sca.question = q and" +
            " c.grade != 0")
    AggregateEvaluationHelper findAvgGradeByEvaluationIdAndQuestionId(@Param("evalId") int evalId,@Param("questionId") int questionId);

    @Query("SELECT" +
            " AVG(c.grade)" +
            " FROM" +
            " de.thb.ue.backend.model.Choice c," +
            " de.thb.ue.backend.model.SingleChoiceAnswer sca," +
            " de.thb.ue.backend.model.Question q," +
            " de.thb.ue.backend.model.Tutor t" +
            " INNER JOIN t.evaluations e" +
            " WHERE e IN (:evaluations) and" +
            " sca.choice = c and" +
            " sca.question = q and" +
            " c.grade != 0")
    double getAvgForTutor(@Param("evaluations") List<Evaluation> evaluations);

    @Query("SELECT " +
            " new de.thb.ue.backend.util.AggregateEvaluationHelper(AVG(c.grade) as rating, COUNT(c.grade) as numberOfRatings)" +
            " FROM" +
            " de.thb.ue.backend.model.Choice c," +
            " de.thb.ue.backend.model.SingleChoiceAnswer sca," +
            " de.thb.ue.backend.model.Vote v," +
            " Evaluation e" +
            " WHERE" +
            " sca.choice = c and" +
            " e = :evaluation and" +
            " c.grade != 0")
    AggregateEvaluationHelper getAvgForEvaluation(@Param("evaluation") Evaluation evaluation);

/*
    @Query("SELECT new de.thb.ue.backend.util.AggregateEvaluationHelper(AVG(grade)" +
            " as rating, COUNT(grade) as numberOfRatings)" +
            " FROM Evaluation e, Vote v WHERE e.id = :id ")
    AggregateEvaluationHelper findAvgGradeByEvaluationId(@Param("id") long id);
*/

}
