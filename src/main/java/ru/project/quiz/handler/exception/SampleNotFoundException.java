package ru.project.quiz.handler.exception;

public class SampleNotFoundException extends RuntimeException{
    public SampleNotFoundException(String message) {
        super(message);
    }
}
