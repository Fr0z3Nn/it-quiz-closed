package ru.project.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.project.quiz.entity.User;

public interface UserDetailsRepository extends JpaRepository<User, String> {
}
