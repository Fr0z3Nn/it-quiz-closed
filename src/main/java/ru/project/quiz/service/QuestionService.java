package ru.project.quiz.service;

import ru.project.quiz.domain.dto.QuestionDTO;

public interface QuestionService {
    QuestionDTO getRandomQuestion();

    void saveQuestion(QuestionDTO questionDTO);

    void deleteQuestion(long id);

    void editQuestion(QuestionDTO questionDTO, long id);
}
