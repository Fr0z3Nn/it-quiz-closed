package ru.project.quiz.controller.quiz;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.project.quiz.domain.dto.quiz.QuestionDTO;
import ru.project.quiz.handler.response.Response;
import ru.project.quiz.service.quiz.QuestionService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/question")
@Tag(name = "Контроллер вопросов")
public class QuestionController {

    private static final String RANDOM_QUESTION = "/random";
    private static final String ADD_QUESTION = "/admin/add";
    private static final String DELETE_QUESTION = "/admin/delete";
    private static final String EDIT_QUESTION = "/admin/edit";

    private final QuestionService questionService;

    @Operation(summary = "Получение рандом вопроса", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping(RANDOM_QUESTION)
    public ResponseEntity<QuestionDTO> getQuestion() {
        QuestionDTO questionDTO = questionService.getRandomQuestion();
        System.out.println(questionService.getRandomQuestion());
        return new ResponseEntity<>(questionDTO, HttpStatus.OK);
    }

    @Operation(summary = "Добавление вопроса", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping(ADD_QUESTION)
    public ResponseEntity<Response> addQuestion(@RequestBody QuestionDTO questionDTO) {
        questionService.saveQuestion(questionDTO);
        return new ResponseEntity<>(new Response("Question is added"), HttpStatus.OK);
    }

    @Operation(summary = "Удаление вопроса", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping(DELETE_QUESTION)
    public ResponseEntity<Response> deleteQuestion(@RequestParam(name = "questionId") long id) {
        questionService.deleteQuestion(id);
        return new ResponseEntity<>(new Response("Question has been deleted"), HttpStatus.OK);
    }

    @Operation(summary = "Редактирование вопроса", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping(EDIT_QUESTION)
    public ResponseEntity<Response> editQuestion(@Valid @RequestBody QuestionDTO questionDTO) {
        questionService.editQuestion(questionDTO);
        return new ResponseEntity<>(new Response("Question has been edited"), HttpStatus.OK);
    }

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }
}
