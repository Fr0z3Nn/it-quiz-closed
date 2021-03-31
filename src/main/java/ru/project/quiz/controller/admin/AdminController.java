package ru.project.quiz.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.project.quiz.service.ituser.ITUserService;

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
