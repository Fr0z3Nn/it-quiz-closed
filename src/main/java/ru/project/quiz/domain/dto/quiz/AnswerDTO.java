package ru.project.quiz.domain.dto.quiz;

import io.swagger.v3.oas.annotations.media.Schema;

public class AnswerDTO {

    @Schema(description = "ID", accessMode = Schema.AccessMode.READ_ONLY)
    private long id;

    private String name;

    private boolean isCorrectAnswer;

    public AnswerDTO(String name, boolean isCorrectAnswer) {
        this.name = name;
        this.isCorrectAnswer = isCorrectAnswer;
    }

    public AnswerDTO() {
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

    public boolean isCorrectAnswer() {
        return isCorrectAnswer;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        isCorrectAnswer = correctAnswer;
    }
}
