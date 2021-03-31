package ru.project.quiz.mapper.quiz;

import org.mapstruct.Mapper;
import ru.project.quiz.domain.dto.quiz.QuestionDTO;
import ru.project.quiz.domain.entity.quiz.Question;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    Question questionFromQuestionDTO(QuestionDTO questionDTO);

    QuestionDTO questionDTOFromQuestion(Question question);
}
