package ru.project.quiz.mapper.ituser;

import org.mapstruct.Mapper;
import ru.project.quiz.domain.dto.ituser.RoleDTO;
import ru.project.quiz.domain.entity.ituser.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role roleFromRoleDTO(RoleDTO RoleDTO);

    RoleDTO roleDTOFromRole(Role Role);

}
