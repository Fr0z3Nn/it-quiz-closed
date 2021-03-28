package ru.project.quiz.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "users")
public class ITUser{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "role_id", referencedColumnName = "id"),
            @JoinColumn(name = "user_id", referencedColumnName = "id")

    })
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

}
