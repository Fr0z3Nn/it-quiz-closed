package ru.project.quiz.repository.quiz;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.project.quiz.domain.entity.quiz.QuizSample;

import java.util.Optional;

public interface QuizSampleRepository extends JpaRepository<QuizSample, Long> {
    Optional<QuizSample> findByName(String name);
}
