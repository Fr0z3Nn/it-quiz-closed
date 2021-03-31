package ru.project.quiz.domain.entity.quiz;

import lombok.*;
import ru.project.quiz.domain.enums.question.CategoryType;
import ru.project.quiz.domain.enums.question.DifficultyType;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;

    @Column(name = "name")
    String name;

    @Column(name = "description")
    String description;

    @Column(name = "image_url")
    String imageUrl;

    @Column(name = "difficulty_type")
    @Enumerated(EnumType.STRING)
    DifficultyType difficultyType;

    @Column(name = "category_type")
    CategoryType categoryType;

    @OneToMany(mappedBy = "question")
    private List<Answer> answers;

}
