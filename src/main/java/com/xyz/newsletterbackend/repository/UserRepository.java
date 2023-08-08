package com.xyz.newsletterbackend.repository;

import com.xyz.newsletterbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameOrEmail(String email, String username);
    Boolean existsByEmailOrUsername(String email, String username);
}
