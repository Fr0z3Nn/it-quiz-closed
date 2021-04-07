package ru.project.quiz.controller.quiz;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.project.quiz.domain.dto.quiz.QuizDTO;
import ru.project.quiz.service.quiz.QuizService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {
    private final QuizService quizService;
    private final static String CREATE_QUIZ = "/create";
    private final static String FINISH_QUIZ = "/finish";

    @GetMapping(CREATE_QUIZ)
    public ResponseEntity<QuizDTO> getQuiz(HttpServletRequest httpServletRequest, @RequestParam int numberOfQuestions,
                                           @RequestParam String quizName) {
        QuizDTO quiz = quizService.createQuiz(numberOfQuestions, quizName);
        return new ResponseEntity<>(quiz, HttpStatus.OK);
    }


    @PostMapping(FINISH_QUIZ)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<QuizDTO> finishQuiz(@RequestBody QuizDTO quizDTO) {
        return new ResponseEntity<>(quizService.finishQuiz(quizDTO), HttpStatus.OK);
    }

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }
}
