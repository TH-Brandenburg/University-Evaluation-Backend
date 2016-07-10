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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.thb.ue.dto.util.Department;
import de.thb.ue.backend.exception.AggregatedAnswerException;
import de.thb.ue.backend.exception.DBEntryDoesNotExistException;
import de.thb.ue.backend.exception.EvaluationException;
import de.thb.ue.backend.exception.ParticipantException;
import de.thb.ue.backend.model.Evaluation;
import de.thb.ue.backend.model.MCQuestion;
import de.thb.ue.backend.model.Question;
import de.thb.ue.backend.model.QuestionRevision;
import de.thb.ue.backend.model.Tutor;
import de.thb.ue.backend.service.interfaces.IEvaluationService;
import de.thb.ue.backend.service.interfaces.IQuestionsService;
import de.thb.ue.backend.service.interfaces.ISubjectService;
import de.thb.ue.backend.service.interfaces.ITutorService;
import de.thb.ue.backend.util.SemesterType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@org.springframework.stereotype.Controller
public class ViewController extends WebMvcConfigurerAdapter {

    @Autowired
    private IEvaluationService evaluationService;

    @Autowired
    private ITutorService tutorService;

    @Autowired
    private ISubjectService subjectService;

    @Autowired
    private IQuestionsService questionsService;

//    @Autowired
//    private PersonRepo personRepo;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    String index(Model model) {
        //TODO
        LdapUserDetails user = (LdapUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Tutor> tutors = tutorService.getByFamilyName(user.getUsername());
        List<Evaluation> evaluations;

        if (tutors != null && !tutors.isEmpty()) {

            evaluations = tutors.
                    get(0).getEvaluations().stream().filter(evaluation -> !evaluation.getClosed()).collect(Collectors.toList());

        } else {
            //TODO delete workaround
            log.debug("Logged in user was no tutor.");
            return "redirect:/logout";
        }
        model.addAttribute("evaluations", evaluations);
        return "index";
    }

    @RequestMapping(value = "/archive", method = RequestMethod.GET)
    String archive(Model model) {
        //TODO
        LdapUserDetails user = (LdapUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Evaluation> evaluations = tutorService.getByFamilyName(user.getUsername()).
                get(0).getEvaluations().stream().filter(Evaluation::getClosed).collect(Collectors.toList());
        model.addAttribute("evaluations", evaluations);
        return "archive";
    }

    @RequestMapping(value = "/new-evaluation", method = RequestMethod.GET)
    String setup(Model model) {
        LdapUserDetails user = (LdapUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //TODo
        model.addAttribute("tutors", new ArrayList<Tutor>() {{
            add(new Tutor("", user.getUsername(), Department.COMPUTER_SCIENCE_MEDIA, null));
        }});
        model.addAttribute("subjects", subjectService.getAll());
        model.addAttribute("semesterTypes", new String[]{"Sommer", "Winter"});
        model.addAttribute("revisions", questionsService.getRevisionNames());
        return "newEvaluation";
    }

    @RequestMapping(value = "/evaluation/{uid}", method = RequestMethod.GET)
    String evaluation(@PathVariable String uid, Model model) throws DBEntryDoesNotExistException, EvaluationException {

        model.addAttribute("evaluation", evaluationService.getByUID(uid));
        return "evaluation";
    }
    
    @RequestMapping(value= "/questionnaires", method = RequestMethod.GET)
    String getAllQuestionRevisions(Model model) {
    	model.addAttribute("questionnaires", questionsService.findAllQuestionRevisions());
    	return "questionnaires";
    }
    
    
    @RequestMapping(value = "/questionnaire/{id}", method = RequestMethod.GET)
    String getQuestionRevision(@PathVariable String id, Model model) {
    	QuestionRevision questionnaire = questionsService.getRevisionById(Integer.parseInt(id));
    	model.addAttribute("questionnaire", questionnaire);
    	model.addAttribute("questionCount", questionnaire.getQuestions().size());
    	model.addAttribute("mcQuestionCount", questionnaire.getMcQuestions().size());
    	return "questionnaire";
    }
    
    @RequestMapping(value = "/questionnaire/{id}", method = RequestMethod.POST)
    String updateQuestionRevision(@PathVariable String id, @RequestParam Map<String,String> allRequestParams, Model model) {
    	QuestionRevision questionnaire = questionsService.getRevisionById(Integer.parseInt(id));
    	questionnaire.setName(allRequestParams.get("name"));
    	
    	boolean textQuestionsFirst = false;
    	String textQuestionsFirstString = allRequestParams.get("text-questions-first");
    	if( textQuestionsFirstString != null ) {
    		textQuestionsFirst = true;
    	}
    	questionnaire.setTextQuestionsFirst(textQuestionsFirst);
    	
    	int mcQuestionCount = Integer.parseInt(allRequestParams.get("mc-question-count"));
    	List<MCQuestion>allMcQuestions = questionnaire.getMcQuestions();
    	for(int i=1; i <= mcQuestionCount; i++){
    		allMcQuestions.get(i-1).setText(allRequestParams.get("mc-question-text-" + i));;
    	}
    	
    	int questionCount = Integer.parseInt(allRequestParams.get("question-count"));
    	List<Question>allQuestions = questionnaire.getQuestions();
    	for(int i=1; i <= questionCount; i++){
    		allQuestions.get(i-1).setText(allRequestParams.get("question-text-" + i));;
    	}
    	
    	model.addAttribute("questionaire", questionnaire);
 
    	questionsService.updateQuestionRevision(questionnaire);
    	return "redirect:/questionnaire/" + id + "?success";
    }
    
    @RequestMapping(value = "/deleteMcQuestion/{id}", method = RequestMethod.POST)
    String deleteMcQuestion(@PathVariable String id, @RequestParam String questionnaireid) {
    	MCQuestion mcQuestion = questionsService.getMCQuestionById(Integer.parseInt(id));
    	QuestionRevision questionnaire = questionsService.getRevisionById(Integer.parseInt(questionnaireid));
    	questionnaire.getMcQuestions().remove(mcQuestion);
    	questionsService.updateQuestionRevision(questionnaire);
    	return "redirect:/questionnaire/" + questionnaireid + "?success";
    }
    
    @RequestMapping(value = "/deleteQuestion/{id}", method = RequestMethod.POST)
    String deleteQuestion(@PathVariable String id, @RequestParam String questionnaireid) {
    	Question question = questionsService.getQuestionById(Integer.parseInt(id));
    	QuestionRevision questionnaire = questionsService.getRevisionById(Integer.parseInt(questionnaireid));
    	questionnaire.getQuestions().remove(question);
    	questionsService.updateQuestionRevision(questionnaire);
    	return "redirect:/questionnaire/" + questionnaireid + "?success";
    }
    
    @RequestMapping(value = "/deleteQuestionnaire/{id}", method = RequestMethod.POST)
    String deleteQuestionRevision(@PathVariable String id) {
    	questionsService.deleteQuestionRevisionById(Integer.parseInt(id));
    	return "questionnaires";
    }

    @RequestMapping(value = "/evaluation/vote-count", method = RequestMethod.GET)
    ResponseEntity<Integer> voteCount(@RequestParam String uid, Model model) {
        int out = 0;
        try {
            out = evaluationService.getByUID(uid).getStudentsVoted();
        } catch (EvaluationException | DBEntryDoesNotExistException e) {
            log.error("Error while counting votes: " + e.getMessage());
        }
        return new ResponseEntity<>(out, HttpStatus.OK);
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    String login() {
        Authentication securityContext = SecurityContextHolder.getContext().getAuthentication();
        if (securityContext.getPrincipal().equals("anonymousUser")) {
            return "login";
        } else {
            return "redirect:/";
        }
    }

//    @RequestMapping(value = "test")
//    ResponseEntity<String> test() throws UnknownHostException {
////        LdapUserDetails user = (LdapUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
////        Person person =personRepo.findPerson(user.getDn());
////        return new ResponseEntity<>("CN "+ Arrays.toString(person.getCn()) +" "+person.getSn(), HttpStatus.OK);
//        return new ResponseEntity<>("Your current IP address : " + InetAddress.getLocalHost().getHostAddress(), HttpStatus.OK);
//    }

    @RequestMapping(value = "/qrcfile", method = RequestMethod.GET)
    void getEvaluationExcelFile(@RequestParam String uid, HttpServletResponse response) throws DBEntryDoesNotExistException, IOException, EvaluationException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=qrcodes.pdf");
        InputStream is = null;
        try {
            // get your file as InputStream
            is = new FileInputStream(evaluationService.getQRCodeFile(uid));
            // copy it to response's OutputStream
            FileCopyUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @RequestMapping(value = "/resultfile", method = RequestMethod.GET)
    void getQRCodeFile(@RequestParam String uid, HttpServletResponse response) throws DBEntryDoesNotExistException, IOException, EvaluationException {
        response.setHeader("Content-Disposition", "attachment; filename=result.zip");
        InputStream is = null;
        try {
            File resultFile = evaluationService.getSummaryFile(uid);
            if (resultFile == null) {
                throw new EvaluationException(EvaluationException.READ_RESULT_FILE, "Error while reading result file");
            }
            // get your file as InputStream
            is = new FileInputStream(resultFile);
            // copy it to response's OutputStream
            FileCopyUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @RequestMapping(value = "/evaluation/close", method = RequestMethod.GET)
    String evaluationClose(@RequestParam String uid, Model model) {
//        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            evaluationService.close(uid);
            return "redirect:/archive";
        } catch (AggregatedAnswerException e) {
            model.addAttribute("error message", "Die Evaluation kann erst geschlossen werden, wenn mindestens eine Person abgestimmt hat");
            return "redirect:/evaluation/" + uid;
        }
    }

    @RequestMapping(value = "/evaluation", method = RequestMethod.POST)
    String startEvaluation(Model model,
                           @RequestParam int semester,
                           @RequestParam int students,
                           @RequestParam String tutors,
                           @RequestParam int subject,
                           @RequestParam String semesterType,
                           @RequestParam String revision) throws ParticipantException, EvaluationException {

        if (semester > 0 && semester <= 8 && students > 1 && students < 1000) {
            String generatedUid;
            generatedUid = evaluationService.add(semester, students, tutors, subject,
                    semesterType.equalsIgnoreCase("Sommer") ? SemesterType.SUMMER : SemesterType.WINTER, revision);

            model.addAttribute("evaluationUID", generatedUid);
            return "redirect:/evaluation/" + generatedUid;
        } else {
            return "new-evaluation";
        }
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleEvaluationException(HttpServletRequest req, Exception exception) {
        log.error("Request: " + req.getRequestURL() + " raised " + exception);
        String message;

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", exception);
        mav.addObject("url", req.getRequestURL());
        if (exception instanceof DBEntryDoesNotExistException) {
            message = "Die angegebene UID existiert nicht.";
        } else if (exception instanceof ParticipantException) {
            message = "Es ist ein Fehler bei der Erstellung der QR-Codes aufgetreten.";
        } else if (exception instanceof IOException) {
            message = "Es ist ein Fehler bei der Erstellung der Datei aufgetreten.";
        } else if (exception instanceof EvaluationException) {
            if (((EvaluationException) exception).getType() == EvaluationException.READ_RESULT_FILE
                    || ((EvaluationException) exception).getType() == EvaluationException.READ_QRC_FILE) {
                message = "Es ist ein Fehler beim Lesen der Datei aufgetreten.";
            } else if (((EvaluationException) exception).getType() == EvaluationException.ALREADY_CLOSED) {
                message = "Die angeforderte Evaluation ist bereits abgeschlossen.";
            } else {
                message = "Es ist ein Fehler bei der Verabeitung der Evaluation aufgetreten";
            }
        } else {
            message = "Es ist ein unbekannter Fehler aufgetreten";
        }
        mav.addObject("message", message);
        mav.setViewName("error");
        return mav;

    }

}
