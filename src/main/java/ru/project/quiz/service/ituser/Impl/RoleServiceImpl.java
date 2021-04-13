package ru.project.quiz.service.ituser.Impl;

import org.springframework.stereotype.Service;
import ru.project.quiz.domain.dto.ituser.ITUserDTO;
import ru.project.quiz.domain.entity.ituser.ITUser;
import ru.project.quiz.domain.entity.ituser.Role;
import ru.project.quiz.domain.enums.ituser.PermissionType;
import ru.project.quiz.mapper.ituser.UserMapper;
import ru.project.quiz.repository.ituser.RoleRepository;
import ru.project.quiz.repository.ituser.UserRepository;
import ru.project.quiz.service.ituser.RoleService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public RoleServiceImpl(RoleRepository roleRepository, UserRepository userRepository, UserMapper userMapper) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public void addNewRole(String name, PermissionType permissionType) {
        Role role = new Role();
        role.setName(name);
        Set<PermissionType> set = new HashSet<>();
        set.add(permissionType);
        role.setPermissions(set);
        roleRepository.save(role);
    }

    @Override
    public void deleteRole(String name) {
       Optional<Role> optRole = roleRepository.findByName(name);
       if (optRole.isEmpty()){
           throw new RuntimeException("Роль не существует");
       }
       Role role = optRole.get();
       roleRepository.delete(role);
    }

    @Override
    public List<ITUserDTO> findUsersByRole(String name) {
        List<ITUser> list = userRepository.findITUsersByRoleName(name);
        List<ITUserDTO> listDTO = userMapper.listITUsersDTOFromListITUsers(list);
        if (list.isEmpty()){
            throw new RuntimeException("Юзеры с данной ролью не найдены");
        }
        return listDTO;
    }
}
