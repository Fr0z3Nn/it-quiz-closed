package ru.project.quiz.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.project.quiz.domain.dto.ITUserDTO;
import ru.project.quiz.handler.response.Response;
import ru.project.quiz.service.ITUserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Tag(name = "Регистрация")
@AllArgsConstructor
public class RegistrationController {
    public final ITUserService userService;

    @Operation(summary = "Регистрация")
    @PostMapping("/register")
    public ResponseEntity<Response> registration(@Valid @RequestBody ITUserDTO ITUserDTO) {
        userService.saveUser(ITUserDTO);
        return new ResponseEntity<>(new Response("Register success"), HttpStatus.OK);
    }
}
