package ru.project.quiz.domain.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import ru.project.quiz.domain.enums.RoleType;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private RoleType role;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;
}
