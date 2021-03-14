package ru.project.quiz.service;

import ru.project.quiz.dto.QuestionDTO;
import ru.project.quiz.entity.Question;

public interface QuestionService {
    QuestionDTO getRandomQuestion();
    void saveQuestion(QuestionDTO questionDTO);
}
