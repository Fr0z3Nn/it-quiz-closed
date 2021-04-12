package ru.project.quiz.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.project.quiz.service.ituser.ITUserService;

@RestController
@RequestMapping("/admin")
@Tag(name = "Админка")
public class AdminController {

    public final ITUserService userService;

    public final String SET_ROLE = "/give_role";

    @Operation(summary = "Дать роль пользователю", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping(SET_ROLE)
    public void setNewRole(@RequestParam String username, @RequestParam String roleName) {
        userService.setNewRole(username, roleName);
    }

    public AdminController(ITUserService userService) {
        this.userService = userService;
    }
}
