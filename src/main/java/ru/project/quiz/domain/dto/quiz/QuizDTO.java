package ru.project.quiz.domain.dto.quiz;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.project.quiz.domain.dto.ituser.ITUserDTO;
import ru.project.quiz.domain.entity.quiz.QuizSample;
import ru.project.quiz.domain.enums.question.QuizStatus;

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

   private ITUserDTO itUser;

   private QuizStatus quizStatus;

   private Long score;
}
