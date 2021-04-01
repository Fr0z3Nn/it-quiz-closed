package ru.project.quiz.handler.exception;

public class BadNumberOfQuestionsException extends RuntimeException{
    public BadNumberOfQuestionsException(String message) {
        super(message);
    }
}
