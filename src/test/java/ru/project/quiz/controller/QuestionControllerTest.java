package ru.project.quiz.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.project.quiz.dto.QuestionDTO;
import ru.project.quiz.entity.Question;
import ru.project.quiz.enums.Category;
import ru.project.quiz.enums.Difficulty;
import ru.project.quiz.repository.QuestionRepository;
import ru.project.quiz.service.QuestionService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    private Question question = Question.builder()
            .id(1)
            .category(Category.CORE)
            .difficulty(Difficulty.EASY)
            .description("description")
            .imageUrl("http://site.com")
            .name("First Question")
            .build();

    {
        Mockito.when(questionRepository.getRandomQuestion()).thenReturn(Optional.of(question));
    }
    @BeforeEach
    public void setUp() {
    }

    @Test
    void getRandomQuestion() throws Exception {
        MvcResult request = mockMvc.perform(get("/api/question/random"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        String result = request.getResponse().getContentAsString();
        QuestionDTO questionResponse = om.readValue(result, QuestionDTO.class);
        assertAll(
                () -> assertEquals(questionResponse.getCategory(),question.getCategory()),
                () -> assertEquals(questionResponse.getName(),question.getName()),
                () -> assertEquals(questionResponse.getImageUrl(),question.getImageUrl()),
                () -> assertEquals(questionResponse.getDifficulty(),question.getDifficulty()),
                () -> assertEquals(questionResponse.getId(),question.getId()),
                () -> assertEquals(questionResponse.getDescription(),question.getDescription())
        );
    }

    /*@Test
    void addQuestion() {
    }*/
}