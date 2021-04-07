package ru.project.quiz.controller.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.project.quiz.domain.dto.ituser.ITUserDTO;
import ru.project.quiz.handler.response.Response;
import ru.project.quiz.service.ituser.ITUserService;

@RestController
@RequestMapping("/auth")
@Tag(name = "Регистрация")
public class RegistrationController {
    public final ITUserService userService;
    public final String REGISTER = "/register";

    @Operation(summary = "Регистрация")
    @PostMapping(REGISTER)
    public ResponseEntity<Response> registration(@RequestBody ITUserDTO ITUserDTO) {
        userService.saveUser(ITUserDTO);
        return new ResponseEntity<>(new Response("Register success"), HttpStatus.OK);
    }

    public RegistrationController(ITUserService userService) {
        this.userService = userService;
    }
}
