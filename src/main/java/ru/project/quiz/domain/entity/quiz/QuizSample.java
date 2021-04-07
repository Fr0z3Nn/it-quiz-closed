package ru.project.quiz.domain.entity.quiz;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "quiz_sample")
public class QuizSample {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;


    @OneToMany(mappedBy = "quizSample")
    private List<Quiz> quizes;

    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "quiz_sample_id")
    private List<Category> categories;

    public QuizSample(List<Quiz> quizes, String name, List<Category> categories) {
        this.quizes = quizes;
        this.name = name;
        this.categories = categories;
    }

    public QuizSample() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Quiz> getQuizes() {
        return quizes;
    }

    public void setQuizes(List<Quiz> quizes) {
        this.quizes = quizes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
