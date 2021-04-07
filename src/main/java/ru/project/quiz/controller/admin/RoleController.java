package ru.project.quiz.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.project.quiz.domain.enums.ituser.RoleType;
import ru.project.quiz.service.role.RoleService;

@RestController
@RequestMapping("/admin/role")
@Tag(name = "Контроллер ролей")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @Operation(summary = "Создать новую роль")
    @PostMapping("/add")
    public void addRole(@RequestParam RoleType roleType) {
        roleService.addRole(roleType);
    }

    @Operation(summary = "Удалить роль")
    @PostMapping("/delete")
    public void addRole(@RequestParam long id) {
        roleService.deleteRole(id);
    }
}
