package de.thb.ue.backend;

import de.thb.ue.backend.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import de.thb.ue.dto.AnswersDTO;
import de.thb.ue.dto.RequestDTO;
import de.thb.ue.dto.util.ChoiceDTO;
import de.thb.ue.dto.util.MultipleChoiceAnswerDTO;
import de.thb.ue.dto.util.TextAnswerDTO;
import de.thb.ue.backend.controller.RestController;
import de.thb.ue.backend.model.TextQuestion;
import de.thb.ue.backend.service.interfaces.IEvaluationService;
import de.thb.ue.backend.service.interfaces.IParticipantService;
import de.thb.ue.backend.util.DBInit;
import de.thb.ue.backend.util.DTOMapper;
import de.thb.ue.backend.util.SemesterType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UniversityEvaluationBackendApplication.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@WebAppConfiguration
public class CampusAppEvalBackendApplicationTests {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    private static final String deviceID = "testID";

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    private IEvaluationService evaluationService;

    @Autowired
    private IParticipantService participantService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream().filter(
                hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();

        Assert.assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void sendQuestions() throws Exception {

        String voteToken = startEvaluationAndGetVoteToken();

        mockMvc.perform(post(RestController.API_VERSION + "/questions")
                .content(this.json(new RequestDTO(voteToken, deviceID)))
                .contentType(contentType))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void receiveAnswers() throws Exception {
        String voteToken = startEvaluationAndGetVoteToken();
        sendQuestions(voteToken);
        sendRandomAnswers(voteToken, status().is2xxSuccessful());
    }

    @Test
    public void receiveAnswersFromAlreadyVotedParticipant() throws Exception {
        String voteToken = startEvaluationAndGetVoteToken();
        sendQuestions(voteToken);
        AnswersDTO answersDTO = sendRandomAnswers(voteToken, status().is2xxSuccessful());
        sendRandomAnswers(voteToken, status().isBadRequest());

    }

//    @Test
//    public void receiveAnswersForClosedEvaluation(){
//        //TODO Test
//    }

    @Test
    public void answerAggregationTest() throws Exception {
        String evaluationUID = evaluationService.add(1, 100, "Socher", 6, SemesterType.SUMMER, "Demo Evaluation");
        Evaluation evaluation = evaluationService.getByUID(evaluationUID);
        List<String> voteTokens = participantService.getByEvaluation(evaluation).stream().map(Participant::getVoteToken).collect(Collectors.toList());

        for (String voteToken : voteTokens) {
            sendQuestions(voteToken);
            sendRandomAnswers(voteToken, status().is2xxSuccessful());
        }

        mockMvc.perform(get("/evaluation/close?uid=" + evaluationUID)).andExpect(status().is3xxRedirection());
    }

    @Test
    public void receivedAnswersWithInvalidToken() throws Exception {
        sendRandomAnswers("wrong-token", status().isBadRequest());
    }


    private AnswersDTO getRandomAnsweredQuestions(String voteToken, String studyPath) {
        List<TextAnswerDTO> answers = new ArrayList<>();
        List<MultipleChoiceAnswerDTO> mcAnswers = new ArrayList<>();
        for (TextQuestion textQuestion : DBInit.getDemoQuestions()) {
            answers.add(new TextAnswerDTO(textQuestion.getId(), textQuestion.getText(), "Lorem ipsum dolor sit amet, consetetur sadipscing elitr aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. \n" +
                    "\n" +
                    " hendrerit in vulputate velit esse molestie consequat, vel "));
        }
        for (SingleChoiceQuestion multipleChoiceQuestion : DBInit.getDemoMCQuestions()) {
            Choice randomChoice = multipleChoiceQuestion.getChoices().get(new Random().nextInt(multipleChoiceQuestion.getChoices().size()));
            mcAnswers.add(new MultipleChoiceAnswerDTO(multipleChoiceQuestion.getText(), new ChoiceDTO(randomChoice.getText(), randomChoice.getGrade())));
        }
        return new AnswersDTO(voteToken, studyPath, answers, mcAnswers, deviceID);
    }

    private AnswersDTO sendRandomAnswers(String voteToken, ResultMatcher result) throws Exception {
        AnswersDTO answersDTO = getRandomAnsweredQuestions(voteToken, "Informatik");
        MockMultipartFile multipartFile = new MockMultipartFile("images", new FileInputStream("README.md"));

        mockMvc.perform(MockMvcRequestBuilders.fileUpload(RestController.API_VERSION + "/textAnswers")
                .file(multipartFile).param("textAnswers-dto", DTOMapper.answersDTOToString(answersDTO))
        ).andExpect(result);
        return answersDTO;
    }

    private void sendQuestions(String voteToken) throws Exception {
        mockMvc.perform(post(RestController.API_VERSION + "/questions")
                .content(this.json(new RequestDTO(voteToken, deviceID)))
                .contentType(contentType)).andExpect(status().isOk());
    }

    private String startEvaluationAndGetVoteToken() throws Exception {
        String evaluationUID = evaluationService.add(1, 10, "Socher", 6, SemesterType.SUMMER, "Demo Evaluation");
        Evaluation evaluation = evaluationService.getByUID(evaluationUID);
        return participantService.getByEvaluation(evaluation).get(0).getVoteToken();
    }

}
