package ru.project.quiz.domain.entity.ituser;

import ru.project.quiz.domain.entity.BaseEntity;
import ru.project.quiz.domain.enums.ituser.RoleType;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private RoleType role;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "roles")
    private Set<ITUser> ITUsers;

    public Role(RoleType role, Set<ITUser> ITUsers) {
        this.role = role;
        this.ITUsers = ITUsers;
    }

    public Role() {
    }

    public Role(RoleType role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return role == role1.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(role);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public Set<ITUser> getITUsers() {
        return ITUsers;
    }

    public void setITUsers(Set<ITUser> ITUsers) {
        this.ITUsers = ITUsers;
    }
}
