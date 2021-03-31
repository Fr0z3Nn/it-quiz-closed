package ru.project.quiz.repository.quiz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.project.quiz.domain.entity.quiz.Question;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question,Long> {
    @Override
    Optional<Question> findById(Long id);
    @Query(value = "SELECT * FROM Questions ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Optional<Question> getRandomQuestion();

}
