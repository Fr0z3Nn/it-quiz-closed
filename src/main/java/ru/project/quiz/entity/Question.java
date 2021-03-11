package ru.project.quiz.entity;

import lombok.*;
import ru.project.quiz.enums.Category;
import ru.project.quiz.enums.Difficulty;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String name;
    String description;
    String imageUrl;
    Difficulty difficulty;
    Category category;
}
