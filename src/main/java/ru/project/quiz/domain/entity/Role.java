package ru.project.quiz.domain.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import ru.project.quiz.domain.enums.RoleType;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Data
@Builder
@EqualsAndHashCode
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private RoleType role;

    @Override
    public String getAuthority() {
        return role.name();
    }

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;
}
