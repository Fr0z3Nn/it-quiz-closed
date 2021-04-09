package ru.project.quiz.domain.entity.quiz;

import ru.project.quiz.domain.entity.BaseEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "questions_quiz")
public class QuestionQuiz extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @ManyToOne
    private Quiz quiz;

    @Column(name = "ituser_choice")
    private Long userChoice;

    public QuestionQuiz(Question question, Quiz quiz, Long userChoice) {
        this.question = question;
        this.quiz = quiz;
        this.userChoice = userChoice;
    }

    public QuestionQuiz() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionQuiz that = (QuestionQuiz) o;
        return Objects.equals(question, that.question) && Objects.equals(quiz, that.quiz) && Objects.equals(userChoice, that.userChoice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, quiz, userChoice);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Long getUserChoice() {
        return userChoice;
    }

    public void setUserChoice(Long userChoice) {
        this.userChoice = userChoice;
    }
}
