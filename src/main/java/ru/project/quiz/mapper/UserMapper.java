package ru.project.quiz.mapper;

import org.mapstruct.Mapper;
import ru.project.quiz.domain.dto.ITUserDTO;
import ru.project.quiz.domain.entity.ITUser;

@Mapper(componentModel = "spring")
public interface UserMapper {
    ITUser userFromUserDTO(ITUserDTO userDTO);

    ITUserDTO userDTOFromUser(ITUser user);
}
