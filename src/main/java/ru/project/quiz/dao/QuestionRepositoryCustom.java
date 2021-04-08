package ru.project.quiz.dao;

import ru.project.quiz.domain.entity.quiz.Question;

import java.util.Optional;

public interface QuestionRepositoryCustom {
    Optional<Question> getRandomQuestion();
}
