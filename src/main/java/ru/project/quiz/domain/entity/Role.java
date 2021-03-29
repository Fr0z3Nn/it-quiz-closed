package ru.project.quiz.domain.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import ru.project.quiz.domain.enums.RoleType;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private RoleType role;

    @ManyToMany(mappedBy = "roles")
    private Collection<ITUser> ITUsers;

    @Override
    public String getAuthority() {
        return role.name();
    }
}
