package ru.project.quiz.mapper.ituser;

import org.mapstruct.Mapper;
import ru.project.quiz.domain.dto.ituser.ITUserDTO;
import ru.project.quiz.domain.entity.ituser.ITUser;

@Mapper(componentModel = "spring")
public interface UserMapper {
    ITUser userFromUserDTO(ITUserDTO userDTO);

    ITUserDTO userDTOFromUser(ITUser user);
}
