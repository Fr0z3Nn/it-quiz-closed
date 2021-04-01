package ru.project.quiz.handler;

import org.springframework.data.repository.query.QueryCreationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.project.quiz.handler.exception.*;
import ru.project.quiz.handler.response.Response;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    public GlobalExceptionHandler() {
        super();
    }

    @ExceptionHandler({
            SampleExistException.class,
            SampleNotFoundException.class,
            BadNumberOfQuestionsException.class,
            QuestionIsExistException.class,
            QuestionNotFoundException.class,
            IncorrectInputUserException.class,
            ConstraintViolationException.class,
            QuestionCreationException.class,
            QuizNotFoundException.class})
    public ResponseEntity<Response> handleException(RuntimeException e) {
        return new ResponseEntity<>(new Response(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
