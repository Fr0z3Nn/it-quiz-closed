package ru.project.quiz.domain.entity.quiz;

import lombok.*;
import ru.project.quiz.domain.entity.ituser.ITUser;
import ru.project.quiz.domain.enums.question.DifficultyType;
import ru.project.quiz.domain.enums.question.QuizStatus;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
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

    @OneToMany(mappedBy = "quiz")
    private List<QuestionQuiz> questions;

    @Column(name = "quiz_status")
    @Enumerated(EnumType.STRING)
    private QuizStatus quizStatus;

    @Column(name = "score")
    private Long score;
}
