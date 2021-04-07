package ru.project.quiz.domain.dto.quiz;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.project.quiz.domain.enums.question.CategoryType;

@Schema(description = "Сущность категории")
public class CategoryDTO {
    private long id;

    private CategoryType category;

    public CategoryDTO(CategoryType category) {
        this.category = category;
    }

    public CategoryDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CategoryType getCategory() {
        return category;
    }

    public void setCategory(CategoryType category) {
        this.category = category;
    }
}
