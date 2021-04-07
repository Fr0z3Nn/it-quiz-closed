package ru.project.quiz.domain.dto.quiz;

public class QuestionQuizDTO {

    private long id;

    private QuestionDTO question;

    private Long userChoice;

    public QuestionQuizDTO(QuestionDTO question, Long userChoice) {
        this.question = question;
        this.userChoice = userChoice;
    }

    public QuestionQuizDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public QuestionDTO getQuestion() {
        return question;
    }

    public void setQuestion(QuestionDTO question) {
        this.question = question;
    }

    public Long getUserChoice() {
        return userChoice;
    }

    public void setUserChoice(Long userChoice) {
        this.userChoice = userChoice;
    }
}
