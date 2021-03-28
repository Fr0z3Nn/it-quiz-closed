package ru.project.quiz.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.project.quiz.domain.dto.ITUserDTO;
import ru.project.quiz.handler.response.Response;
import ru.project.quiz.service.ITUserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/register")
@Tag(name = "Регистрация")
@AllArgsConstructor
public class RegistrationController {
    public final ITUserService userService;

    @Operation(summary = "Регистрация")
    @PostMapping("/")
    public ResponseEntity<Response> registration(@Valid @RequestBody ITUserDTO ITUserDTO) {
        userService.doRegister(ITUserDTO);
        return new ResponseEntity<>(new Response("Register success"), HttpStatus.OK);
    }
}
