package ru.project.quiz.domain.entity.ituser;

import ru.project.quiz.domain.entity.BaseEntity;
import ru.project.quiz.domain.enums.ituser.PermissionType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    @Column(name = "name")
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "permissions",
            joinColumns = @JoinColumn(name = "role_id")
    )
    @Column(name = "permission")
    @Enumerated(EnumType.STRING)
    private Set<PermissionType> permissions;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "roles")
    private Set<ITUser> ITUsers;

    public Role(String name, Set<PermissionType> permissions, Set<ITUser> ITUsers) {
        this.name = name;
        this.permissions = permissions;
        this.ITUsers = ITUsers;
    }

    public Role() {
    }

    public Role(String name, Set<PermissionType> permissions) {
        this.name = name;
        this.permissions = permissions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<PermissionType> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<PermissionType> permission) {
        this.permissions = permission;
    }

    public Set<ITUser> getITUsers() {
        return ITUsers;
    }

    public void setITUsers(Set<ITUser> ITUsers) {
        this.ITUsers = ITUsers;
    }
}
