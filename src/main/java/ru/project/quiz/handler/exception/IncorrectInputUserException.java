package ru.project.quiz.handler.exception;

public class IncorrectInputUserException extends RuntimeException{
    public IncorrectInputUserException(String message) {
        super(message);
    }
}
