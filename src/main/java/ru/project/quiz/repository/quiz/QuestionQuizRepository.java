package ru.project.quiz.repository.quiz;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.project.quiz.domain.entity.quiz.QuestionQuiz;

public interface QuestionQuizRepository extends JpaRepository<QuestionQuiz, Long> {
}
