package ru.project.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.project.quiz.entity.User;

@Service
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String email);
    User findByName(String name);
    User findByGoogleUsername(String googleUsername);
    User findByGoogleName(String googleName);
}