package ru.project.quiz.service;

import ru.project.quiz.domain.dto.ITUserDTO;
import ru.project.quiz.domain.entity.ITUser;

import java.util.Optional;

public interface ITUserService {
    void saveUser(ITUserDTO ITUserDTO);
    void setUserToAdmin(long id);
}
