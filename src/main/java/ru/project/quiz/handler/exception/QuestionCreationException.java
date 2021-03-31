package ru.project.quiz.handler.exception;

public class QuestionCreationException extends RuntimeException{
    public QuestionCreationException(String message) {
        super(message);
    }
}
