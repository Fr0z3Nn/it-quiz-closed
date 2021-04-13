package ru.project.quiz.service.ituser;

import ru.project.quiz.domain.dto.ituser.ITUserDTO;
import ru.project.quiz.domain.entity.ituser.ITUser;
import ru.project.quiz.domain.enums.ituser.PermissionType;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    void addNewRole(String name, PermissionType permissionType);

    void deleteRole(String name);

    List<ITUserDTO> findUsersByRole(String name);
}
