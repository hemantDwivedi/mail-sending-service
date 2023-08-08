package com.xyz.newsletterbackend.repository;

import com.xyz.newsletterbackend.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByName(String token);
}
