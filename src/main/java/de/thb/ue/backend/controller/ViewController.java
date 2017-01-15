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

import de.thb.ue.backend.exception.AggregatedAnswerException;
import de.thb.ue.backend.exception.DBEntryDoesNotExistException;
import de.thb.ue.backend.exception.EvaluationException;
import de.thb.ue.backend.exception.ParticipantException;
import de.thb.ue.backend.model.*;
import de.thb.ue.backend.service.interfaces.*;
import de.thb.ue.backend.util.SemesterType;
import de.thb.ue.backend.util.eval_helper.EvalHelper;
import de.thb.ue.backend.util.eval_helper.EvalScQuestion;
import de.thb.ue.dto.util.Department;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static de.thb.ue.backend.util.QuestionType.SingleChoiceQuestion;
import static de.thb.ue.backend.util.QuestionType.TextQuestion;

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

    @Autowired
    private IVoteService voteService;

    @Autowired
    private ISCAnswerService scAnswerService;

	// @Autowired
	// private PersonRepo personRepo;

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	String index(Model model) {
		// TODO
		LdapUserDetails user = (LdapUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Tutor tutor = tutorService.getByUsername(user.getUsername());
		List<Evaluation> evaluations;

		if (tutor != null) {
			evaluations = tutor.getEvaluations().stream().filter(evaluation -> !evaluation.getClosed()).collect(Collectors.toList());
		} else {
			// TODO delete workaround
			log.debug("Logged in user was no tutor.");
			return "redirect:/logout";
		}
		model.addAttribute("evaluations", evaluations);
		return "index";
	}

	@RequestMapping(value = "/archive", method = RequestMethod.GET)
	String archive(Model model) {
		// TODO
		LdapUserDetails user = (LdapUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user.getUsername().equals("socher")) {
            List<Evaluation> evaluations = new ArrayList<Evaluation>();
            allEvaluation(evaluations, tutorService.getAll().size()); //alle Evaluationen von allen Tutoren in einer Liste
            List<Evaluation> profList = new ArrayList<Evaluation>();
            singleTutorList(profList, evaluations, evaluations.size()); // Liste, bei der jeder Professor nur einmal vorkommt
            List<Float> averageGradeTutorList = new ArrayList<Float>(); // Liste, in der die Durchschnittsnoten gespeichert sind
            averageGradeTutor(averageGradeTutorList);
            model.addAttribute("AverageGradeProf", averageGradeTutorList);
            model.addAttribute("evaluations", profList);
            model.addAttribute("tutor", user.getUsername());

            return "archive";
        } else {
            Tutor tutor = tutorService.getByUsername(user.getUsername());
            List<Evaluation> evaluations = tutor.getEvaluations()
                    .stream().filter(Evaluation::getClosed).collect(Collectors.toList());

            // Berechnung der Durchschnittsnote eines Professors
            double mainAverageGradeVariable = evaluationService.getAvgForTutor(tutor);
            double mainAverageGradeRounded = Math.round(mainAverageGradeVariable * 100.0) / 100.0;

            //Berechnung der Rücklaufquote
            float mainResponseRate = responseRate(evaluations, evaluations.size());

            //Evaluationen nach Datum sortieren
            dateSortEvaluation(evaluations, evaluations.size());

            //neue Liste damit eine Veranstaltung nur einmal angezeigt wird
            List<Evaluation> subjectList = new ArrayList<Evaluation>();
            singleSubjectList(subjectList, evaluations, evaluations.size());

            //Berechnung der Rücklaufquote für ein Fach insgesamt
            subjectResponseAnswer(subjectList, evaluations);

            //Berechnung der Durchschnittsnote der letzten 5 Evaluationen
            List<Evaluation> collumnEvaList = new ArrayList<Evaluation>();
            evaluationFilter(collumnEvaList,evaluations, evaluations.size());  //Filterung von Evaluationen mit den Fragen 8, 16, 17, 27 und 32
            String dateFormatString = "dd.MM.YYYY(HH:mm)";
            float overallGradeSemOne = 0;
            float overallGradeSemTwo = 0;
            float overallGradeSemThree = 0;
            float overallGradeSemFour = 0;
            float overallGradeSemFive = 0;
            String overallGradeSemOneDate = "nicht vorhanden";
            String overallGradeSemTwoDate = "nicht vorhanden";
            String overallGradeSemThreeDate = "nicht vorhanden";
            String overallGradeSemFourDate = "nicht vorhanden";
            String overallGradeSemFiveDate = "nicht vorhanden";
            String overallGradeSemOneName = "nicht vorhanden";
            String overallGradeSemTwoName = "nicht vorhanden";
            String overallGradeSemThreeName = "nicht vorhanden";
            String overallGradeSemFourName = "nicht vorhanden";
            String overallGradeSemFiveName = "nicht vorhanden";
            for (int i = 0; i < collumnEvaList.size(); i++) {
                if (i == 5) {
                    break;
                }
                if (i == 0) {
                    overallGradeSemOne = collumnAverageGrade(collumnEvaList, i);
                    overallGradeSemOneName = collumnEvaList.get(i).getSubject().getName();
                    overallGradeSemOneDate = collumnEvaList.get(i).getDateOfEvaluation().toString(dateFormatString);
                }
                if (i == 1) {
                    overallGradeSemTwo = collumnAverageGrade(collumnEvaList, i);
                    overallGradeSemTwoName = collumnEvaList.get(i).getSubject().getName();
                    overallGradeSemTwoDate = collumnEvaList.get(i).getDateOfEvaluation().toString(dateFormatString);
                }
                if (i == 2) {
                    overallGradeSemThree = collumnAverageGrade(collumnEvaList, i);
                    overallGradeSemThreeName = collumnEvaList.get(i).getSubject().getName();
                    overallGradeSemThreeDate = collumnEvaList.get(i).getDateOfEvaluation().toString(dateFormatString);
                }
                if (i == 3) {
                    overallGradeSemFour = collumnAverageGrade(collumnEvaList, i);
                    overallGradeSemFourName = collumnEvaList.get(i).getSubject().getName();
                    overallGradeSemFourDate = collumnEvaList.get(i).getDateOfEvaluation().toString(dateFormatString);
                }
                if (i == 4) {
                    overallGradeSemFive = collumnAverageGrade(collumnEvaList, i);
                    overallGradeSemFiveName = collumnEvaList.get(i).getSubject().getName();
                    overallGradeSemFiveDate = collumnEvaList.get(i).getDateOfEvaluation().toString(dateFormatString);
                }
            }

            model.addAttribute("evaluations", subjectList);
            if (subjectList.isEmpty() == false) {
                model.addAttribute("tutor", subjectList.get(0).getTutors().get(0).getFamilyName().toString());
                model.addAttribute("tutorID", subjectList.get(0).getTutors().get(0).getId());
            }

            model.addAttribute("evaCount", evaluations.size());
            if (mainAverageGradeVariable >= 1.0) {
                model.addAttribute("mainAverage", mainAverageGradeRounded);
            }
            model.addAttribute("gEvaCount", collumnEvaList.size());
            model.addAttribute("oGradeSemOneDate", overallGradeSemOneDate);
            model.addAttribute("oGradeSemTwoDate", overallGradeSemTwoDate);
            model.addAttribute("oGradeSemThreeDate", overallGradeSemThreeDate);
            model.addAttribute("oGradeSemFourDate", overallGradeSemFourDate);
            model.addAttribute("oGradeSemFiveDate", overallGradeSemFiveDate);

            model.addAttribute("oGradeSemOneVName", overallGradeSemOneName);
            model.addAttribute("oGradeSemTwoVName", overallGradeSemTwoName);
            model.addAttribute("oGradeSemThreeVName", overallGradeSemThreeName);
            model.addAttribute("oGradeSemFourVName", overallGradeSemFourName);
            model.addAttribute("oGradeSemFiveVName", overallGradeSemFiveName);

            model.addAttribute("oGradeSemOne", overallGradeSemOne);
            model.addAttribute("oGradeSemTwo", overallGradeSemTwo);
            model.addAttribute("oGradeSemThree", overallGradeSemThree);
            model.addAttribute("oGradeSemFour", overallGradeSemFour);
            model.addAttribute("oGradeSemFive", overallGradeSemFive);

            model.addAttribute("mainResponse", mainResponseRate);
        }
        return "archive2";
	}


    @RequestMapping(value = "/auswertung", method = RequestMethod.GET)
    String auswertung(Model model) {
        //TODO
        LdapUserDetails user = (LdapUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user.getUsername().equals("socher")) {
            List<Evaluation> evaluations = tutorService.getByUsername(user.getUsername()).
                    getEvaluations().stream().filter(Evaluation::getClosed).collect(Collectors.toList());
            model.addAttribute("evaluations", evaluations);
            return "archive";
        } else {
            return "nope";
        }
    }

    //NEW CODE ARCHIVE_EBENE 2
    @RequestMapping(value = "/archive2", method = RequestMethod.GET)
    String archive2(@RequestParam int tutorID, Model model) {
        //TODO
        List<Evaluation> evaluations = new ArrayList<Evaluation>();
        LdapUserDetails user = (LdapUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//Sicherheitsabfrage, damit nur der Superuser auf alle Daten zugriff hat
        if (user.getUsername().equals("socher"))
        {
            tutorIdList(evaluations, tutorID); // Evaluationen von einem Professor
        }
        else
        {
            evaluations = tutorService.getByUsername(user.getUsername()).
                    getEvaluations().stream().filter(Evaluation::getClosed).collect(Collectors.toList());
        }

        //Berechnung der Durchschnittsnote eines Professors
        float mainAverageGradeVariable= mainAverageGrade(evaluations, evaluations.size());
        double mainAverageGradeRounded = Math.round(mainAverageGradeVariable * 100.0) / 100.0;
        //Berechnung der Rücklaufquote
        float mainResponseRate = responseRate(evaluations, evaluations.size());

        //Evaluationen nach Datum sortieren
        dateSortEvaluation(evaluations, evaluations.size());

        //neue Liste damit eine Veranstaltung nur einmal angezeigt wird
        List<Evaluation> subjectList = new ArrayList<Evaluation>();
        singleSubjectList(subjectList, evaluations, evaluations.size());

        //Berechnung der Rücklaufquote für ein Fach insgesamt
        subjectResponseAnswer(subjectList, evaluations);

        //Berechnung der Durchschnittsnote der letzten 5 Evaluationen mit gesamtnote
        List<Evaluation> collumnEvaList = new ArrayList<Evaluation>();
        evaluationFilter(collumnEvaList,evaluations, evaluations.size());  //for-schleife die evaluationen mit den Fragen 8, 16, 17, 27 und 32  herausfiltert
        //Variablen fuer das Collumn-Diagramm
        String dateFormatString = "dd.MM.YYYY(HH:mm)";
        float overallGradeSemOne = 0;
        float overallGradeSemTwo = 0;
        float overallGradeSemThree = 0;
        float overallGradeSemFour = 0;
        float overallGradeSemFive = 0;
        String overallGradeSemOneDate = "nicht vorhanden";
        String overallGradeSemTwoDate = "nicht vorhanden";
        String overallGradeSemThreeDate = "nicht vorhanden";
        String overallGradeSemFourDate = "nicht vorhanden";
        String overallGradeSemFiveDate = "nicht vorhanden";
        String overallGradeSemOneName = "nicht vorhanden";
        String overallGradeSemTwoName = "nicht vorhanden";
        String overallGradeSemThreeName = "nicht vorhanden";
        String overallGradeSemFourName = "nicht vorhanden";
        String overallGradeSemFiveName = "nicht vorhanden";
        for (int i = 0; i < collumnEvaList.size(); i++) {
            if (i == 5) {
                break;
            }
            if (i == 0) {
                overallGradeSemOne = collumnAverageGrade(collumnEvaList, i);
                overallGradeSemOneName = collumnEvaList.get(i).getSubject().getName();
                overallGradeSemOneDate = collumnEvaList.get(i).getDateOfEvaluation().toString(dateFormatString);
            }
            if (i == 1) {
                overallGradeSemTwo = collumnAverageGrade(collumnEvaList, i);
                overallGradeSemTwoName = collumnEvaList.get(i).getSubject().getName();
                overallGradeSemTwoDate = collumnEvaList.get(i).getDateOfEvaluation().toString(dateFormatString);
            }
            if (i == 2) {
                overallGradeSemThree = collumnAverageGrade(collumnEvaList, i);
                overallGradeSemThreeName = collumnEvaList.get(i).getSubject().getName();
                overallGradeSemThreeDate = collumnEvaList.get(i).getDateOfEvaluation().toString(dateFormatString);
            }
            if (i == 3) {
                overallGradeSemFour = collumnAverageGrade(collumnEvaList, i);
                overallGradeSemFourName = collumnEvaList.get(i).getSubject().getName();
                overallGradeSemFourDate = collumnEvaList.get(i).getDateOfEvaluation().toString(dateFormatString);
            }
            if (i == 4) {
                overallGradeSemFive = collumnAverageGrade(collumnEvaList, i);
                overallGradeSemFiveName = collumnEvaList.get(i).getSubject().getName();
                overallGradeSemFiveDate = collumnEvaList.get(i).getDateOfEvaluation().toString(dateFormatString);
            }
        }

        model.addAttribute("evaluations", subjectList);
        model.addAttribute("tutor", subjectList.get(0).getTutors().get(0).getFamilyName().toString());
        model.addAttribute("tutorID", tutorID);

        model.addAttribute("evaCount", evaluations.size());
        if (mainAverageGradeVariable >= 1.0) {
            model.addAttribute("mainAverage", mainAverageGradeRounded);
        }

        model.addAttribute("gEvaCount", collumnEvaList.size());
        model.addAttribute("oGradeSemOneDate", overallGradeSemOneDate);
        model.addAttribute("oGradeSemTwoDate", overallGradeSemTwoDate);
        model.addAttribute("oGradeSemThreeDate", overallGradeSemThreeDate);
        model.addAttribute("oGradeSemFourDate", overallGradeSemFourDate);
        model.addAttribute("oGradeSemFiveDate", overallGradeSemFiveDate);

        model.addAttribute("oGradeSemOneVName", overallGradeSemOneName);
        model.addAttribute("oGradeSemTwoVName", overallGradeSemTwoName);
        model.addAttribute("oGradeSemThreeVName", overallGradeSemThreeName);
        model.addAttribute("oGradeSemFourVName", overallGradeSemFourName);
        model.addAttribute("oGradeSemFiveVName", overallGradeSemFiveName);

        model.addAttribute("oGradeSemOne", overallGradeSemOne);
        model.addAttribute("oGradeSemTwo", overallGradeSemTwo);
        model.addAttribute("oGradeSemThree", overallGradeSemThree);
        model.addAttribute("oGradeSemFour", overallGradeSemFour);
        model.addAttribute("oGradeSemFive", overallGradeSemFive);


        model.addAttribute("mainResponse", mainResponseRate);

        return "archive2";
    }

    //NEW CODE ARCHIVE_EBENE 3
    @RequestMapping(value = "/archive3", method = RequestMethod.GET)
    String archive3(@RequestParam int subjectID, @RequestParam int tutorID, Model model)
    {
        //TODO
        List<Evaluation> evaluations = new ArrayList<Evaluation>();
        LdapUserDetails user = (LdapUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //Sicherheitsabfrage, damit nur der Superuser auf alle Daten zugriff hat
        if (user.getUsername().equals("socher"))
        {
            tutorIdList(evaluations, tutorID); // Evaluationen von einem Professor
        }
        else
        {
            evaluations = tutorService.getByUsername(user.getUsername()).
                    getEvaluations().stream().filter(Evaluation::getClosed).collect(Collectors.toList());
        }

        List<Evaluation> relEvaList = new ArrayList<Evaluation>();
        tutorSubjectIdList(relEvaList, evaluations,tutorID, subjectID); //Herausfiltern relevanter Veranstaltungen

        //Evaluationen nach Datum sortieren
        dateSortEvaluation(relEvaList, relEvaList.size());

        //Berechnung der Durchschnittsnote einer Veranstaltung
        float subjectAverageGradeVariable = mainAverageGrade(relEvaList, relEvaList.size());
        double subjectAverageGradeRounded = Math.round(subjectAverageGradeVariable * 100.0) / 100.0;

        // Rücklaufquote der Veranstaltung
        float subjectResponseRate = responseRate(relEvaList, relEvaList.size());

        //Berechnung der Durchschnittsnote der letzten 5 Evaluationen
        List<Evaluation> collumnEvaList = new ArrayList<Evaluation>();
        evaluationFilter(collumnEvaList,relEvaList, relEvaList.size()); //for-schleife die evaluationen mit den Fragen 8, 16, 17, 27 und 32 herausfiltert
        // Variablen fuer Collumn-Diagramm
        String dateFormatString = "dd.MM.YYYY(HH:mm)";
        float subjectGradeOne = 0;
        float subjectGradeTwo = 0;
        float subjectGradeThree = 0;
        float subjectGradeFour = 0;
        float subjectGradeFive = 0;
        String subjectGradeOneDate = "nicht vorhanden";
        String subjectGradeTwoDate = "nicht vorhanden";
        String subjectGradeThreeDate = "nicht vorhanden";
        String subjectGradeFourDate = "nicht vorhanden";
        String subjectGradeFiveDate = "nicht vorhanden";
        String subjectGradeOneName = "nicht vorhanden";
        String subjectGradeTwoName = "nicht vorhanden";
        String subjectGradeThreeName = "nicht vorhanden";
        String subjectGradeFourName = "nicht vorhanden";
        String subjectGradeFiveName = "nicht vorhanden";

        /*List<EvalAnalysisHelper> evalAnalysisHelpers = new ArrayList<>();
        for (Evaluation evaluation : relEvaList) {
            Map<Question, AggregateEvaluationHelper> map = new HashMap<>();
            for (Question question : evaluation.getQuestionRevision().getQuestions()) {
                if (question.getType() == QuestionType.SingleChoiceQuestion) {
                    map.put(question, evaluationService.findAvgGradeByEvaluationIdAndQuestionId(evaluation, question));
                }
            }
            evalAnalysisHelpers.add(new EvalAnalysisHelper(evaluation, map));
        }*/

        //model.addAttribute("evalHelper", evalAnalysisHelpers);
        //model.addAttribute("test", evaluationService.getAvgForEvaluation(evaluations.get(0)));

        for (int i = 0; i < collumnEvaList.size(); i++) {
            if (i == 5) {
                break;
            }
            if (i == 0) {
                subjectGradeOne = collumnAverageGrade(collumnEvaList, i);
                subjectGradeOneName = collumnEvaList.get(i).getSubject().getName();
                subjectGradeOneDate = collumnEvaList.get(i).getDateOfEvaluation().toString(dateFormatString);
            }
            if (i == 1) {
                subjectGradeTwo = collumnAverageGrade(collumnEvaList, i);
                subjectGradeTwoName = collumnEvaList.get(i).getSubject().getName();
                subjectGradeTwoDate = collumnEvaList.get(i).getDateOfEvaluation().toString(dateFormatString);
            }
            if (i == 2) {
                subjectGradeThree = collumnAverageGrade(collumnEvaList, i);
                subjectGradeThreeName = collumnEvaList.get(i).getSubject().getName();
                subjectGradeThreeDate = collumnEvaList.get(i).getDateOfEvaluation().toString(dateFormatString);

            }
            if (i == 3) {
                subjectGradeFour = collumnAverageGrade(collumnEvaList, i);
                subjectGradeFourName = collumnEvaList.get(i).getSubject().getName();
                subjectGradeFourDate = collumnEvaList.get(i).getDateOfEvaluation().toString(dateFormatString);
            }
            if (i == 4) {
                subjectGradeFive = collumnAverageGrade(collumnEvaList, i);
                subjectGradeFiveName = collumnEvaList.get(i).getSubject().getName();
                subjectGradeFiveDate = collumnEvaList.get(i).getDateOfEvaluation().toString(dateFormatString);
            }
        }

        model.addAttribute("evaluations", relEvaList);
        model.addAttribute("subjectEvaCount", collumnEvaList.size());
        model.addAttribute("subjectGradeSemOneDate", subjectGradeOneDate);
        model.addAttribute("subjectGradeSemTwoDate", subjectGradeTwoDate);
        model.addAttribute("subjectGradeSemThreeDate", subjectGradeThreeDate);
        model.addAttribute("subjectGradeSemFourDate", subjectGradeFourDate);
        model.addAttribute("subjectGradeSemFiveDate", subjectGradeFiveDate);
        model.addAttribute("subjectGradeSemOneVName", subjectGradeOneName);
        model.addAttribute("subjectGradeSemTwoVName", subjectGradeTwoName);
        model.addAttribute("subjectGradeSemThreeVName", subjectGradeThreeName);
        model.addAttribute("subjectGradeSemFourVName", subjectGradeFourName);
        model.addAttribute("subjectGradeSemFiveVName", subjectGradeFiveName);
        model.addAttribute("subjectGradeOne", subjectGradeOne);
        model.addAttribute("subjectGradeTwo", subjectGradeTwo);
        model.addAttribute("subjectGradeThree", subjectGradeThree);
        model.addAttribute("subjectGradeFour", subjectGradeFour);
        model.addAttribute("subjectGradeFive", subjectGradeFive);
        if (subjectAverageGradeVariable >= 1.0) {
            model.addAttribute("subjectNotenQ", subjectAverageGradeRounded);
        }
        model.addAttribute("subjectResponse", subjectResponseRate);
        model.addAttribute("averageGradeList", createAverageGradeList(relEvaList));
        model.addAttribute("pictureCommentList",createPictureCommentList(relEvaList));

        return "archive3";
    }
	@RequestMapping(value = "/new-evaluation", method = RequestMethod.GET)
	String setup(Model model) {
		LdapUserDetails user = (LdapUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// TODo
		model.addAttribute("tutors", new ArrayList<Tutor>() {
			{
				add(new Tutor("", "", user.getUsername(), Department.COMPUTER_SCIENCE_MEDIA, null));
			}
		});
		model.addAttribute("subjects", subjectService.getAll());
		model.addAttribute("semesterTypes", new String[] { "Sommer", "Winter" });
		model.addAttribute("revisions", questionsService.getRevisionNames());
		return "newEvaluation";
	}

	@RequestMapping(value = "/evaluation/{uid}", method = RequestMethod.GET)
	String evaluation(@PathVariable String uid, Model model) throws DBEntryDoesNotExistException, EvaluationException {

		model.addAttribute("evaluation", evaluationService.getByUID(uid));
		return "evaluation";
	}

	@RequestMapping(value = "/questionnaires", method = RequestMethod.GET)
	String getAllQuestionRevisions(Model model) {
		model.addAttribute("questionnaires", questionsService.findAllQuestionRevisions());
		return "questionnaires";
	}

	@RequestMapping(value = "/newQuestionnaire", method = RequestMethod.GET)
	String getNewQuestionnaire(Model model) {
		return "newQuestionnaire";
	}

	@RequestMapping(value = "/newQuestionnaire", method = RequestMethod.POST)
	String saveNewQuestionnaire(@RequestParam Map<String, String> allRequestParams, Model model, RedirectAttributes redirectAttributes) {
		QuestionRevision questionnaire = new QuestionRevision();
		questionnaire = hydrateQuestionRevision(questionnaire, allRequestParams);
		String questionnaireName = allRequestParams.get("name").trim();
		List<String> revisionNames = questionsService.getRevisionNames();

		for (String revisionName : revisionNames) {
			if (revisionName.equals(questionnaireName)) {
				model.addAttribute("questionnaire", questionnaire);
				model.addAttribute("success", false);
				model.addAttribute("message", "Ein Fragebogen mit diesem Namen existiert bereits.");
				setupQuestionnaireModel(model, questionnaire);
				return "newQuestionnaire";
			}
		}

		int id = questionsService.saveQuestionRevision(questionnaire).getId();

		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("message", "Der Fragebogen wurde erfolgreich erstellt.");

		return "redirect:/questionnaire/" + id;
	}

	@RequestMapping(value = "/questionnaire/{id}", method = RequestMethod.GET)
	String getQuestionRevision(@PathVariable String id, Model model) {
		QuestionRevision questionnaire = null;

        if (model.containsAttribute("questionnaire")) {
            Object questionnaireTemp = model.asMap().get("questionnaire");
            if (questionnaireTemp != null && questionnaireTemp instanceof QuestionRevision) {
                questionnaire = (QuestionRevision) questionnaireTemp;
            }
        } else {
            questionnaire = questionsService.getRevisionById(Integer.parseInt(id));
        }

		setupQuestionnaireModel(model, questionnaire);

		return "questionnaire";
	}

	@RequestMapping(value = "/questionnaire/{id}", method = RequestMethod.POST)
	String updateQuestionRevision(@PathVariable String id, @RequestParam Map<String, String> allRequestParams, Model model,
			RedirectAttributes redirectAttributes) {
		boolean evaluationExistsForQuestionRevision = evaluationService.evaluationWithQuestionRevisionExists(Integer.parseInt(id));
		if (evaluationExistsForQuestionRevision) {
			QuestionRevision newQuestionnaire = new QuestionRevision();
			newQuestionnaire = hydrateQuestionRevision(newQuestionnaire, allRequestParams);
			String name = allRequestParams.get("name").trim();
			List<String> revisionNames = questionsService.getRevisionNames();
			for (String revisionName : revisionNames) {
				if (revisionName.equals(name)) {
					String timestamp = new SimpleDateFormat("(yyyy.MM.dd HH:mm:ss)").format(new Date());
					name = name.replaceAll("^(.*)\\(\\d{4}\\.\\d{2}\\.\\d{2} \\d{2}:\\d{2}:\\d{2}\\)$", "$1");
					name = name + " " + timestamp;
					newQuestionnaire.setName(name);
				}
			}

			int questionnaireId = questionsService.saveQuestionRevision(newQuestionnaire).getId();
			redirectAttributes.addFlashAttribute("success", true);
			redirectAttributes.addFlashAttribute("message", "Der Fragebogen wurde erfolgreich aktualisiert.");
			return "redirect:/questionnaire/" + questionnaireId;

		} else {
			QuestionRevision questionnaire = questionsService.getRevisionById(Integer.parseInt(id));
			String oldQuestionnaireName = questionnaire.getName();
			int questionnaireId = questionnaire.getId();
			List<Choice> oldChoices = questionnaire.getChoices();
			List<Question> oldQuestions = questionnaire.getQuestions();

			String questionnaireName = allRequestParams.get("name").trim();
			List<String> revisionNames = questionsService.getRevisionNames();

			questionnaire = hydrateQuestionRevision(questionnaire, allRequestParams);
			if(!oldQuestionnaireName.equals(questionnaireName)){
				for (String revisionName : revisionNames) {
					if (revisionName.equals(questionnaireName)) {
						redirectAttributes.addFlashAttribute("questionnaire", questionnaire);
						redirectAttributes.addFlashAttribute("success", false);
						redirectAttributes.addFlashAttribute("message", "Ein Fragebogen mit diesem Namen existiert bereits.");
						return "redirect:/questionnaire/" + questionnaireId;
					}
				}
			}

			questionsService.updateQuestionRevision(questionnaire);
			questionsService.deleteQuestionsAndChoices(oldChoices, oldQuestions);
			redirectAttributes.addFlashAttribute("success", true);
			redirectAttributes.addFlashAttribute("message", "Der Fragebogen wurde erfolgreich aktualisiert.");
			return "redirect:/questionnaire/" + id;
		}
	}

	@RequestMapping(value = "/deleteQuestionnaire/{id}", method = RequestMethod.POST)
	String deleteQuestionRevision(@PathVariable String id) {
		questionsService.deleteQuestionRevisionById(Integer.parseInt(id));
		return "redirect:/questionnaires/";
	}

	@RequestMapping(value = "/evaluation/vote-count", method = RequestMethod.GET)
	ResponseEntity<Integer> voteCount(@RequestParam String uid, Model model) {
		int out = 0;
		try {
			if (uid.contains("?")) {
				uid = uid.replace("?", "");
			}
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

	 /*@RequestMapping(value = "/test", method = RequestMethod.GET)
	 ResponseEntity test() {
	    Tutor tutor = tutorService.getByUsername("bartz");
         List<Evaluation> evaluations = tutor.getEvaluations();
         List<SingleChoiceAnswer> singleChoiceAnswers = new ArrayList<>();
         for (Vote vote : evaluations.get(1).getVotes()) {
             singleChoiceAnswers.addAll(vote.getSingleChoiceAnswers());
         }
	 return new ResponseEntity<>(scAnswerService.computeAvgGrade(singleChoiceAnswers), HttpStatus.OK);
	 }*/

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
            if (resultFile == null)
            {
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

    @RequestMapping(value = "/imagefile", method = RequestMethod.GET)
    void getImageFile(@RequestParam String uid, @RequestParam int voteID, HttpServletResponse response) throws DBEntryDoesNotExistException, IOException, EvaluationException {
        String responsetxt = "attachment; filename="+voteID+".zip";
        response.setHeader("Content-Disposition", responsetxt);
        InputStream is = null;
        try {
            File imageFile = evaluationService.getImageFile(uid, voteID);
            if (imageFile == null)
            {
                throw new EvaluationException(EvaluationException.READ_RESULT_FILE, "Error while reading result file");
            }
            // get your file as InputStream
            is = new FileInputStream(imageFile);
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
	String startEvaluation(Model model, @RequestParam int semester, @RequestParam int students, @RequestParam String tutors, @RequestParam int subject,
			@RequestParam String semesterType, @RequestParam String revision, @RequestParam Map<String, String> allRequestParams)
			throws ParticipantException, EvaluationException {

		int questionPosition = questionsService.findByName(revision).getQuestions().size();

		if (semester > 0 && semester <= 8 && students > 1 && students < 1000) {
			String generatedUid;

			List<Question> adhocQuestions = new ArrayList<Question>();

			int additionalQuestionCount = Integer.parseInt(allRequestParams.get("question-count"));

			for (int j = 1; j <= additionalQuestionCount; j++) {
				String questionType = allRequestParams.get("question-type-" + j);
				if (questionType.equals("Textfrage")) {
					TextQuestion textQuestion = new TextQuestion();
					questionPosition++;
					textQuestion.setQuestionPosition(questionPosition);
					String text = allRequestParams.get("question-" + j);
					int maxLength = Integer.parseInt(allRequestParams.get("max-chars-" + j));
					boolean onlyNumbers = false;
					String onlyNumbersString = allRequestParams.get("numbers-only-" + j);
					if (onlyNumbersString != null) {
						onlyNumbers = true;
					}

					textQuestion.setType(TextQuestion);
					textQuestion.setText(text);
					textQuestion.setMaxLength(maxLength);
					textQuestion.setOnlyNumbers(onlyNumbers);
					adhocQuestions.add(textQuestion);

				}
				if (questionType.equals("Best First") || questionType.equals("Best In The Middle")) {
					SingleChoiceQuestion singleChoiceQuestion = new SingleChoiceQuestion();
					questionPosition++;
					singleChoiceQuestion.setQuestionPosition(questionPosition);
					String text = allRequestParams.get("question-" + j);
					List<Choice> choices = new ArrayList<Choice>();
					int choicesNumber = Integer.parseInt(allRequestParams.get("choices-number-" + j));
					for (int i = 1; i <= choicesNumber; i++) {
						Choice choice = new Choice();
						choice.setText(allRequestParams.get("choice-text-" + j + "-" + i));
						choice.setGrade(Short.parseShort(allRequestParams.get("choice-grade-" + j + "-" + i)));
						choices.add(choice);
					}
					boolean noAnswer = false;
					String noAnswerString = allRequestParams.get("no-answer-" + j);
					if (noAnswerString != null) {
						noAnswer = true;
					}

					if (noAnswer) {
						Choice noAnswerChoice = new Choice();
						noAnswerChoice.setText(allRequestParams.get("choice-text-" + j + "-" + 0));
						noAnswerChoice.setGrade((short) 0);
						choices.add(noAnswerChoice);
					}

					singleChoiceQuestion.setType(SingleChoiceQuestion);
					singleChoiceQuestion.setText(text);
					singleChoiceQuestion.setChoices(choices);
					adhocQuestions.add(singleChoiceQuestion);
				}
			}

			generatedUid = evaluationService.add(semester, students, tutors, subject,
					semesterType.equalsIgnoreCase("Sommer") ? SemesterType.SUMMER : SemesterType.WINTER, revision, adhocQuestions);

			model.addAttribute("evaluationUID", generatedUid);
			return "redirect:/evaluation/" + generatedUid;
		} else {
			return "new-evaluation";
		}
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleEvaluationException(HttpServletRequest req, Exception exception) {
		log.error("Request: " + req.getRequestURL() + " raised " + exception);
        exception.printStackTrace();
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

	private QuestionRevision hydrateQuestionRevision(QuestionRevision questionRevision, Map<String, String> allRequestParams) {
		int questionCount = Integer.parseInt(allRequestParams.get("question-count"));
		List<Question> questions = new ArrayList<Question>();
		List<Choice> questionRevisionChoices = new ArrayList<Choice>();

		String questionnaireName = allRequestParams.get("name").trim();
		questionRevision.setName(questionnaireName);

		boolean textQuestionsFirst = false;
		String textQuestionsFirstString = allRequestParams.get("text-questions-first");
		if (textQuestionsFirstString != null) {
			textQuestionsFirst = true;
		}
		questionRevision.setTextQuestionsFirst(textQuestionsFirst);
        for (int i = 1; i <= questionCount; i++) {
            String questionType = allRequestParams.get("question-type-" + i);
            if (questionType.equals("Textfrage")) {
                TextQuestion textQuestion = new TextQuestion();
                textQuestion.setQuestionPosition(i);
                String text = allRequestParams.get("question-" + i);
                int maxLength = Integer.parseInt(allRequestParams.get("max-chars-" + i));
                boolean onlyNumbers = false;
                String onlyNumbersString = allRequestParams.get("numbers-only-" + i);
                if (onlyNumbersString != null) {
                    onlyNumbers = true;
                }

                textQuestion.setType(TextQuestion);
                textQuestion.setText(text);
                textQuestion.setMaxLength(maxLength);
                textQuestion.setOnlyNumbers(onlyNumbers);
                questions.add(textQuestion);

            }
            if (questionType.equals("Best First") || questionType.equals("Best In The Middle")) {
                SingleChoiceQuestion singleChoiceQuestion = new SingleChoiceQuestion();
                singleChoiceQuestion.setQuestionPosition(i);
                String text = allRequestParams.get("question-" + i);
                List<Choice> choices = new ArrayList<Choice>();
                int choicesNumber = Integer.parseInt(allRequestParams.get("choices-number-" + i));
                for (int j = 1; j <= choicesNumber; j++) {
                    Choice choice = new Choice();
                    choice.setText(allRequestParams.get("choice-text-" + i + "-" + j));
                    choice.setGrade(Short.parseShort(allRequestParams.get("choice-grade-" + i + "-" + j)));
                    choices.add(choice);
                }
                boolean noAnswer = false;
                String noAnswerString = allRequestParams.get("no-answer-" + i);
                if (noAnswerString != null) {
                    noAnswer = true;
                }

                if (noAnswer) {
                    Choice noAnswerChoice = new Choice();
                    noAnswerChoice.setText(allRequestParams.get("choice-text-" + i + "-" + 0));
                    noAnswerChoice.setGrade((short) 0);
                    choices.add(noAnswerChoice);
                }

                singleChoiceQuestion.setType(SingleChoiceQuestion);
                singleChoiceQuestion.setText(text);
                singleChoiceQuestion.setChoices(choices);
                questionRevisionChoices.addAll(choices);
                questions.add(singleChoiceQuestion);
            }
        }

        questionRevision.setChoices(questionRevisionChoices);
        questionRevision.setQuestions(questions);

        return questionRevision;
    }

    private void setupQuestionnaireModel(Model model, QuestionRevision questionnaire) {
        // Unterscheiden zwischen Textfragen und Single-Choice-Fragen
        List<TextQuestion> textQuestions = new ArrayList<TextQuestion>();
        List<SingleChoiceQuestion> singleChoiceQuestionsTemp = new ArrayList<SingleChoiceQuestion>();
        for (Question question : questionnaire.getQuestions()) {
            if (question instanceof TextQuestion) {
                textQuestions.add((TextQuestion) question);
            } else if (question instanceof SingleChoiceQuestion) {
                singleChoiceQuestionsTemp.add((SingleChoiceQuestion) question);
            }
        }

        // Unterscheiden zwischen Best-First-Fragen und Best-In-The-Middle-Fragen
        List<AbstractMap.SimpleEntry<String, SingleChoiceQuestion>> singleChoiceQuestions = new ArrayList<AbstractMap.SimpleEntry<String, SingleChoiceQuestion>>();
        List<Choice> noAnswerChoices = new ArrayList<Choice>();
        for (SingleChoiceQuestion question : singleChoiceQuestionsTemp) {
            List<Choice> choicesTemp = question.getChoices();
            Choice noAnswerChoice = null;
            boolean containsNegativeGrade = false;

            for (Choice choice : choicesTemp) {
                if (choice.getGrade() < 0) {
                    containsNegativeGrade = true;
                }
                if (choice.getGrade() == 0) {
                    noAnswerChoice = choice;
                }
            }

            // "keine Angabe"-Choice aus der Liste aller Choices entfernen und einer separaten Liste hinzufügen
            if (noAnswerChoice != null) {
                choicesTemp.remove(noAnswerChoice);
            }
            noAnswerChoices.add(noAnswerChoice);

            if (containsNegativeGrade) {
                // Choices absteigend sortieren und Frage der Liste von Best-In-The-Middle-Fragen hinzufügen
                choicesTemp.sort((Choice choice1, Choice choice2) -> Short.compare(choice1.getGrade(), choice2.getGrade()) * -1);
                singleChoiceQuestions.add(new AbstractMap.SimpleEntry<String, SingleChoiceQuestion>("BestInTheMiddle", question));
            } else {
                // Choices aufsteigend sortieren und Frage der Liste von Best-First-Fragen hinzufügen
                choicesTemp.sort((Choice choice1, Choice choice2) -> Short.compare(choice1.getGrade(), choice2.getGrade()));
                singleChoiceQuestions.add(new AbstractMap.SimpleEntry<String, SingleChoiceQuestion>("BestFirst", question));
            }
        }

        model.addAttribute("questionnaire", questionnaire);
        model.addAttribute("textQuestions", textQuestions);
        model.addAttribute("singleChoiceQuestions", singleChoiceQuestions);
        model.addAttribute("noAnswerChoices", noAnswerChoices);
    }

    private float collumnAverageGrade(List<Evaluation> evaluationArray, int evaNumber)
    {
        // Berechnung der Durchschnittsnote für das Balkendiagramm
        float gradeAverage = 0;
        int studentsVoted = 0;
        for (int j = 0; j < evaluationArray.get(evaNumber).getQuestionRevision().getQuestions().size(); j++)// zählt vorhandene Fragen durch
        {
            int currentId = evaluationArray.get(evaNumber).getQuestionRevision().getQuestions().get(j).getId();
            if (currentId == 8 ||currentId == 16 ||currentId == 17||currentId == 27|| currentId == 32) {
                if (evaluationArray.get(evaNumber).getVotes().size() != 0) {
                    for (int stVote = 0; stVote < evaluationArray.get(evaNumber).getVotes().size(); stVote++) {
                        gradeAverage += evaluationArray.get(evaNumber).getVotes().get(stVote).getSingleChoiceAnswers().get(j).getChoice().getGrade();
                        if(evaluationArray.get(evaNumber).getVotes().get(stVote).getSingleChoiceAnswers().get(j).getChoice().getGrade() == 0)
                        {
                            studentsVoted -= 1;//wenn "keine Angabe" gewählt wird, wird der Vote abgezogen
                        }
                    }
                }
                studentsVoted += evaluationArray.get(evaNumber).getVotes().size(); // Anzahl der Votes wird aufaddiert
            }
        }
        return (gradeAverage / studentsVoted);

    }

    private float mainAverageGrade(List<Evaluation> evaluationArray, int evaluationLength) {
        //Berechnung der Gesamtdurchschnittsnote
        float subjectNoteDurchschnitt = 0;
        int studentsVotetMainQuestions = 0;
        for (int i = 0; i < evaluationLength; i++) // zählt evas durch
        {
            for (int j = 0; j < evaluationArray.get(i).getQuestionRevision().getQuestions().size(); j++)// zählt vorhandene Fragen durch
            {
                int currentId = evaluationArray.get(i).getQuestionRevision().getQuestions().get(j).getId();
                if (currentId == 8 ||currentId == 16 ||currentId == 17||currentId == 27|| currentId == 32) {
                    if (evaluationArray.get(i).getVotes().size() != 0) {
                        for (int stVote = 0; stVote < evaluationArray.get(i).getVotes().size(); stVote++) {
                            subjectNoteDurchschnitt += evaluationArray.get(i).getVotes().get(stVote).getSingleChoiceAnswers().get(j).getChoice().getGrade();
                            if(evaluationArray.get(i).getVotes().get(stVote).getSingleChoiceAnswers().get(j).getChoice().getGrade() == 0)
                            {
                                studentsVotetMainQuestions -= 1; //wenn "keine Angabe" gewählt wird, wird der Vote abgezogen
                            }
                        }
                    }
                    studentsVotetMainQuestions += evaluationArray.get(i).getVotes().size();// Anzahl der Votes wird aufaddiert
                }
            }
        }
        return (subjectNoteDurchschnitt / studentsVotetMainQuestions);
    }

    private List<Evaluation> dateSortEvaluation(List<Evaluation> evaluationArray, int evaluationLength)
    {
        // Sortieren der Evaluationen nach Datum
        for (int i = 0; i < evaluationLength; i++) {
            for (int j = 0; j < evaluationLength - 1; j++) {
                if (evaluationArray.get(j).getDateOfEvaluation().compareTo(evaluationArray.get(j + 1).getDateOfEvaluation()) == -1) {

                    Evaluation temp = evaluationArray.get(j);
                    evaluationArray.set(j, evaluationArray.get(j + 1));
                    evaluationArray.set(j + 1, temp);
                }
            }
        }

        return evaluationArray;
    }
    private float responseRate(List<Evaluation> evaluationArray, int evaluationLength)
    {
        //for schleife zur Berechnung der Ruecklaufquote
        float studentsAllCount = 0;
        float studentsVotetCount = 0;
        for (int i = 0; i < evaluationLength; i++)
        {
            studentsAllCount += evaluationArray.get(i).getStudentsAll();
            studentsVotetCount += evaluationArray.get(i).getStudentsVoted();
        }
        return (studentsVotetCount / studentsAllCount);
    }
    private List<Evaluation> allEvaluation(List<Evaluation> evaluationArray, int tutorCount)
    {
        //Sucht alle Evaluationen jedes Tutors und packt sie in eine Liste
        int evaListElement = 0;

        for (int i = 0; i < tutorCount; i++) {
            List<Evaluation> evaluationsFromTutor = tutorService.getByUsername(tutorService.getAll().get(i).getFamilyName()).
                    getEvaluations().stream().filter(Evaluation::getClosed).collect(Collectors.toList());
            if (evaluationsFromTutor.size() >= 1) {
                for (int j = 0; j < evaluationsFromTutor.size(); j++) {
                    evaluationArray.add(evaListElement, evaluationsFromTutor.get(j));
                    evaListElement += 1;
                }
            }
        }

        return evaluationArray;
    }
    private List<Evaluation> singleTutorList(List<Evaluation> newEvaluationArray, List<Evaluation> oldEvaluationArray, int evaLength)
    {
        //Erstellen einer Liste bei der jeder Professor nur einmal vorkommt
        int newListElementPos = 0;
        List<Integer> tutorIDList = new ArrayList<Integer>();// Liste in der bereits vorhandene Professoren gespeichert werden
        for (int i = 0; i < evaLength; i++) {
            boolean tutorListed = false;
            int tutorListLength = tutorIDList.size();
            if (tutorListLength != 0) {
                for (int j = 0; j < tutorListLength; j++)
                {
                    // Überprüfung ob die tutorID schon vorkommt
                    if (oldEvaluationArray.get(i).getTutors().get(0).getId() == tutorIDList.get(j)) {
                        tutorListed = true;
                    }
                }
            }
            if (tutorListed == false) //wenn tutorID nicht schon vorhanden ist, wird sie die Evaluation der neuen Liste hinzugefügt
            {
                newEvaluationArray.add(newListElementPos, oldEvaluationArray.get(i));
                tutorIDList.add(newListElementPos, oldEvaluationArray.get(i).getTutors().get(0).getId());
                newListElementPos += 1;

            }
        }
        return newEvaluationArray;
    }
    private List<Evaluation> singleSubjectList(List<Evaluation> newEvaluationArray, List<Evaluation> oldEvaluationArray, int evaLength) {
        //Erstellen einer Liste bei der jede Veranstaltung nur einmal vorkommt
        int newListElementPos = 0;
        List<Integer> subjectIDList = new ArrayList<Integer>();// liste in der bereits vorhandene Veranstaltungen gespeichert werden
        for (int i = 0; i < evaLength; i++) {
            boolean subjectListed = false;
            int tutorListLength = subjectIDList.size();
            if (tutorListLength != 0) {
                for (int j = 0; j < tutorListLength; j++) {
                    // Überprüfung ob die subjectID schon vorkommt
                    if (oldEvaluationArray.get(i).getSubject().getId() == subjectIDList.get(j)) {
                        subjectListed = true;
                    }
                }
            }
            if (subjectListed == false) //wenn subjectID nicht schon vorhanden ist, wird sie die Evaluation der neuen Liste hinzugefügt
            {
                newEvaluationArray.add(newListElementPos, oldEvaluationArray.get(i));
                subjectIDList.add(newListElementPos, oldEvaluationArray.get(i).getSubject().getId());
                newListElementPos += 1;
            }
        }
        return newEvaluationArray;
    }

    private List<Evaluation> evaluationFilter(List<Evaluation> newEvaluationArray, List<Evaluation> oldEvaluationArray, int evaLength) {

        //Herausfiltern von Evaluationen mit den FragenIDs 8 und 16 herausfiltert
        int newListElementPos = 0;
        for (int i = 0; i < evaLength; i++)// zählt Evaluationen durch
        {
            boolean questionAvailable = false;
            for (int j = 0; j < oldEvaluationArray.get(i).getQuestionRevision().getQuestions().size(); j++)// zählt vorhandene Fragen durch
            {
                int currentId = oldEvaluationArray.get(i).getQuestionRevision().getQuestions().get(j).getId();
                if (currentId == 8 ||currentId == 16 ||currentId == 17||currentId == 27|| currentId == 32) {
                    questionAvailable = true;
                }
            }
            if (questionAvailable == true) {
                newEvaluationArray.add(newListElementPos, oldEvaluationArray.get(i));
                newListElementPos += 1;
            }

        }
        return newEvaluationArray;
    }

    private List<Evaluation> subjectResponseAnswer(List<Evaluation> subjectArray, List<Evaluation> evaluationArray) {
        //Berechnung der Rücklaufquoten für Veranstaltungen
        for (int i = 0; i < subjectArray.size(); i++) {
            int studentsSubjectCount = 0;
            int studentsSubjectVoted = 0;
            for (int j = 0; j < evaluationArray.size(); j++) {
                if (evaluationArray.get(j).getSubject().getId() == subjectArray.get(i).getSubject().getId())//wenn ID übereinstimmt
                {
                    studentsSubjectCount += evaluationArray.get(j).getStudentsAll();
                    studentsSubjectVoted += evaluationArray.get(j).getStudentsVoted();
                }
            }
            subjectArray.get(i).setStudentsAll(studentsSubjectCount);
            subjectArray.get(i).setStudentsVoted(studentsSubjectVoted);
        }
        return subjectArray;
    }

    private List<Evaluation> tutorIdList(List<Evaluation> newEvaluationArray, int tutorID)
    {
        // Erstellen einer Liste mit allen Evaluationen eines Tutors
        int tutorCount = tutorService.getAll().size();// Anzahl aller möglichen Lehrenden
        int evaListElement = 0;
        for (int i = 0; i < tutorCount; i++) //durchläuft tutorIds
        {
            //Liste des jeweiligen Tutors
            List<Evaluation> evaluationsFromTutor = tutorService.getByUsername(tutorService.getAll().get(i).getFamilyName()).
                    getEvaluations().stream().filter(Evaluation::getClosed).collect(Collectors.toList());
            if (evaluationsFromTutor.size() >= 1) {
                for (int j = 0; j < evaluationsFromTutor.size(); j++) {
                    if (evaluationsFromTutor.get(j).getTutors().get(0).getId() == tutorID) {
                        newEvaluationArray.add(evaListElement, evaluationsFromTutor.get(j));
                        evaListElement += 1;
                    }
                }
            }
        }
        return newEvaluationArray;
    }

    private List<Evaluation> tutorSubjectIdList(List<Evaluation> newEvaluationArray, List<Evaluation> oldEvaluationArray,int tutorId, int subjectId)
    {
        //Filtert relevante Veranstaltungen aufgrund subjectId und tutorId
        int newListElementPos = 0;
        for (int i = 0; i < oldEvaluationArray.size(); i++) {
            if (oldEvaluationArray.get(i).getSubject().getId() == subjectId && oldEvaluationArray.get(i).getTutors().get(0).getId() == tutorId) {
                //in neue Liste hinzufügen
                newEvaluationArray.add(newListElementPos, oldEvaluationArray.get(i));
                newListElementPos += 1;
            }
        }
        return newEvaluationArray;
    }

    private List<Float>averageGradeTutor(List<Float> averageGradeList) {
        //Berechnung der Gesamtdurchschnittsnote
        for(int h = 0; h < tutorService.getAll().size(); h++)
        {
            float subjectNoteDurchschnitt = 0;
            int studentsVotetMainQuestions = 0;
            List<Evaluation> singleTutorList = new ArrayList<Evaluation>();
            tutorIdList(singleTutorList,h); //Herausfiltern des jeweiligen Tutors
            if (singleTutorList.size() >= 1)
            {
                for (int i = 0; i < singleTutorList.size(); i++) // zählt alle evaluationen eines Professors durch
                {
                    for (int j = 0; j < singleTutorList.get(i).getQuestionRevision().getQuestions().size(); j++)// zählt vorhandene Fragen durch
                    {
                        int currentId = singleTutorList.get(i).getQuestionRevision().getQuestions().get(j).getId();
                        if (currentId == 8 ||currentId == 16 ||currentId == 17||currentId == 27|| currentId == 32) {
                            if (singleTutorList.get(i).getVotes().size() != 0) {
                                for (int stVote = 0; stVote < singleTutorList.get(i).getVotes().size(); stVote++) {
                                    subjectNoteDurchschnitt += singleTutorList.get(i).getVotes().get(stVote).getSingleChoiceAnswers().get(j).getChoice().getGrade();
                                    if(singleTutorList.get(i).getVotes().get(stVote).getSingleChoiceAnswers().get(j).getChoice().getGrade() == 0)
                                    {
                                        studentsVotetMainQuestions -= 1; //wenn "keine Angabe" gewählt wird, wird der Vote abgezogen
                                    }
                                }
                            }
                            studentsVotetMainQuestions += singleTutorList.get(i).getVotes().size();// Anzahl der Votes wird aufaddiert
                        }
                    }
                }
            }

            averageGradeList.add(Math.round((subjectNoteDurchschnitt / studentsVotetMainQuestions)* 100.0f)/100.0f);
        }
        return averageGradeList;
    }

    private Float avg(List<Short> grades) {
        Float sum = 0f;
        int students = 0;
        if(!grades.isEmpty()) {
            for (Short grade : grades) {
                if (grade != 0) {
                    sum += grade;
                    students++;
                }
            }
            if (students != 0)
                return Math.round(100.0f * (sum / students)) / 100.0f;
        }
        return sum;
    }

    private List<EvalHelper> createAverageGradeList(List<Evaluation> evaluationArray) //gibt ein 2-Dimensionales Array mit allen Evaluationen (x) und Mittelwerten aller Fragen (y) zurück
    {
        /*int [] questionCount= new int[100];
        for(int i = 0; i< evaluationArray.size() ; i++) //geht durch jeden vote in der Evaluation des Fachs und schreibt die Anzahl der Fragen an die entsprechende Stelle des questioncount Arrays
        {
            questionCount[i]= evaluationArray.get(i).getQuestionRevision().getQuestions().size();
        }*/
        List<EvalHelper> evalHelperList = new ArrayList<>();

        //Map<String, Float> averageGrade = new HashMap<>();
        for (Evaluation eva : evaluationArray) {
            HashMap<Question, EvalScQuestion> evalQuestions = new HashMap<>();
            for (Vote vote : eva.getVotes()) {
                for (SingleChoiceAnswer singleChoiceAnswer : vote.getSingleChoiceAnswers()) {
                    EvalScQuestion evalQuestion;
                    if (evalQuestions.containsKey(singleChoiceAnswer.getQuestion())) {
                        evalQuestion = evalQuestions.get(singleChoiceAnswer.getQuestion());
                    } else {
                        evalQuestion = new EvalScQuestion();
                    }
                    evalQuestion.getGrades().add(singleChoiceAnswer.getChoice().getGrade());
                    evalQuestion.setMw(avg(evalQuestion.getGrades()));
                    evalQuestions.put(singleChoiceAnswer.getQuestion(), evalQuestion);
                }
            }
            evalHelperList.add(new EvalHelper(eva, evalQuestions));
/*
            for (Vote vote : eva.getVotes()) {
                float grade = 0;
                int studentsVoted = 0;
                for (SingleChoiceAnswer scAnswer : vote.getSingleChoiceAnswers()) {
                    if (scAnswer.getChoice().getGrade() != 0) {
                        grade += scAnswer.getChoice().getGrade();
                        studentsVoted++;
                    }
                }
                averageGrade.put(eva.getUid(), (Math.round((grade/studentsVoted) * 100.0f) / 100.0f));
            }*/
        }
        /*
        for (int i = 0; i < evaluationArray.size(); i++) // zählt evas durch
        {
            for(int j = 0; j < questionCount[i]; j++) //zählt anzahl der fragen der aktuellen eva durch
            {
                int studentsVotedQuestion = 0;
                float Grade = 0;
                if (evaluationArray.get(i).getVotes().size() != 0) {
                    for (int v = 0; v < evaluationArray.get(i).getVotes().size(); v++) //zählt votes durch
                    {
                        //FIXME: Bug
                        try {
                            if (evaluationArray.get(i).getVotes().get(v).getSingleChoiceAnswers().get(j).getChoice().getGrade() != 0) //holt grade der aktuellen frage und eva und addiert grade und student++ wenn grade!=0
                            {
                                Grade += evaluationArray.get(i).getVotes().get(v).getSingleChoiceAnswers().get(j).getChoice().getGrade();
                                studentsVotedQuestion++;
                            }
                        } catch (Exception e) {
                            log.error(e.getMessage());
                        }
                    }
                }
                averageGradeList[i][j] = (Math.round((Grade/studentsVotedQuestion) * 100.0f) / 100.0f);
            }
        }
        */

        return evalHelperList;
    }

    private Boolean [][][]createPictureCommentList(List<Evaluation> evaluationArray) //gibt ein 2-Dimensionales Array mit allen Evaluationen (x) und booleans ob textfragen beantwortet wurden (y) zurück
    {
        int [] questionCount= new int[100];
        for(int i = 0; i< evaluationArray.size() ; i++) //geht durch jeden vote in der Evaluation des Fachs und schreibt die Anzahl der Textfragen an die entsprechende Stelle des questioncount Arrays
        {
            questionCount[i]= evaluationArray.get(i).getQuestionRevision().getQuestions().size();
        }

        Boolean [][][] pictureCommentList = new Boolean[evaluationArray.size()][1000][100];
        for (int i = 0; i < evaluationArray.size(); i++) // zählt evas durch
        {
            for(int j = 0; j < questionCount[i]; j++) //zählt Anzahl der Textfragen der aktuellen Evaluation durch
            {
                if (evaluationArray.get(i).getVotes().size() != 0) {
                    for (int v = 0; v < evaluationArray.get(i).getVotes().size(); v++) //zählt votes durch und guckt ob eine zip mit der voteID, ergo ein Bild Existiert
                    {
                        pictureCommentList[i][v][j] = evaluationService.imageExists(evaluationArray.get(i).getUid(), v+1);
                    }
                }
            }
        }
        return pictureCommentList;
    }
}
