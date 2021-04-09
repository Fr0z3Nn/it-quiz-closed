package ru.project.quiz.service.ituser;

import ru.project.quiz.domain.dto.ituser.ITUserDTO;
import ru.project.quiz.domain.enums.ituser.PermissionType;

public interface ITUserService {
    void saveUser(ITUserDTO ITUserDTO);
    void setNewRole(String username, String roleName);
}
