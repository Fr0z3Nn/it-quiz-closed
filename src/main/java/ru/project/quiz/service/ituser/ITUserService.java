package ru.project.quiz.service.ituser;

import org.springframework.security.core.userdetails.UserDetails;
import ru.project.quiz.domain.dto.ituser.ITUserDTO;
import ru.project.quiz.domain.enums.ituser.PermissionType;

public interface ITUserService {
    void saveUser(ITUserDTO ITUserDTO);
    void setNewRole(String username, String roleName);
    UserDetails loadUserByUsername(String username);
}
