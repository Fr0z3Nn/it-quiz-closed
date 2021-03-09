package ru.project.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.project.quiz.enums.Category;
import ru.project.quiz.enums.Difficulty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {
    long id;
    String name;
    String imageUrl;
    Difficulty difficulty;
    Category category;
}
