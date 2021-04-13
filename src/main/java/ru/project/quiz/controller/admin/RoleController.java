package ru.project.quiz.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.project.quiz.domain.dto.ituser.ITUserDTO;
import ru.project.quiz.domain.enums.ituser.PermissionType;
import ru.project.quiz.service.ituser.RoleService;

import java.util.List;


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
    public void addNewRole(@RequestParam String name, @RequestParam PermissionType permissionType) {
        roleService.addNewRole(name, permissionType);
    }

    @Operation(summary = "Удалить роль")
    @PostMapping("/delete")
    public void deleteRole(@RequestParam String name) {
        roleService.deleteRole(name);
    }

    @Operation(summary = "Лист пользователей с данной ролью")
    @PostMapping("/find_by_role")
    public List<ITUserDTO> findUsersByRole(@RequestParam String name) {
        return roleService.findUsersByRole(name);
    }
}
