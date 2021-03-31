package ru.project.quiz.controller.quiz;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.project.quiz.domain.dto.quiz.QuizDTO;
import ru.project.quiz.service.quiz.QuizService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class QuizController {
    private final QuizService quizService;
    private final static String CREATE_QUIZ = "/quiz/create";

    @GetMapping(CREATE_QUIZ)
    public ResponseEntity<QuizDTO> getQuiz( HttpServletRequest httpServletRequest){
        QuizDTO quiz = quizService.createQuiz();
        return new ResponseEntity<>(quiz, HttpStatus.OK);
    }
}
