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

import de.thb.ue.backend.model.Question;
import de.thb.ue.backend.util.QuestionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import de.thb.ue.dto.QuestionsDTO;
import de.thb.ue.backend.exception.DBEntryDoesNotExistException;
import de.thb.ue.backend.model.QuestionRevision;
import de.thb.ue.backend.repository.ISCQuestion;
import de.thb.ue.backend.repository.ITextQuestion;
import de.thb.ue.backend.repository.IQuestionRevision;
import de.thb.ue.backend.service.interfaces.IQuestionsService;
import de.thb.ue.backend.util.DTOMapper;

@Component
@Service
public class QuestionService implements IQuestionsService {

    @Autowired
    private IQuestionRevision questionRevisionRepo;

    @Autowired
    private ITextQuestion questionRepo;

    @Autowired
    private ISCQuestion mcQuestionRepo;

    @Override
    public QuestionsDTO getAllQuestionsAsDTO(String revisionName) throws DBEntryDoesNotExistException {
        List<QuestionRevision> questionRevisions = questionRevisionRepo.findByName(revisionName);
        QuestionsDTO out;
        if (questionRevisions != null && questionRevisions.size() == 1) {
            List<Question> textQuestions = new ArrayList<Question>();
            List<Question> scQuestions = new ArrayList<Question>();
            for (Question element:questionRevisions.get(0).getQuestions()){
                if (element.getType()==QuestionType.TextQuestion){
                    textQuestions.add(element);
                }
            }
            for (Question element:questionRevisions.get(0).getQuestions()){
                if (element.getType()==QuestionType.SingleChoiceQuestion){
                    scQuestions.add(element);
                }
            }
            out = DTOMapper.questionModelsToDTO(textQuestions, scQuestions);
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
    public List<Question> getMCQuestions() {
        return mcQuestionRepo.findAll();
    }

    @Override
    public List<Question> getQuestions(String revisionName) {
        List<QuestionRevision> questionRevision = questionRevisionRepo.findByName(revisionName);
        if (questionRevision.size() == 1) {
            List<Question> tQuestionList = new ArrayList<Question>();
            for(Question element : questionRevision.get(0).getQuestions()){
                if (element.getType() == QuestionType.TextQuestion){
                    tQuestionList.add(element);
                }
            }
            return questionRevision.get(0).getQuestions();
        } else {
            return null;
        }
    }

    @Override
    public List<Question> getMCQuestions(String revisionName) {
        List<QuestionRevision> questionRevision = questionRevisionRepo.findByName(revisionName);
        if (questionRevision.size() == 1) {
            List<Question> sCquestionList = new ArrayList<Question>();
             for(Question element : questionRevision.get(0).getQuestions()){
                   if (element.getType() == QuestionType.SingleChoiceQuestion){
                       sCquestionList.add(element);
                   }
            }
            return sCquestionList;
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
    public Question getMCQuestionById(int id) {
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
