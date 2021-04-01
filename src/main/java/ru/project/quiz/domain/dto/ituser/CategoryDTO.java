package ru.project.quiz.domain.dto.ituser;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.project.quiz.domain.enums.question.CategoryType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Сущность категории")
public class CategoryDTO {
    private long id;

    private CategoryType category;
}
