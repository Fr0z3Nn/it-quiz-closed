package ru.project.quiz.domain.dto.ituser;

import ru.project.quiz.domain.enums.ituser.RoleType;

public class RoleDTO {
    private long id;
    private RoleType role;

    public RoleDTO(RoleType role) {
        this.role = role;
    }

    public RoleDTO() {
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
}
