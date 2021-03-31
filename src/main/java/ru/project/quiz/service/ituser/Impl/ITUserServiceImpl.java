package ru.project.quiz.service.ituser.Impl;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class ITUserServiceImpl implements UserDetailsService, ITUserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MailService mailService;
    private final UserMapper userMapper;
    private final Validator validator;

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
            ITUser user = ITUser.builder()
                    .username(itUserDTO.getUsername())
                    .email(email)
                    .password(bCryptPasswordEncoder.encode(itUserDTO.getPassword()))
                    .roles(
                            Set.of(Role.builder().role(RoleType.USER).build())
                    )
                    .build();
            userRepository.save(user);
            mailService.registrationSuccessfulMessage(email);
        }
    }


    @Override
    public void setUserToAdmin(long id) {
        Optional<ITUser> optionalITUser = userRepository.findById(id);
        if (optionalITUser.isEmpty()) {
            throw new IncorrectInputUserException("Данный пользователь не существует");
        } else {
            ITUser user = optionalITUser.get();
            Set<Role> set = user.getRoles();
            set.add(Role.builder().role(RoleType.ADMIN).build());
            user.setRoles(set);
            userRepository.save(user);
        }
    }
}