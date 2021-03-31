package ru.project.quiz.mapper.quiz;

import org.mapstruct.Mapper;
import ru.project.quiz.domain.dto.quiz.AnswerDTO;
import ru.project.quiz.domain.entity.quiz.Answer;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    Answer answerFromAnswerDTO(AnswerDTO answerDTO);

    AnswerDTO answerDTOFromAnswer(Answer answer);

    List<Answer> listAnswersFromListAnswersDTO(List<AnswerDTO> answerDTOS);
}
