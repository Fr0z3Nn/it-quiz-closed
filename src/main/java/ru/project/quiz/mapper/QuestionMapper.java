package ru.project.quiz.mapper;

import org.mapstruct.Mapper;
import ru.project.quiz.dto.QuestionDTO;
import ru.project.quiz.entity.Question;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    Question questionFromQuestionDTO (QuestionDTO questionDTO);
    QuestionDTO questionDTOFromQuestion (Question question);
}
