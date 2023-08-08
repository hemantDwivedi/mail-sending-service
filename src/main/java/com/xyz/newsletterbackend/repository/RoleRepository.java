package com.xyz.newsletterbackend.repository;

import com.xyz.newsletterbackend.model.ERole;
import com.xyz.newsletterbackend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
