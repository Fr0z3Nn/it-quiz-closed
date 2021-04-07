package ru.project.quiz.service.role;

import ru.project.quiz.domain.entity.ituser.Role;
import ru.project.quiz.domain.enums.ituser.RoleType;

public interface RoleService {
    void addRole(RoleType roleType);

    void deleteRole(long id);
}
