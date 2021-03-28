package ru.project.quiz.mapper;

import org.mapstruct.Mapper;
import ru.project.quiz.domain.dto.QuestionDTO;
import ru.project.quiz.domain.entity.Question;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    Question questionFromQuestionDTO(QuestionDTO questionDTO);

    QuestionDTO questionDTOFromQuestion(Question question);
}
