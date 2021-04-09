package ru.project.quiz.repository.ituser;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.project.quiz.domain.entity.ituser.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
