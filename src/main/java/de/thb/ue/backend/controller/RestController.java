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

package de.thb.ue.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import de.thb.ue.dto.AnswersDTO;
import de.thb.ue.dto.QuestionsDTO;
import de.thb.ue.dto.RequestDTO;
import de.thb.ue.dto.ResponseDTO;
import de.thb.ue.dto.util.ErrorType;
import de.thb.ue.backend.exception.DBEntryDoesNotExistException;
import de.thb.ue.backend.exception.EvaluationException;
import de.thb.ue.backend.exception.ParticipantException;
import de.thb.ue.backend.exception.ValidationExeption;
import de.thb.ue.backend.model.Evaluation;
import de.thb.ue.backend.model.Subject;
import de.thb.ue.backend.model.Vote;
import de.thb.ue.backend.service.interfaces.IAnswerImageService;
import de.thb.ue.backend.service.interfaces.IEvaluationService;
import de.thb.ue.backend.service.interfaces.IParticipantService;
import de.thb.ue.backend.service.interfaces.IQuestionsService;
import de.thb.ue.backend.service.interfaces.IStudyPathService;
import de.thb.ue.backend.service.interfaces.IVoteService;
import de.thb.ue.backend.util.DTOMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@org.springframework.web.bind.annotation.RestController
public class RestController {

    public static final String API_VERSION = "/v1";

//    @Autowired
//    private IInitializationService initializationService;

    @Autowired
    private IParticipantService participantService;

    @Autowired
    private IQuestionsService questionsService;

    @Autowired
    private IStudyPathService studyPathService;

    @Autowired
    private IEvaluationService evaluationService;

    @Autowired
    private IVoteService voteService;

    @Autowired
    private IAnswerImageService answerImageService;


//    @RequestMapping(value = API_VERSION + "/init", method = RequestMethod.GET)
//    @ResponseBody
//    ResponseEntity<String> initDB() {
//        initializationService.createDBEntries();
//        log.error("This method should not be used");
//        return new ResponseEntity<>("DB Initialized", HttpStatus.OK);
//    }
//
//    @RequestMapping(value = API_VERSION + "/42", method = RequestMethod.GET)
//    @ResponseBody
//    ResponseEntity<String> deleteAll() {
//        initializationService.deleteAll();
//        return new ResponseEntity<>("Deletion is the answer!", HttpStatus.OK);
//    }

    @RequestMapping(value = API_VERSION + "/questions", method = RequestMethod.POST)
    @ResponseBody
    ResponseEntity<?> getQuestions(@RequestBody RequestDTO requestDTO) throws DBEntryDoesNotExistException, EvaluationException, ParticipantException {

        participantService.setDeviceID(requestDTO.getVoteToken(), requestDTO.getDeviceID());
        Evaluation evaluation = participantService.getEvaluation(requestDTO.getVoteToken());

        if (!evaluation.getClosed() && !participantService.hasVoted(requestDTO.getVoteToken())) {
            QuestionsDTO out = questionsService.getAllQuestionsAsDTO(evaluation.getQuestionRevision().getName());
            Subject subject = evaluation.getSubject();
            out.setTextQuestionsFirst(evaluation.getQuestionRevision().getTextQuestionsFirst());
            out.setStudyPaths(studyPathService.getByDegreeAndSection(subject.getDegree(), subject.getDepartment()));
            return new ResponseEntity<>(out, HttpStatus.OK);
        } else {
            throw new EvaluationException(EvaluationException.ALREADY_CLOSED, "Evaluation already closed or the participant already voted");
        }
    }

    @RequestMapping(value = API_VERSION + "/answers", method = RequestMethod.POST)
    @ResponseBody
    ResponseEntity<ResponseDTO> addAnswers(@RequestParam("answers-dto") String answerDTOString, @RequestParam("images") MultipartFile images) throws DBEntryDoesNotExistException, EvaluationException, ParticipantException, IOException, ValidationExeption {
        AnswersDTO answersDTO = DTOMapper.stringToAnswersDTO(answerDTOString);
        participantService.setVoted(answersDTO.getVoteToken(), answersDTO.getDeviceID());
        Evaluation evaluation = participantService.getEvaluation(answersDTO.getVoteToken());
        Vote vote = voteService.addAnswers(answersDTO, evaluation.getUid());
        evaluationService.addVote(vote, evaluation.getUid());
        if(!images.isEmpty()){
            answerImageService.addAnswerImage(vote, images, evaluation.getUid());
        }
        return new ResponseEntity<>(new ResponseDTO("Answers successful added", ErrorType.ANSWERS_SUCCESSFULLY_ADDED), HttpStatus.OK);
    }
/*
    @RequestMapping(value = API_VERSION + "/textAnswers", method = RequestMethod.POST)
    @ResponseBody
    ResponseEntity<ResponseDTO> addTextAnswers(@RequestParam("textAnswers-dto") String answerDTOString, @RequestParam("images") MultipartFile images) throws DBEntryDoesNotExistException, EvaluationException, ParticipantException, IOException, ValidationExeption {
        AnswersDTO answersDTO = DTOMapper.stringToAnswersDTO(answerDTOString);
        participantService.setVoted(answersDTO.getVoteToken(), answersDTO.getDeviceID());
        Evaluation evaluation = participantService.getEvaluation(answersDTO.getVoteToken());
        Vote vote = voteService.addAnswers(answersDTO, evaluation.getUid());
        evaluationService.addVote(vote, evaluation.getUid());
        if(!images.isEmpty()){
            answerImageService.addAnswerImage(vote, images, evaluation.getUid());
        }
        return new ResponseEntity<>(new ResponseDTO("Answers successful added", ErrorType.ANSWERS_SUCCESSFULLY_ADDED), HttpStatus.OK);
    }*/

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO> handleError(HttpServletRequest req, Exception exception) {
        log.error("Request: " + req.getRequestURL() + " raised " + exception);
        exception.printStackTrace();
        ResponseEntity<ResponseDTO> out;
        if (exception instanceof DBEntryDoesNotExistException || exception instanceof EvaluationException) {
            out = new ResponseEntity<>(new ResponseDTO("Invalid vote token", ErrorType.INVALID_TOKEN), HttpStatus.BAD_REQUEST);
        } else if (exception instanceof ParticipantException) {
            out = new ResponseEntity<>(new ResponseDTO("Already voted (ParticipantException "
                    + ((ParticipantException) exception).getType() + " )", ErrorType.TOKEN_ALLREADY_USED), HttpStatus.BAD_REQUEST);
        } else if (exception instanceof HttpMessageNotReadableException || exception instanceof ValidationExeption) {
            out = new ResponseEntity<>(new ResponseDTO(exception.getMessage(), ErrorType.MALFORMED_REQUEST), HttpStatus.BAD_REQUEST);
        } else {
            out = new ResponseEntity<>(new ResponseDTO("An unknown error occurred: " + exception.getMessage(), ErrorType.UNKNOWN_ERROR), HttpStatus.BAD_REQUEST);
        }
        return out;
    }

}
