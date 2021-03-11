package ru.project.quiz.handler.exception;

public class QuestionIsExistException extends RuntimeException{
    public QuestionIsExistException(String message) {
        super(message);
    }
}
