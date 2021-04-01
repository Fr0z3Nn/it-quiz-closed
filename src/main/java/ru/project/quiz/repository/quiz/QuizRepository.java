package ru.project.quiz.repository.quiz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.project.quiz.domain.entity.quiz.Quiz;

public interface QuizRepository extends JpaRepository<Quiz,Long> {
    @Query(value = "SELECT user_id FROM quiz q WHERE q.id = :quiz_search_id",nativeQuery = true)
    Long getUserIdByQuizID (@Param("quiz_search_id") Long id);
}
