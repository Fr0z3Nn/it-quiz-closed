package ru.project.quiz.repository.quiz;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.project.quiz.domain.entity.quiz.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
