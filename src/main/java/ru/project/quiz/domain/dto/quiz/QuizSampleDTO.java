package ru.project.quiz.domain.dto.quiz;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.project.quiz.domain.dto.ituser.CategoryDTO;
import ru.project.quiz.domain.entity.ituser.Category;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizSampleDTO {

    long id;

    List<QuizDTO> quizes;

    String name;


    List<CategoryDTO> categories;
}
