package ru.project.quiz.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.project.quiz.domain.enums.ituser.RoleType;
import ru.project.quiz.service.ituser.ITUserService;

@RestController
@RequestMapping("/admin")
@Tag(name = "Админка")
public class AdminController {

    public final ITUserService userService;

    public final String SET_ROLE = "/give_role";

    @Operation(summary = "Дать роль пользователю")
    @PostMapping(SET_ROLE)
    public void setNewRole(@RequestParam String username, @RequestParam RoleType roleType) {
        userService.setNewRole(username, roleType);
    }

    public AdminController(ITUserService userService) {
        this.userService = userService;
    }
}
