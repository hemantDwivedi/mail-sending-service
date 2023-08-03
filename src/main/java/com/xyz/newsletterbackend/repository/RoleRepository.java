package com.xyz.newsletterbackend.repository;

import com.xyz.newsletterbackend.model.ERole;
import com.xyz.newsletterbackend.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
