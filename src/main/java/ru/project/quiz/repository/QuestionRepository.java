package ru.project.quiz.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.project.quiz.entity.Question;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question,Long> {
    @Override
    Optional<Question> findById(Long id);
    @Query(value = "SELECT * FROM Question ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<Question> getRandomQuestion();

}
