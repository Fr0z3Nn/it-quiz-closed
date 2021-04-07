package ru.project.quiz.domain.dto.quiz;

import ru.project.quiz.domain.enums.question.QuizStatus;

import java.util.List;

public class QuizDTO {

    private Long id;

    private String description;

    private String name;

    private List<QuestionQuizDTO> questions;

    private QuizStatus quizStatus;

    private Long score;

    public QuizDTO(String description, String name, List<QuestionQuizDTO> questions, QuizStatus quizStatus, Long score) {
        this.description = description;
        this.name = name;
        this.questions = questions;
        this.quizStatus = quizStatus;
        this.score = score;
    }

    public QuizDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<QuestionQuizDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionQuizDTO> questions) {
        this.questions = questions;
    }

    public QuizStatus getQuizStatus() {
        return quizStatus;
    }

    public void setQuizStatus(QuizStatus quizStatus) {
        this.quizStatus = quizStatus;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }
}
