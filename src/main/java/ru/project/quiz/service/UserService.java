package ru.project.quiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.project.quiz.domain.dto.QuestionDTO;
import ru.project.quiz.domain.dto.UserDTO;
import ru.project.quiz.domain.entity.User;
import ru.project.quiz.repository.UserRepository;

public interface UserService {
    void doRegister(UserDTO userDTO);
}
