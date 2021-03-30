package ru.project.quiz.domain.dto;

import lombok.Data;
import ru.project.quiz.domain.enums.RoleType;

import java.util.Set;

@Data
public class RoleDTO {
    long id;
    private RoleType role;
}
