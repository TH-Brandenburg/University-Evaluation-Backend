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
        ArrayList<MultipleChoiceQuestionDTO> out = new ArrayList<MultipleChoiceQuestionDTO>();
        for (SingleChoiceQuestion singleChoiceQuestion : singleChoiceQuestions) {
            MultipleChoiceQuestionDTO tmpQuestion = new MultipleChoiceQuestionDTO();
            tmpQuestion.setQuestion(singleChoiceQuestion.getText());
            tmpQuestion.setChoices(new ArrayList<ChoiceDTO>());
            for (Choice choice : singleChoiceQuestion.getChoices()) {
                tmpQuestion.getChoices().add(new ChoiceDTO(choice.getText(), choice.getGrade()));
            }

            out.add(tmpQuestion);
        }
        for (MultipleChoiceQuestionDTO element : out){
        	List<ChoiceDTO> choices = element.getChoices(); 
        	boolean containsNegativeGrade = false;
        	
            for (ChoiceDTO choice : choices) {
                if (choice.getGrade() < 0) {
                    containsNegativeGrade = true;
                    break;
                }
            }
            if (containsNegativeGrade){
            	ChoiceDTO noAnswerChoice = null;
            	
            	for (ChoiceDTO choice : choices) {
                    if (choice.getGrade() == 0) {
                       noAnswerChoice = choice;
                    }
                }
            	if(noAnswerChoice != null){
            		choices.remove(noAnswerChoice);
            	}
            	choices.sort((ChoiceDTO choice1, ChoiceDTO choice2) -> Short.compare(choice1.getGrade(), choice2.getGrade()) * -1);
            	if(noAnswerChoice != null){
            		choices.add(0, noAnswerChoice);
            	}
            	
        	} else {
            	choices.sort((ChoiceDTO choice1, ChoiceDTO choice2) -> Short.compare(choice1.getGrade(), choice2.getGrade()));
            }
        }
        return out;
    }

    public static QuestionsDTO questionModelsToDTO(List<TextQuestion> textQuestions, List<SingleChoiceQuestion> singleChoiceQuestions) {
        QuestionsDTO out = new QuestionsDTO();
        List<TextQuestionDTO> textQuestionDTOs = new ArrayList<TextQuestionDTO>();
        for (TextQuestion textQuestion : textQuestions) {
            textQuestionDTOs.add(new TextQuestionDTO(textQuestion.getId(), textQuestion.getText(), textQuestion.getOnlyNumbers(), textQuestion.getMaxLength()));
        }
        out.setTextQuestions(textQuestionDTOs);
        out.setMultipleChoiceQuestionDTOs(mcQuestionsToMultipleChoiceQuestionDTOs(singleChoiceQuestions));
        return out;
    }

}
