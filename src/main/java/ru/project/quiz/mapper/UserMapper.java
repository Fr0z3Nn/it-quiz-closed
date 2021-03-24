package ru.project.quiz.mapper;

import org.mapstruct.Mapper;
import ru.project.quiz.domain.dto.QuestionDTO;
import ru.project.quiz.domain.dto.UserDTO;
import ru.project.quiz.domain.entity.Question;
import ru.project.quiz.domain.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User userFromUserDTO (UserDTO userDTO);
    UserDTO userDTOFromUser (User user);
}
