package ru.project.quiz.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.project.quiz.domain.dto.UserDTO;
import ru.project.quiz.domain.entity.User;
import ru.project.quiz.domain.enums.RoleType;
import ru.project.quiz.handler.exception.IncorrectInputUserException;
import ru.project.quiz.mapper.UserMapper;
import ru.project.quiz.repository.UserRepository;
import ru.project.quiz.service.UserService;

import java.util.HashSet;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    @Override
    public void doRegister(UserDTO userDTO) {
        if(userDTO == null){
            throw new IncorrectInputUserException("Неккоретный ввод пользователя");
        } else {
            User user = userMapper.userFromUserDTO(userDTO);
            userRepository.save(user);
        }
    }
}
