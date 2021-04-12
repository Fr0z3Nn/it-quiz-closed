package ru.project.quiz.controller.quiz;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.project.quiz.domain.dto.quiz.QuizDTO;
import ru.project.quiz.service.quiz.QuizService;

import javax.servlet.http.HttpServletRequest;

@RestController
@Tag(name = "Контроллер тестов")
@RequestMapping("/api/quiz")
public class QuizController {
    private final QuizService quizService;
    private final static String CREATE_QUIZ = "/create";
    private final static String FINISH_QUIZ = "/finish";

    @Operation(summary = "Создание квиза (теста)", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping(CREATE_QUIZ)
    public ResponseEntity<QuizDTO> getQuiz(HttpServletRequest httpServletRequest, @RequestParam int numberOfQuestions,
                                           @RequestParam String quizName) {
        QuizDTO quiz = quizService.createQuiz(numberOfQuestions, quizName);
        return new ResponseEntity<>(quiz, HttpStatus.OK);
    }


    @Operation(summary = "Завершение квиза(теста)", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping(FINISH_QUIZ)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<QuizDTO> finishQuiz(@RequestBody QuizDTO quizDTO) {
        return new ResponseEntity<>(quizService.finishQuiz(quizDTO), HttpStatus.OK);
    }

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }
}
