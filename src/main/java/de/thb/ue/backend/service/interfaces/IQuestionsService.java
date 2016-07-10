

package de.thb.ue.backend.service.interfaces;

import java.util.List;

import de.thb.ue.dto.QuestionsDTO;
import de.thb.ue.backend.exception.DBEntryDoesNotExistException;
import de.thb.ue.backend.model.MCQuestion;
import de.thb.ue.backend.model.Question;
import de.thb.ue.backend.model.QuestionRevision;

public interface IQuestionsService {

    QuestionsDTO getAllQuestionsAsDTO(String revisionName) throws DBEntryDoesNotExistException;

    List<Question> getQuestions();

    List<MCQuestion> getMCQuestions();

    List<Question> getQuestions(String revisionName);

    List<MCQuestion> getMCQuestions(String revisionName);

    List<String> getRevisionNames();
    
    QuestionRevision getRevisionById(int id);
    
    void updateQuestionRevision(QuestionRevision questionaire);
    
    List<QuestionRevision> findAllQuestionRevisions();
    
    public MCQuestion getMCQuestionById(int id);
    
    public Question getQuestionById(int id);
}
