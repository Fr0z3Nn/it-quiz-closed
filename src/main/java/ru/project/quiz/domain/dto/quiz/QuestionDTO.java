package ru.project.quiz.domain.dto.quiz;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.project.quiz.domain.enums.question.CategoryType;
import ru.project.quiz.domain.enums.question.DifficultyType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Schema(description = "Сущность вопроса")
public class QuestionDTO {
    @Schema(description = "ID", accessMode = Schema.AccessMode.READ_WRITE)
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

    @NotNull
    List<AnswerDTO> answers;

    public QuestionDTO(String name, String description, String imageUrl, DifficultyType difficultyType, CategoryType categoryType, List<AnswerDTO> answers) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.difficultyType = difficultyType;
        this.categoryType = categoryType;
        this.answers = answers;
    }

    public QuestionDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public DifficultyType getDifficultyType() {
        return difficultyType;
    }

    public void setDifficultyType(DifficultyType difficultyType) {
        this.difficultyType = difficultyType;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
    }

    public List<AnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDTO> answers) {
        this.answers = answers;
    }
}
