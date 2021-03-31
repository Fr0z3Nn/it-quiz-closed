package ru.project.quiz.domain.entity.ituser;

import lombok.*;
import ru.project.quiz.domain.enums.ituser.RoleType;

import javax.persistence.*;
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

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "roles")
    @EqualsAndHashCode.Exclude
    private Set<ITUser> ITUsers;

}
