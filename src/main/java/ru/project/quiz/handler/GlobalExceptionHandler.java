package ru.project.quiz.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.project.quiz.handler.exception.IncorrectInputUserException;
import ru.project.quiz.handler.exception.QuestionIsExistException;
import ru.project.quiz.handler.exception.QuestionNotFoundException;
import ru.project.quiz.handler.response.Response;

@RestControllerAdvice
public class GlobalExceptionHandler {
    public GlobalExceptionHandler() {
        super();
    }

    @ExceptionHandler({QuestionIsExistException.class,
            QuestionNotFoundException.class,
            IncorrectInputUserException.class})
    public ResponseEntity<Response> handleException(RuntimeException e) {
        return new ResponseEntity<>(new Response(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
