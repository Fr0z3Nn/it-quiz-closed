package ru.project.quiz.repository.quiz;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.project.quiz.domain.entity.quiz.Quiz;

public interface QuizRepository extends JpaRepository<Quiz,Long> {
}
