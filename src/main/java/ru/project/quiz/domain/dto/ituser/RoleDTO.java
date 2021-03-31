package ru.project.quiz.domain.dto.ituser;

import lombok.Data;
import ru.project.quiz.domain.enums.ituser.RoleType;

@Data
public class RoleDTO {
    long id;
    private RoleType role;
}
