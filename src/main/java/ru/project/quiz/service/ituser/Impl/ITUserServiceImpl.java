package ru.project.quiz.service.ituser.Impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.project.quiz.domain.dto.ituser.ITUserDTO;
import ru.project.quiz.domain.entity.ituser.ITUser;
import ru.project.quiz.domain.entity.ituser.Role;
import ru.project.quiz.domain.enums.ituser.RoleType;
import ru.project.quiz.handler.exception.IncorrectInputUserException;
import ru.project.quiz.mapper.ituser.UserMapper;
import ru.project.quiz.repository.itquiz.UserRepository;
import ru.project.quiz.service.ituser.ITUserService;
import ru.project.quiz.service.mail.MailService;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Optional;
import java.util.Set;

@Service
public class ITUserServiceImpl implements UserDetailsService, ITUserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MailService mailService;
    private final UserMapper userMapper;
    private final Validator validator;

    public ITUserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, MailService mailService, UserMapper userMapper, Validator validator) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.mailService = mailService;
        this.userMapper = userMapper;
        this.validator = validator;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ITUser> optionalUser = userRepository.findUserByUsername(username);
        if (optionalUser.isPresent()) {
            return userMapper.userDTOFromUser(optionalUser.get());
        }
        throw new UsernameNotFoundException("User not found, sorry");
    }

    @Override
    public void saveUser(ITUserDTO itUserDTO) {
        Set<ConstraintViolation<ITUserDTO>> violations = validator.validate(itUserDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        Optional<ITUser> optionalUser = userRepository.findUserByUsername(itUserDTO.getUsername());
        if (optionalUser.isPresent()) {
            throw new IncorrectInputUserException("Данный пользователь существует");
        } else {
            String email = itUserDTO.getEmail();
            if (userRepository.existsByEmail(email)) {
                throw new IncorrectInputUserException("Пользователь с данной почтой уже существует");
            }
            ITUser user = new ITUser();
            user.setUsername(itUserDTO.getUsername());
            user.setEmail(email);
            user.setPassword(bCryptPasswordEncoder.encode(itUserDTO.getPassword()));
            user.setRoles(Set.of(new Role(RoleType.USER)));
            userRepository.save(user);
            mailService.registrationSuccessfulMessage(email);
        }
    }


    @Override
    public void setNewRole(String username, RoleType roleType) {
        Optional<ITUser> optionalITUser = userRepository.findUserByUsername(username);
        if (optionalITUser.isEmpty()) {
            throw new IncorrectInputUserException("Данный пользователь не существует");
        } else {
            ITUser user = optionalITUser.get();
            Set<Role> set = user.getRoles();
            set.add(new Role(roleType));
            user.setRoles(set);
            userRepository.save(user);
        }
    }
}