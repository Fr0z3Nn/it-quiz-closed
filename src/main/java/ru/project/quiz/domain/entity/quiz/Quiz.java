package ru.project.quiz.domain.entity.quiz;

import ru.project.quiz.domain.entity.ituser.ITUser;
import ru.project.quiz.domain.enums.question.QuizStatus;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "quiz")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "quiz_sample_name")
    private QuizSample quizSample;

    //who create quiz
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private ITUser itUser;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "quiz_id")
    private List<QuestionQuiz> questions;

    @Column(name = "quiz_status")
    @Enumerated(EnumType.STRING)
    private QuizStatus quizStatus;

    @Column(name = "score")
    private Long score;

    public Quiz(QuizSample quizSample, ITUser itUser, List<QuestionQuiz> questions, QuizStatus quizStatus, Long score) {
        this.quizSample = quizSample;
        this.itUser = itUser;
        this.questions = questions;
        this.quizStatus = quizStatus;
        this.score = score;
    }

    public Quiz() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quiz quiz = (Quiz) o;
        return Objects.equals(quizSample, quiz.quizSample) && Objects.equals(itUser, quiz.itUser) && Objects.equals(questions, quiz.questions) && quizStatus == quiz.quizStatus && Objects.equals(score, quiz.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quizSample, itUser, questions, quizStatus, score);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuizSample getQuizSample() {
        return quizSample;
    }

    public void setQuizSample(QuizSample quizSample) {
        this.quizSample = quizSample;
    }

    public ITUser getItUser() {
        return itUser;
    }

    public void setItUser(ITUser itUser) {
        this.itUser = itUser;
    }

    public List<QuestionQuiz> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionQuiz> questions) {
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
