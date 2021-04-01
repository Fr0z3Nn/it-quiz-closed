package ru.project.quiz.repository.quiz;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.project.quiz.domain.entity.quiz.QuizSample;

public interface QuizSampleRepository extends JpaRepository<QuizSample, Long> {
}
