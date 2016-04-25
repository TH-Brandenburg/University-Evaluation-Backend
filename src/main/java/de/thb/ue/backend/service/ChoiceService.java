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

import java.util.List;

import de.thb.ue.backend.model.Choice;
import de.thb.ue.backend.model.QuestionRevision;
import de.thb.ue.backend.repository.IChoice;
import de.thb.ue.backend.repository.IQuestionRevision;
import de.thb.ue.backend.service.interfaces.IChoiceService;

@Component
@Service
public class ChoiceService implements IChoiceService {

    @Autowired
    private IChoice choiceRepo;

    @Autowired
    private IQuestionRevision questionRevisionRepo;

    @Override
    public Choice get(String text, short grade) {
        return choiceRepo.findByTextGrade(text, grade).get(0);
    }

    @Override
    public List<Choice> getByRevisionName(String revisionName) {
        List<QuestionRevision> questionRevision = questionRevisionRepo.findByName(revisionName);
        if (questionRevision.size() == 1) {
            return questionRevision.get(0).getChoices();
        } else {
            return null;
        }
    }
}
