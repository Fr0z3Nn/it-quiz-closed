package ru.project.quiz.domain.dto.ituser;

import ru.project.quiz.domain.enums.ituser.PermissionType;

import java.util.Set;

public class RoleDTO {
    private long id;
    private String name;
    private Set<PermissionType> permissions;

    public RoleDTO(String name, Set<PermissionType> permissions) {
        this.name = name;
        this.permissions = permissions;
    }

    public RoleDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public void setPermissions(Set<PermissionType> permissions) {
        this.permissions = permissions;
    }
}
