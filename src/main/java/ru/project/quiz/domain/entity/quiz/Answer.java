package ru.project.quiz.domain.entity.quiz;

import ru.project.quiz.domain.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "answers")
public class Answer extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "is_correct")
    private boolean isCorrectAnswer;

    @ManyToOne
    private Question question;

    public Answer(String name, boolean isCorrectAnswer, Question question) {
        this.name = name;
        this.isCorrectAnswer = isCorrectAnswer;
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return isCorrectAnswer == answer.isCorrectAnswer && Objects.equals(name, answer.name) && Objects.equals(question, answer.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, isCorrectAnswer, question);
    }

    public Answer() {
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

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
