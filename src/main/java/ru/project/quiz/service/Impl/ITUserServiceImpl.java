package ru.project.quiz.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.project.quiz.domain.dto.ITUserDTO;
import ru.project.quiz.domain.entity.ITUser;
import ru.project.quiz.domain.entity.Role;
import ru.project.quiz.domain.enums.RoleType;
import ru.project.quiz.handler.exception.IncorrectInputUserException;
import ru.project.quiz.mapper.UserMapper;
import ru.project.quiz.repository.UserRepository;
import ru.project.quiz.service.ITUserService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class ITUserServiceImpl implements UserDetailsService, ITUserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ITUser> optionalUser = userRepository.findUserByUsername(username);
        if (optionalUser.isPresent()) {
            ITUser ITUser = optionalUser.get();
            List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("USER"));
            return User.builder()
                    .username(ITUser.getUsername())
                    .password(ITUser.getPassword())
                    .authorities(authorities)
                    .build();
        }
        throw new UsernameNotFoundException("User not found, sorry");
    }

    @Override
    public void saveUser(ITUserDTO itUserDTO) {
        Optional<ITUser> optionalUser = userRepository.findUserByUsername(itUserDTO.getUsername());
        if (optionalUser.isPresent()) {
            throw new IncorrectInputUserException("Данный пользователь существует");
        } else {
            ITUser user = ITUser.builder()
                    .username(itUserDTO.getUsername())
                    .password(bCryptPasswordEncoder.encode(itUserDTO.getPassword()))
                    .roles(
                            Set.of(Role.builder().role(RoleType.USER).build())
                    )
                    .build();
            userRepository.save(user);
        }
    }

    @Override
    public List<ITUser> allUsers() {
        return userRepository.findAll();
    }
}