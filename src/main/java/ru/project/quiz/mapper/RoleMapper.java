package ru.project.quiz.mapper;

import org.mapstruct.Mapper;
import ru.project.quiz.domain.dto.RoleDTO;
import ru.project.quiz.domain.entity.Role;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role roleFromRoleDTO(RoleDTO RoleDTO);

    RoleDTO roleDTOFromRole(Role Role);

}
