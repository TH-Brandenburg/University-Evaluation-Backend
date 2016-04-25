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

package de.thb.ue.backend.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import de.fhb.ca.dto.AnswersDTO;
import de.fhb.ca.dto.MultipleChoiceQuestionDTO;
import de.fhb.ca.dto.QuestionsDTO;
import de.fhb.ca.dto.util.ChoiceDTO;
import de.fhb.ca.dto.util.TextQuestionDTO;
import de.thb.ue.backend.model.Choice;
import de.thb.ue.backend.model.MCQuestion;
import de.thb.ue.backend.model.Question;
import de.thb.ue.backend.model.StudyPath;

public class DTOMapper {

    public static List<String> innerSectionsToStrings(List<StudyPath> studyPaths) {
        return studyPaths.stream().map(StudyPath::getName).collect(Collectors.toList());
    }

    public static AnswersDTO stringToAnswersDTO(String answersDTO) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.readValue(answersDTO, AnswersDTO.class);
    }

    public static String answersDTOToString(AnswersDTO answersDTO) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.writeValueAsString(answersDTO);
    }

    public static List<MultipleChoiceQuestionDTO> mcQuestionsToMultipleChoiceQuestionDTOs(List<MCQuestion> mcQuestions) {
        ArrayList<MultipleChoiceQuestionDTO> out = new ArrayList<>();
        for (MCQuestion mcQuestion : mcQuestions) {
            MultipleChoiceQuestionDTO tmpQuestion = new MultipleChoiceQuestionDTO();
            tmpQuestion.setQuestion(mcQuestion.getText());
            tmpQuestion.setChoices(new ArrayList<>());
            for (Choice choice : mcQuestion.getChoices()) {
                tmpQuestion.getChoices().add(new ChoiceDTO(choice.getText(), choice.getGrade()));
            }

            out.add(tmpQuestion);
        }
        return out;
    }

    public static QuestionsDTO questionModelsToDTO(List<Question> questions, List<MCQuestion> mcQuestions) {
        QuestionsDTO out = new QuestionsDTO();
        List<TextQuestionDTO> textQuestionDTOs = new ArrayList<>(questions.size());
        int questionNumber = mcQuestions.size() + 1;
        for (Question question : questions) {
            textQuestionDTOs.add(new TextQuestionDTO(questionNumber, question.getText(), question.getOnlyNumbers(), question.getMaxLength()));
            questionNumber++;
        }
        out.setTextQuestions(textQuestionDTOs);
        out.setMultipleChoiceQuestionDTOs(mcQuestionsToMultipleChoiceQuestionDTOs(mcQuestions));
        return out;
    }

}
