package ru.project.quiz.domain.dto;

import lombok.Data;
import ru.project.quiz.domain.entity.ITUser;
import ru.project.quiz.domain.enums.RoleType;

import javax.persistence.*;
import java.util.Set;

@Data
public class RoleDTO {
    long id;
    private RoleType role;
    private Set<ITUserDTO> ITUsers;
}
