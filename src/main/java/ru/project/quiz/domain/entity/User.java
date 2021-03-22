package ru.project.quiz.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.project.quiz.domain.enums.RoleType;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "users")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;


    @ManyToMany
    @JoinColumns({
            @JoinColumn(name = "role_id", referencedColumnName = "id"),
            @JoinColumn(name = "user_id", referencedColumnName = "id")

    })
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

}
