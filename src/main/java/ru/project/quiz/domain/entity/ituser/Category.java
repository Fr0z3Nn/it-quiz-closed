package ru.project.quiz.domain.entity.ituser;

import lombok.*;
import ru.project.quiz.domain.entity.quiz.QuizSample;
import ru.project.quiz.domain.enums.question.CategoryType;

import javax.persistence.*;

@Entity
@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private CategoryType category;

    @ManyToOne

    private QuizSample quizSample;
}
