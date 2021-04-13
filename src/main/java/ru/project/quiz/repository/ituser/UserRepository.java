package ru.project.quiz.repository.ituser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.project.quiz.domain.entity.ituser.ITUser;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<ITUser, Long> {
    Optional<ITUser> findUserByUsername(String username);

    boolean existsByEmail(String email);

    @Query(value = "SELECT * FROM users " +
                    "JOIN users_roles ON users.id = users_roles.itusers_id" +
                    " WHERE users_roles.roles_name = :role_name", nativeQuery = true)
    List<ITUser> findITUsersByRoleName(@Param("role_name")String name);
}