package ru.project.quiz.domain.dto.quiz;

import java.util.List;

public class QuizSampleDTO {

    long id;

    String name;

    List<CategoryDTO> categories;

    public QuizSampleDTO(String name, List<CategoryDTO> categories) {
        this.name = name;
        this.categories = categories;
    }

    public QuizSampleDTO() {
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

    public List<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDTO> categories) {
        this.categories = categories;
    }
}
