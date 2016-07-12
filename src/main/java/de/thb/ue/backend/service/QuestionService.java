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

import de.thb.ue.dto.QuestionsDTO;
import de.thb.ue.backend.exception.DBEntryDoesNotExistException;
import de.thb.ue.backend.model.MCQuestion;
import de.thb.ue.backend.model.Question;
import de.thb.ue.backend.model.QuestionRevision;
import de.thb.ue.backend.repository.IMCQuestion;
import de.thb.ue.backend.repository.IQuestion;
import de.thb.ue.backend.repository.IQuestionRevision;
import de.thb.ue.backend.service.interfaces.IQuestionsService;
import de.thb.ue.backend.util.DTOMapper;

@Component
@Service
public class QuestionService implements IQuestionsService {

    @Autowired
    private IQuestionRevision questionRevisionRepo;

    @Autowired
    private IQuestion questionRepo;

    @Autowired
    private IMCQuestion mcQuestionRepo;

    @Override
    public QuestionsDTO getAllQuestionsAsDTO(String revisionName) throws DBEntryDoesNotExistException {
        List<QuestionRevision> questionRevisions = questionRevisionRepo.findByName(revisionName);
        QuestionsDTO out;
        if (questionRevisions != null && questionRevisions.size() == 1) {
            out = DTOMapper.questionModelsToDTO(questionRevisions.get(0).getQuestions(), questionRevisions.get(0).getMcQuestions());
        } else {
            throw new DBEntryDoesNotExistException("Revision with this name: " + revisionName + " not found or more the one found.");
        }
        return out;
    }

    @Override
    public List<Question> getQuestions() {
        return questionRepo.findAll();
    }

    @Override
    public List<MCQuestion> getMCQuestions() {
        return mcQuestionRepo.findAll();
    }

    @Override
    public List<Question> getQuestions(String revisionName) {
        List<QuestionRevision> questionRevision = questionRevisionRepo.findByName(revisionName);
        if (questionRevision.size() == 1) {
            return questionRevision.get(0).getQuestions();
        } else {
            return null;
        }
    }

    @Override
    public List<MCQuestion> getMCQuestions(String revisionName) {
        List<QuestionRevision> questionRevision = questionRevisionRepo.findByName(revisionName);
        if (questionRevision.size() == 1) {
            return questionRevision.get(0).getMcQuestions();
        } else {
            return null;
        }
    }

    @Override
    public List<String> getRevisionNames() {
        List<String> out = new ArrayList<>();
        List<QuestionRevision> revisions = questionRevisionRepo.findAll();
        out.addAll(revisions.stream().map(QuestionRevision::getName).collect(Collectors.toList()));
        return out;
    }
    
    @Override
    public QuestionRevision getRevisionById(int id) {
    	return questionRevisionRepo.findOne(id);
    }
    
    @Override
    public void updateQuestionRevision(QuestionRevision questionnaire) {
    	questionRevisionRepo.save(questionnaire);
    }
    
    @Override
    public List<QuestionRevision> findAllQuestionRevisions() {
    	return questionRevisionRepo.findAll();
    }
    
    @Override
    public MCQuestion getMCQuestionById(int id) {
    	return mcQuestionRepo.findOne(id);
    }
    
    @Override
    public Question getQuestionById(int id) {
    	return questionRepo.findOne(id);
    }
    
    @Override
    public void deleteQuestionRevisionById(int id) {
    	questionRevisionRepo.delete(id);
    }
}
