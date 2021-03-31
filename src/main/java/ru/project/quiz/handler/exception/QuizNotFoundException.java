package ru.project.quiz.handler.exception;

public class QuizNotFoundException extends RuntimeException{
    public QuizNotFoundException(String message) {
        super(message);
    }
}
