package ru.project.quiz.mapper.quiz;

import org.mapstruct.Mapper;
import ru.project.quiz.domain.dto.quiz.QuizSampleDTO;
import ru.project.quiz.domain.entity.quiz.QuizSample;

@Mapper(componentModel = "spring")
public interface QuizSampleMapper {
    QuizSample quizSampleFromQuizSampleDto(QuizSampleDTO quizSampleDTO);
}
