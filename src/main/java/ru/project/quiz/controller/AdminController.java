package ru.project.quiz.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.project.quiz.domain.entity.ITUser;
import ru.project.quiz.handler.response.Response;
import ru.project.quiz.service.ITUserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin")
@Tag(name = "Админ контроллер")
@AllArgsConstructor
public class AdminController {
    public final ITUserService userService;

    @Operation(summary = "Список юзеров")
    @PostMapping("/users")
    public List<ITUser> getAllUsers() {
        return userService.allUsers();
    }
}
