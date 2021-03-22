package ru.project.quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.project.quiz.entity.User;
import ru.project.quiz.enums.Role;
import ru.project.quiz.repository.UserRepository;

import java.util.Collections;

@RestController
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String registration()
    {
        return "REGISTER PAGE";
    }

    @PostMapping("/registration")
    public User addUser(@RequestBody User userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));

        userRepository.save(user);

        return user;
    }
}
