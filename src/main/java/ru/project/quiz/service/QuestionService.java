package ru.project.quiz.service;

import ru.project.quiz.dto.QuestionDTO;

public interface QuestionService {
    QuestionDTO getRandomQuestion();
    void saveQuestion(QuestionDTO questionDTO);
}
