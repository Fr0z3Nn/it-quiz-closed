package ru.project.quiz.domain.entity.quiz;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "answers")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;

    @Column(name = "name")
    String name;

    @Column(name = "is_correct")
    boolean isCorrectAnswer;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Question question;
}
