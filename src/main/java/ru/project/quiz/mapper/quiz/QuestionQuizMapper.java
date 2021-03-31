package ru.project.quiz.mapper.quiz;

import org.mapstruct.Mapper;
import ru.project.quiz.domain.dto.quiz.QuestionQuizDTO;
import ru.project.quiz.domain.dto.quiz.QuizDTO;
import ru.project.quiz.domain.entity.quiz.QuestionQuiz;
import ru.project.quiz.domain.entity.quiz.Quiz;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionQuizMapper {
    QuestionQuiz questionQuizFromQuestionQuizDTO(QuestionQuizDTO RoleDTO);

    QuestionQuizDTO questionQuizDTOFromQuestionQuiz(QuestionQuiz Role);

    List<QuestionQuiz> listQuestionQuizFromQuestionQuizDTO(List<QuestionQuizDTO> RoleDTO);

    List<QuestionQuizDTO> listQuestionQuizDTOFromQuestionQuiz(List<QuestionQuiz> Role);
}