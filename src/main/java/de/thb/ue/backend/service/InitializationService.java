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

import de.thb.ue.backend.model.Choice;
import de.thb.ue.backend.model.MCQuestion;
import de.thb.ue.backend.model.Question;
import de.thb.ue.backend.model.QuestionRevision;
import de.thb.ue.backend.repository.IChoice;
import de.thb.ue.backend.repository.IMCQuestion;
import de.thb.ue.backend.repository.IQuestion;
import de.thb.ue.backend.repository.IQuestionRevision;
import de.thb.ue.backend.repository.IStudyPath;
import de.thb.ue.backend.repository.ISubject;
import de.thb.ue.backend.repository.ITutor;
import de.thb.ue.backend.service.interfaces.IInitializationService;
import de.thb.ue.backend.util.DBInit;

@Component
@Service
public class InitializationService implements IInitializationService {

    @Autowired
    private IQuestionRevision questionRevisionRepo;

    @Autowired
    private IChoice choiceRepo;

    @Autowired
    private IQuestion questionRepo;

    @Autowired
    private IMCQuestion mcQuestionRepo;

    @Autowired
    private ITutor tutorRepo;

    @Autowired
    private ISubject subjectRepo;

    @Autowired
    private IStudyPath studyPathRepo;

    public void deleteAll() {
        questionRepo.deleteAll();
        mcQuestionRepo.deleteAll();
        choiceRepo.deleteAll();
        questionRevisionRepo.deleteAll();
    }

    @Override
    public void createDBEntries() {
        tutorRepo.save(DBInit.getAllTutors());
        subjectRepo.save(DBInit.getAllSubjects());
        studyPathRepo.save(DBInit.getAllStudyPaths());
        addQuestions(DBInit.getBusinessAdministrationMCQuestions(), DBInit.getBusinessAdministrationQuestions(), "Business Administration", DBInit.TEXT_QUESTIONS_FIRST_BUSINESS_ADMINISTRATION);
        addQuestions(DBInit.getComputerScienceMCQuestions(), DBInit.getComputerScienceQuestions(), "Computer Science and Media", DBInit.TEXT_QUESTIONS_FIRST_COMPUTER_SCIENCE_V1);
        addQuestions(DBInit.getDemoMCQuestions(), DBInit.getDemoQuestions(), "Demo Evaluation", DBInit.TEXT_QUESTIONS_FIRST_DEMO);
        addQuestions(DBInit.getComputerScienceMCQuestionsV2(), DBInit.getComputerScienceQuestionsV2(), "Computer Science and Media v2.1 (2016-05-16)", DBInit.TEXT_QUESTIONS_FIRST_COMPUTER_SCIENCE_V2);
    }

    private void addQuestions(List<MCQuestion> MCQuestions, List<Question> questions, String revisionName, boolean textQuestionsFirst) {
        List<Choice> choicesForRevision = new ArrayList<>();
        List<MCQuestion> mcQuestionsForRevision = new ArrayList<>();
        ArrayList<Question> questionsForRevision = new ArrayList<>();

        for (MCQuestion mcQuestion : MCQuestions) {
            List<Choice> actualQuestionChoiceList = new ArrayList<>();
            for (int i = 0; i < mcQuestion.getChoices().size(); i++) {
                Choice actualChoice = mcQuestion.getChoices().get(i);
                List<Choice> tempChoices = choiceRepo.findByTextGrade(actualChoice.getText(), actualChoice.getGrade());
                Choice tempChoice;
                if (tempChoices == null || tempChoices.isEmpty()) {
                    tempChoice = choiceRepo.save(actualChoice);
                }else {
                    tempChoice = tempChoices.get(0);
                }
                actualQuestionChoiceList.add(tempChoice);
                choicesForRevision.add(tempChoice);
            }
            mcQuestion.setChoices(actualQuestionChoiceList);

            MCQuestion savedQuestion = mcQuestionRepo.findByText(mcQuestion.getText());
            if(savedQuestion != null){
                mcQuestionsForRevision.add(savedQuestion);
            } else {
                mcQuestionsForRevision.add(mcQuestionRepo.save(mcQuestion));
            }
        }

        for(Question question : questions){
            List<Question> savedQuestion = questionRepo.findByText(question.getText());
            if(savedQuestion.size() == 0){
                questionsForRevision.add(questionRepo.save(question));
            } else {
                questionsForRevision.add(savedQuestion.get(0));
            }
        }
//        questionsForRevision = (List<Question>) questionRepo.save(questions);
        questionRevisionRepo.save(new QuestionRevision(revisionName,
                questionsForRevision, mcQuestionsForRevision, choicesForRevision, textQuestionsFirst));
    }
}
