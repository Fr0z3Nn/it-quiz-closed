package ru.project.quiz.service.ituser;

import ru.project.quiz.domain.dto.ituser.ITUserDTO;

public interface ITUserService {
    void saveUser(ITUserDTO ITUserDTO);
    void setUserToAdmin(long id);
}
