package ru.project.quiz.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.project.quiz.domain.dto.QuestionDTO;
import ru.project.quiz.domain.entity.Question;
import ru.project.quiz.domain.enums.CategoryType;
import ru.project.quiz.domain.enums.DifficultyType;
import ru.project.quiz.handler.response.Response;
import ru.project.quiz.repository.QuestionRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@SpringBootTest
@AutoConfigureMockMvc
class QuestionControllerTest {

    private final ObjectMapper om = new ObjectMapper();
    @MockBean
    private QuestionRepository questionRepository;
    @Autowired
    private MockMvc mockMvc;

    private Question question;

    @BeforeEach
    public void setUp() {
        question = Question.builder()
                .id(1)
                .categoryType(CategoryType.CORE)
                .difficultyType(DifficultyType.EASY)
                .description("description")
                .imageUrl("http://site.com")
                .name("First Question")
                .build();
    }

    @Test
    @Disabled
    void getRandomQuestion() throws Exception {
        Mockito.when(questionRepository.getRandomQuestion())
                .thenReturn(Optional.of(question));
        MvcResult request = mockMvc.perform(get("/api/question/random"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        String result = request.getResponse().getContentAsString();
        QuestionDTO questionResponse = om.readValue(result, QuestionDTO.class);
        assertAll(
                () -> assertEquals(questionResponse.getCategoryType(), question.getCategoryType()),
                () -> assertEquals(questionResponse.getName(), question.getName()),
                () -> assertEquals(questionResponse.getImageUrl(), question.getImageUrl()),
                () -> assertEquals(questionResponse.getDifficultyType(), question.getDifficultyType()),
                () -> assertEquals(questionResponse.getId(), question.getId()),
                () -> assertEquals(questionResponse.getDescription(), question.getDescription())
        );
    }

    @Test
    @Disabled
    public void getRandomQuestionWhenNullFromRepository() throws Exception {
        Mockito.when(questionRepository.getRandomQuestion())
                .thenReturn(Optional.ofNullable(null));
        MvcResult request = mockMvc.perform(get("/api/question/random"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        String result = request.getResponse().getContentAsString();
        Response response = om.readValue(result, Response.class);
        assertEquals(response.getMessage(), "Question list is empty");
    }

    @Test
    @Disabled
    void addQuestion() throws Exception {
        Mockito.when(questionRepository.save(question)).thenReturn(question);
        String jsonItem = om.writeValueAsString(question);
        MvcResult result = mockMvc
                .perform(
                        post("/api/question/add")
                                .content(jsonItem)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String resultContent = result.getResponse().getContentAsString();
        Response response = om.readValue(resultContent, Response.class);
        assertEquals("Question is added", response.getMessage());
    }

    @Test
    @Disabled
    void addQuestionWhenIsExist() throws Exception {
        Example<Question> example = Example.of(question, ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withIgnoreCase("name", "description", "imageUrl"));
        Mockito.when(questionRepository.exists(example)).thenReturn(true);
        String jsonItem = om.writeValueAsString(question);
        MvcResult result = mockMvc
                .perform(
                        post("/api/question/add")
                                .content(jsonItem)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
        String resultContent = result.getResponse().getContentAsString();
        Response response = om.readValue(resultContent, Response.class);
        assertEquals("Question is exist", response.getMessage());
    }

    @Test
    @Disabled
    void addBadQuestionWithoutSomeFields() throws Exception {
        question.setName(null);
        String jsonItem = om.writeValueAsString(question);
        MvcResult result = mockMvc
                .perform(
                        post("/api/question/add")
                                .content(jsonItem)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    @Disabled
    void deleteQuestion() throws Exception {
        long id = question.getId();
        Mockito.doReturn(Optional.of(question)).when(questionRepository).findById(id);
        Mockito.doNothing().when(questionRepository).deleteById(id);
        MvcResult result = mockMvc
                .perform(
                        post("/api/question/delete/{id}", id))
                .andExpect(status().isOk())
                .andReturn();
        String resultContent = result.getResponse().getContentAsString();
        Response response = om.readValue(resultContent, Response.class);
        assertEquals("Question has been deleted", response.getMessage());
    }

    @Test
    @Disabled
    void deleteQuestionNotFound() throws Exception {
        long id = question.getId();
        Mockito.doReturn(Optional.empty()).when(questionRepository).findById(id);
        Mockito.doNothing().when(questionRepository).deleteById(id);
        MvcResult result = mockMvc
                .perform(
                        post("/api/question/delete/{id}", id))
                .andExpect(status().isBadRequest())
                .andReturn();
        String resultContent = result.getResponse().getContentAsString();
        Response response = om.readValue(resultContent, Response.class);
        assertEquals("Question not found with id: " + id, response.getMessage());
    }

    @Test
    @Disabled
    void editQuestion() throws Exception {
        long id = question.getId();
        Question editedQuestion = question;
        editedQuestion.setName("someName");
        Mockito.doReturn(Optional.of(question)).when(questionRepository).findById(id);
        Mockito.when(questionRepository.save(editedQuestion)).thenReturn(editedQuestion);
        String jsonItem = om.writeValueAsString(editedQuestion);
        MvcResult result = mockMvc
                .perform(
                        post("/api/question/edit/{id}", id)
                                .content(jsonItem)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String resultContent = result.getResponse().getContentAsString();
        Response response = om.readValue(resultContent, Response.class);
        assertEquals("Question has been edited", response.getMessage());
    }

    @Test
    @Disabled
    void editQuestionNotFound() throws Exception {
        long id = question.getId();
        Question editedQuestion = question;
        editedQuestion.setName("someName");
        Mockito.doReturn(Optional.empty()).when(questionRepository).findById(id);
        Mockito.when(questionRepository.save(editedQuestion)).thenReturn(null);
        String jsonItem = om.writeValueAsString(editedQuestion);
        MvcResult result = mockMvc
                .perform(
                        post("/api/question/edit/{id}", id)
                                .content(jsonItem)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
        String resultContent = result.getResponse().getContentAsString();
        Response response = om.readValue(resultContent, Response.class);
        assertEquals("Question not found", response.getMessage());
    }
}