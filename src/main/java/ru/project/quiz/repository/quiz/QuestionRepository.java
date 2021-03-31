package ru.project.quiz.repository.quiz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.project.quiz.domain.entity.quiz.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question,Long> {
    @Override
    Optional<Question> findById(Long id);
    @Query(value = "SELECT * FROM Questions ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Optional<Question> getRandomQuestion();
    @Query(value = "SELECT * FROM Questions ORDER BY RANDOM() LIMIT :limit", nativeQuery = true)
    List<Question> getListOfRandomQuestions(@Param("limit") int limit);
}
