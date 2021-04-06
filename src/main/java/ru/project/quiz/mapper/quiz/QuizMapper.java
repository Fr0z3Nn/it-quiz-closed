package ru.project.quiz.mapper.quiz;

import org.mapstruct.Mapper;
import ru.project.quiz.domain.dto.quiz.QuizDTO;
import ru.project.quiz.domain.entity.quiz.Quiz;

@Mapper(componentModel = "spring")
public interface QuizMapper {
    Quiz quizFromQuizDTO(QuizDTO quizDTO);

    QuizDTO quizDTOFromQuiz(Quiz quiz);

}
