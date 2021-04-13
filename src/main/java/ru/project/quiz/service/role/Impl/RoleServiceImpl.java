package ru.project.quiz.service.role.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.project.quiz.domain.entity.ituser.Role;
import ru.project.quiz.domain.enums.ituser.RoleType;
import ru.project.quiz.repository.quiz.RoleRepository;
import ru.project.quiz.service.role.RoleService;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
    public final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void addRole(RoleType roleType) {
        Role role = Role.builder()
                .role(roleType)
                .build();
        roleRepository.save(role);
    }

    @Override
    public void deleteRole(long id) {
        roleRepository.deleteById(id);
    }
}
