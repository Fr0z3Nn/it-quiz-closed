package ru.project.quiz.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class QuestionControllerTest {

    private final ObjectMapper om = new ObjectMapper();
    @Mock
    private QuestionRepository questionRepository;
    @Autowired
    private MockMvc mockMvc;

    @BeforeTestClass
    public void setUp() {
        Question question = Question.builder()
                .id(1)
                .category(Category.CORE)
                .difficulty(Difficulty.EASY)
                .imageUrl("http://site.com")
                .name("Первый вопрос")
                .build();
        Mockito.doReturn(question).when(questionRepository.getRandomQuestion());
    }

    @Test
    void getRandomQuestion() throws Exception {
        mockMvc.perform(get("/api/question/random"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    @Test
    void addQuestion() {
    }
}