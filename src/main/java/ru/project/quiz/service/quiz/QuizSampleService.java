package ru.project.quiz.service.quiz;

import ru.project.quiz.domain.dto.quiz.QuizSampleDTO;

public interface QuizSampleService {
    void saveSample(QuizSampleDTO quizSampleDTO);

    void editSample(QuizSampleDTO quizSampleDTO, long id);

    void deleteSample(long id);
}
