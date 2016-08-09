

package de.thb.ue.backend.service.interfaces;

import java.util.List;

import de.thb.ue.backend.model.Question;
import de.thb.ue.backend.model.SingleChoiceQuestion;
import de.thb.ue.backend.model.TextQuestion;
import de.thb.ue.dto.QuestionsDTO;
import de.thb.ue.backend.exception.DBEntryDoesNotExistException;
import de.thb.ue.backend.model.QuestionRevision;

public interface IQuestionsService {

    QuestionsDTO getAllQuestionsAsDTO(String evaluationUid, int id) throws DBEntryDoesNotExistException;

    List<Question> getQuestions();

    List<Question> getMCQuestions();

    List<Question> getQuestions(String revisionName);

    List<Question> getMCQuestions(String revisionName);

    List<String> getRevisionNames();
    
    QuestionRevision getRevisionById(int id);
    
    QuestionRevision findByName(String name);
    
    void updateQuestionRevision(QuestionRevision questionaire);
    
    List<QuestionRevision> findAllQuestionRevisions();
    
    Question getMCQuestionById(int id);
    
    Question getQuestionById(int id);
    
    void deleteQuestionRevisionById(int id);
    
    QuestionRevision saveQuestionRevision(QuestionRevision questionRevision);
    
}
