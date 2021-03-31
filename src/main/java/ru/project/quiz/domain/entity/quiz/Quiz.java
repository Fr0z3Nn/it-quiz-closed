package ru.project.quiz.domain.entity.quiz;

import lombok.*;
import ru.project.quiz.domain.entity.ituser.ITUser;

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

    @Column(name = "quiz_name")
    private String name;

    //who create quiz
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private ITUser userId;

    @OneToMany(mappedBy = "quiz")
    private List<QuestionQuiz> questions;

}
