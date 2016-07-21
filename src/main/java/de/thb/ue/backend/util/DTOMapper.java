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
import de.thb.ue.backend.model.*;
import de.thb.ue.dto.AnswersDTO;
import de.thb.ue.dto.MultipleChoiceQuestionDTO;
import de.thb.ue.dto.QuestionsDTO;
import de.thb.ue.dto.util.ChoiceDTO;
import de.thb.ue.dto.util.TextQuestionDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public static List<MultipleChoiceQuestionDTO> mcQuestionsToMultipleChoiceQuestionDTOs(List<SingleChoiceQuestion> singleChoiceQuestions) {
        ArrayList<MultipleChoiceQuestionDTO> out = new ArrayList<>();
        for (SingleChoiceQuestion singleChoiceQuestion : singleChoiceQuestions) {
            MultipleChoiceQuestionDTO tmpQuestion = new MultipleChoiceQuestionDTO();
            tmpQuestion.setQuestion(singleChoiceQuestion.getText());
            tmpQuestion.setChoices(new ArrayList<>());
            for (Choice choice : singleChoiceQuestion.getChoices()) {
                tmpQuestion.getChoices().add(new ChoiceDTO(choice.getText(), choice.getGrade()));
            }

            out.add(tmpQuestion);
        }
        return out;
    }

    public static QuestionsDTO questionModelsToDTO(List<Question> textQuestions, List<SingleChoiceQuestion> singleChoiceQuestions) {
        QuestionsDTO out = new QuestionsDTO();
        List<TextQuestionDTO> textQuestionDTOs = new ArrayList<>(textQuestions.size());
        int questionNumber = singleChoiceQuestions.size() + 1;
        for (Question question : textQuestions) {
            if (question instanceof TextQuestion) {
                TextQuestion textQuestion = (TextQuestion) question;
                textQuestionDTOs.add(new TextQuestionDTO(questionNumber, textQuestion.getText(), textQuestion.getOnlyNumbers(), textQuestion.getMaxLength()));
                questionNumber++;
            }
        }
        out.setTextQuestions(textQuestionDTOs);
        out.setMultipleChoiceQuestionDTOs(mcQuestionsToMultipleChoiceQuestionDTOs(singleChoiceQuestions));
        return out;
    }

}
