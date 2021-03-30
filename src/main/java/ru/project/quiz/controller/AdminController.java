package ru.project.quiz.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.project.quiz.domain.dto.ITUserDTO;
import ru.project.quiz.domain.entity.ITUser;
import ru.project.quiz.domain.entity.Role;
import ru.project.quiz.domain.enums.RoleType;
import ru.project.quiz.handler.response.Response;
import ru.project.quiz.service.ITUserService;

import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/admin")
@Tag(name = "Админка")
@AllArgsConstructor
public class AdminController {

    public final ITUserService userService;
    public final String SET_ROLE = "/doAdmin/{id}";

    @Operation(summary = "Дать права админа юзеру")
    @PostMapping(SET_ROLE)
    public void setRoleToAdmin(@PathVariable long id) {
        userService.setUserToAdmin(id);
    }
}
