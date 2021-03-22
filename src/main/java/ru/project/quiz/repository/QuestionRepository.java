package ru.project.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.project.quiz.domain.entity.Question;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question,Long> {
    @Override
    Optional<Question> findById(Long id);
    @Query(value = "SELECT * FROM Questions ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<Question> getRandomQuestion();

}
