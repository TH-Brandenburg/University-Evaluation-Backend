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

import de.thb.ue.backend.model.*;
import de.thb.ue.backend.repository.*;
import de.thb.ue.backend.service.interfaces.IInitializationService;
import de.thb.ue.backend.util.DBInit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
@Service
public class InitializationService implements IInitializationService {

    @Autowired
    private IQuestionRevision questionRevisionRepo;

    @Autowired
    private IChoice choiceRepo;

    @Autowired
    private ITextQuestion questionRepo;

    @Autowired
    private ISCQuestion mcQuestionRepo;

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
        addQuestions(DBInit.getComputerScienceMCQuestionsV2(), DBInit.getComputerScienceQuestionsV2(), "Computer Science and Media v2.1 (2016-05-16)", DBInit.TEXT_QUESTIONS_FIRST_COMPUTER_SCIENCE_V2);
        addQuestions(DBInit.getBusinessAdministrationMCQuestions(), DBInit.getBusinessAdministrationQuestions(), "Business Administration", DBInit.TEXT_QUESTIONS_FIRST_BUSINESS_ADMINISTRATION);
        addQuestions(DBInit.getComputerScienceMCQuestions(), DBInit.getComputerScienceQuestions(), "Computer Science and Media", DBInit.TEXT_QUESTIONS_FIRST_COMPUTER_SCIENCE_V1);
        addQuestions(DBInit.getDemoMCQuestions(), DBInit.getDemoQuestions(), "Demo Evaluation", DBInit.TEXT_QUESTIONS_FIRST_DEMO);
    }

    private void addQuestions(List<SingleChoiceQuestion> SingleChoiceQuestions, List<TextQuestion> textQuestions, String revisionName, boolean textQuestionsFirst) {
        List<Choice> choicesForRevision = new ArrayList<>();
        List<SingleChoiceQuestion> singleChoiceQuestionsForRevision = new ArrayList<>();
        ArrayList<TextQuestion> questionsForRevision = new ArrayList<>();

        for (SingleChoiceQuestion singleChoiceQuestion : SingleChoiceQuestions) {
            List<Choice> actualQuestionChoiceList = new ArrayList<>();
            for (int i = 0; i < singleChoiceQuestion.getChoices().size(); i++) {
                Choice actualChoice = singleChoiceQuestion.getChoices().get(i);
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
            singleChoiceQuestion.setChoices(actualQuestionChoiceList);

            Question savedQuestion = mcQuestionRepo.findByText(singleChoiceQuestion.getText());
            if (savedQuestion != null && savedQuestion instanceof SingleChoiceQuestion) {
                singleChoiceQuestionsForRevision.add((SingleChoiceQuestion) savedQuestion);
            } else {
                singleChoiceQuestionsForRevision.add(mcQuestionRepo.save(singleChoiceQuestion));
            }
        }

        for(TextQuestion textQuestion : textQuestions){
            List<Question> savedTextQuestion = questionRepo.findByText(textQuestion.getText());
            if(savedTextQuestion.size() == 0){
                questionRepo.save(textQuestion);
            } else {
                questionsForRevision.add((TextQuestion) savedTextQuestion.get(0));
            }
        }
//        questionsForRevision = (List<Question>) questionRepo.save(questions);
        List<Question> questions = new ArrayList<>();
        questions.addAll(questionsForRevision);
        questions.addAll(singleChoiceQuestionsForRevision);

        questionRevisionRepo.save(new QuestionRevision(revisionName,
                questions, choicesForRevision,textQuestionsFirst, false));
    }
}
