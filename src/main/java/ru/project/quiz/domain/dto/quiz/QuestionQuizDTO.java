package ru.project.quiz.domain.dto.quiz;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.project.quiz.domain.entity.quiz.Question;
import ru.project.quiz.domain.entity.quiz.Quiz;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionQuizDTO {

    long id;

    private QuestionDTO question;

}
