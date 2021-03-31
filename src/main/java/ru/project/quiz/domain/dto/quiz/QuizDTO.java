package ru.project.quiz.domain.dto.quiz;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.project.quiz.domain.dto.ituser.ITUserDTO;
import ru.project.quiz.domain.entity.ituser.ITUser;
import ru.project.quiz.domain.entity.quiz.Question;
import ru.project.quiz.domain.entity.quiz.QuestionQuiz;
import ru.project.quiz.domain.entity.quiz.Quiz;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizDTO {

   private Long id;

   private String description;

   private String name;

   private List<QuestionQuizDTO> questions;

}
