package ru.project.quiz.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.project.quiz.domain.dto.QuestionDTO;
import ru.project.quiz.handler.response.Response;
import ru.project.quiz.service.QuestionService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/question")
@AllArgsConstructor
@Tag(name = "Контроллер вопросов")
public class QuestionController {

    private static final String RANDOM_QUESTION = "/random";
    private static final String ADD_QUESTION = "/admin/add";
    private static final String DELETE_QUESTION = "/admin/delete/{id}";
    private static final String EDIT_QUESTION = "/admin/edit/{id}";

    private final QuestionService questionService;

    @Operation(summary = "Получение рандом вопроса")
    @GetMapping(RANDOM_QUESTION)
    public ResponseEntity<QuestionDTO> getQuestion() {
        QuestionDTO questionDTO = questionService.getRandomQuestion();
        return new ResponseEntity<>(questionDTO, HttpStatus.OK);
    }

    @Operation(summary = "Добавление вопроса")
    @PostMapping(ADD_QUESTION)
    public ResponseEntity<Response> addQuestion(@Valid @RequestBody QuestionDTO questionDTO) {
        questionService.saveQuestion(questionDTO);
        return new ResponseEntity<>(new Response("Question is added"), HttpStatus.OK);
    }

    @Operation(summary = "Удаление вопроса")
    @PostMapping(DELETE_QUESTION)
    public ResponseEntity<Response> deleteQuestion(@PathVariable long id) {
        questionService.deleteQuestion(id);
        return new ResponseEntity<>(new Response("Question has been deleted"), HttpStatus.OK);
    }

    @Operation(summary = "Редактирование вопроса")
    @PostMapping(EDIT_QUESTION)
    public ResponseEntity<Response> editQuestion(@Valid @RequestBody QuestionDTO questionDTO, @PathVariable long id) {
        questionService.editQuestion(questionDTO, id);
        return new ResponseEntity<>(new Response("Question has been edited"), HttpStatus.OK);
    }
}
