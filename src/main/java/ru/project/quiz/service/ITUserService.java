package ru.project.quiz.service;

import ru.project.quiz.domain.dto.ITUserDTO;
import ru.project.quiz.domain.entity.ITUser;

import java.util.List;

public interface ITUserService {
    void saveUser(ITUserDTO ITUserDTO);
}
