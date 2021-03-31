package ru.project.quiz.domain.dto.quiz;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import ru.project.quiz.domain.enums.question.CategoryType;
import ru.project.quiz.domain.enums.question.DifficultyType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Сущность вопроса")
public class QuestionDTO {
    @Schema(description = "ID", accessMode = Schema.AccessMode.READ_ONLY)
    long id;

    @NotBlank(message = "Название вопроса не должно быть пустым")
    @Schema(description = "Название")
    String name;

    @Schema(description = "Описание")
    String description;

    @Schema(description = "URL картинки")
    String imageUrl;

    @NotNull(message = "Сложность вопроса должна присутствовать")
    @Schema(description = "Сложность")
    DifficultyType difficultyType;

    @NotNull(message = "Категория вопроса должна присутствовать")
    @Schema(description = "Категория")
    CategoryType categoryType;

}
