package ru.project.quiz.controller.quiz;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.project.quiz.domain.dto.quiz.QuizSampleDTO;
import ru.project.quiz.handler.response.Response;
import ru.project.quiz.service.quiz.QuizSampleService;

import javax.validation.Valid;

@RestController
@RequestMapping("/quiz_sample")
@Tag(name = "Контроллер сэмплов")
public class QuizSampleController {
    public final QuizSampleService quizSampleService;

    private static final String ADD_SAMPLE= "/admin/add";
    private static final String DELETE_SAMPLE = "/admin/delete";
    private static final String EDIT_SAMPLE = "/admin/edit";

    @Operation(summary = "Добавление сэмпла")
    @PostMapping(ADD_SAMPLE)
    public ResponseEntity<Response> addSample(@Valid @RequestBody QuizSampleDTO quizSampleDTO) {
        quizSampleService.saveSample(quizSampleDTO);
        return new ResponseEntity<>(new Response("Сэмпл добавлен"), HttpStatus.OK);
    }

    @Operation(summary = "Редактирование сэмпла")
    @PostMapping(EDIT_SAMPLE)
    public ResponseEntity<Response> editQuestion(@Valid @RequestBody QuizSampleDTO quizSampleDTO, @RequestParam long id) {
        quizSampleService.editSample(quizSampleDTO, id);
        return new ResponseEntity<>(new Response("QuizSample has been edited"), HttpStatus.OK);
    }

    @Operation(summary = "Удаление сэмпла")
    @PostMapping(DELETE_SAMPLE)
    public ResponseEntity<Response> deleteQuestion(@RequestParam long id) {
        quizSampleService.deleteSample(id);
        return new ResponseEntity<>(new Response("QuizSample has been deleted"), HttpStatus.OK);
    }

    public QuizSampleController(QuizSampleService quizSampleService) {
        this.quizSampleService = quizSampleService;
    }
}
