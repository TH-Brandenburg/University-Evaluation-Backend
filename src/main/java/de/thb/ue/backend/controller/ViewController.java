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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.thb.ue.backend.exception.AggregatedAnswerException;
import de.thb.ue.backend.exception.DBEntryDoesNotExistException;
import de.thb.ue.backend.exception.EvaluationException;
import de.thb.ue.backend.exception.ParticipantException;
import de.thb.ue.backend.model.Choice;
import de.thb.ue.backend.model.Evaluation;
import de.thb.ue.backend.model.Question;
import de.thb.ue.backend.model.QuestionRevision;
import de.thb.ue.backend.model.SingleChoiceQuestion;
import de.thb.ue.backend.model.TextQuestion;
import de.thb.ue.backend.model.Tutor;
import de.thb.ue.backend.service.interfaces.IEvaluationService;
import de.thb.ue.backend.service.interfaces.IQuestionsService;
import de.thb.ue.backend.service.interfaces.ISubjectService;
import de.thb.ue.backend.service.interfaces.ITutorService;
import de.thb.ue.backend.util.QuestionType;
import de.thb.ue.backend.util.SemesterType;
import de.thb.ue.dto.util.Department;
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
		List<Tutor> tutors = tutorService.getByFamilyName(user.getUsername());
		List<Evaluation> evaluations;

		if (tutors != null && !tutors.isEmpty()) {

			evaluations = tutors.get(0).getEvaluations().stream().filter(evaluation -> !evaluation.getClosed()).collect(Collectors.toList());

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
		List<Evaluation> evaluations = tutorService.getByFamilyName(user.getUsername()).get(0).getEvaluations().stream().filter(Evaluation::getClosed)
				.collect(Collectors.toList());
		model.addAttribute("evaluations", evaluations);
		return "archive";
	}

	@RequestMapping(value = "/new-evaluation", method = RequestMethod.GET)
	String setup(Model model) {
		LdapUserDetails user = (LdapUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// TODo
		model.addAttribute("tutors", new ArrayList<Tutor>() {
			{
				add(new Tutor("", user.getUsername(), Department.COMPUTER_SCIENCE_MEDIA, null));
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
		int questionCount = Integer.parseInt(allRequestParams.get("question-count"));
		List<Question> questions = new ArrayList<Question>();
		List<Choice> questionRevisionChoices = new ArrayList<Choice>();
		QuestionRevision questionnaire = new QuestionRevision();

		String questionnaireName = allRequestParams.get("name");
		List<String> revisionNames = questionsService.getRevisionNames();
		for (String revisionName : revisionNames) {
			if (revisionName.equals(questionnaireName)) {
				return "newQuestionnaire";
			}
		}
		questionnaire.setName(questionnaireName);
		boolean textQuestionsFirst = false;
		String textQuestionsFirstString = allRequestParams.get("text-questions-first");
		if (textQuestionsFirstString != null) {
			textQuestionsFirst = true;
		}
		questionnaire.setTextQuestionsFirst(textQuestionsFirst);

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

				textQuestion.setType(QuestionType.TextQuestion);
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

				singleChoiceQuestion.setType(QuestionType.SingleChoiceQuestion);
				singleChoiceQuestion.setText(text);
				singleChoiceQuestion.setChoices(choices);
				questionRevisionChoices.addAll(choices);
				questions.add(singleChoiceQuestion);
			}
		}

		questionnaire.setChoices(questionRevisionChoices);
		questionnaire.setQuestions(questions);
		int id = questionsService.saveQuestionRevision(questionnaire).getId();

		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("message", "Der Fragebogen wurde erfolgreich erstellt.");

		return "redirect:/questionnaire/" + id;
	}

	@RequestMapping(value = "/questionnaire/{id}", method = RequestMethod.GET)
	String getQuestionRevision(@PathVariable String id, Model model) {
		QuestionRevision questionnaire = questionsService.getRevisionById(Integer.parseInt(id));

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
		List<SimpleEntry<String, SingleChoiceQuestion>> singleChoiceQuestions = new ArrayList<SimpleEntry<String, SingleChoiceQuestion>>();
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
				singleChoiceQuestions.add(new SimpleEntry<String, SingleChoiceQuestion>("BestInTheMiddle", question));
			} else {
				// Choices aufsteigend sortieren und Frage der Liste von Best-First-Fragen hinzufügen
				choicesTemp.sort((Choice choice1, Choice choice2) -> Short.compare(choice1.getGrade(), choice2.getGrade()));
				singleChoiceQuestions.add(new SimpleEntry<String, SingleChoiceQuestion>("BestFirst", question));
			}
		}

		model.addAttribute("questionnaire", questionnaire);
		model.addAttribute("textQuestions", textQuestions);
		model.addAttribute("singleChoiceQuestions", singleChoiceQuestions);
		model.addAttribute("noAnswerChoices", noAnswerChoices);

		return "questionnaire";
	}

	@RequestMapping(value = "/questionnaire/{id}", method = RequestMethod.POST)
	String updateQuestionRevision(@PathVariable String id, @RequestParam Map<String, String> allRequestParams, Model model,
			RedirectAttributes redirectAttributes) {
		QuestionRevision questionnaire = questionsService.getRevisionById(Integer.parseInt(id));
		String name = allRequestParams.get("name");

		boolean evaluationExistsForQuestionRevision = evaluationService.evaluationWithQuestionRevisionExists(Integer.parseInt(id));

		if (evaluationExistsForQuestionRevision) {
			QuestionRevision newQuestionnaire = new QuestionRevision();

			List<String> revisionNames = questionsService.getRevisionNames();
			for (String revisionName : revisionNames) {
				if (revisionName.equals(name)) {
					String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
					name = name + " " + timestamp;
				}
			}

			newQuestionnaire.setName(name);

			int questionCount = Integer.parseInt(allRequestParams.get("question-count"));
			List<Question> questions = new ArrayList<Question>();
			List<Choice> questionRevisionChoices = new ArrayList<Choice>();

			boolean textQuestionsFirst = false;
			String textQuestionsFirstString = allRequestParams.get("text-questions-first");
			if (textQuestionsFirstString != null) {
				textQuestionsFirst = true;
			}
			newQuestionnaire.setTextQuestionsFirst(textQuestionsFirst);

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

					textQuestion.setType(QuestionType.TextQuestion);
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

					singleChoiceQuestion.setType(QuestionType.SingleChoiceQuestion);
					singleChoiceQuestion.setText(text);
					singleChoiceQuestion.setChoices(choices);
					questionRevisionChoices.addAll(choices);
					questions.add(singleChoiceQuestion);
				}
			}

			newQuestionnaire.setChoices(questionRevisionChoices);
			newQuestionnaire.setQuestions(questions);
			int questionnaireId = questionsService.saveQuestionRevision(newQuestionnaire).getId();
			redirectAttributes.addFlashAttribute("success", true);
			redirectAttributes.addFlashAttribute("message", "Der Fragebogen wurde erfolgreich aktualisiert.");
			return "redirect:/questionnaire/" + questionnaireId;

		} else {
			List<Choice> oldChoices = questionnaire.getChoices();
			List<Question> oldQuestions = questionnaire.getQuestions();

			int questionCount = Integer.parseInt(allRequestParams.get("question-count"));
			List<Question> questions = new ArrayList<Question>();
			List<Choice> questionRevisionChoices = new ArrayList<Choice>();

			questionnaire.setName(name);

			boolean textQuestionsFirst = false;
			String textQuestionsFirstString = allRequestParams.get("text-questions-first");
			if (textQuestionsFirstString != null) {
				textQuestionsFirst = true;
			}
			questionnaire.setTextQuestionsFirst(textQuestionsFirst);

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

					textQuestion.setType(QuestionType.TextQuestion);
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

					singleChoiceQuestion.setType(QuestionType.SingleChoiceQuestion);
					singleChoiceQuestion.setText(text);
					singleChoiceQuestion.setChoices(choices);
					questionRevisionChoices.addAll(choices);
					questions.add(singleChoiceQuestion);
				}
			}
			questionnaire.setChoices(questionRevisionChoices);
			questionnaire.setQuestions(questions);
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

	// @RequestMapping(value = "test")
	// ResponseEntity<String> test() throws UnknownHostException {
	//// LdapUserDetails user = (LdapUserDetails)
	// SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	//// Person person =personRepo.findPerson(user.getDn());
	//// return new ResponseEntity<>("CN "+ Arrays.toString(person.getCn()) +"
	// "+person.getSn(), HttpStatus.OK);
	// return new ResponseEntity<>("Your current IP address : " +
	// InetAddress.getLocalHost().getHostAddress(), HttpStatus.OK);
	// }

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
		// SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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

					textQuestion.setType(QuestionType.TextQuestion);
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

					singleChoiceQuestion.setType(QuestionType.SingleChoiceQuestion);
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