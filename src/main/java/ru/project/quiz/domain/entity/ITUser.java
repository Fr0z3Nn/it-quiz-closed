package ru.project.quiz.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "users")
public class ITUser{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", unique = true)
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
