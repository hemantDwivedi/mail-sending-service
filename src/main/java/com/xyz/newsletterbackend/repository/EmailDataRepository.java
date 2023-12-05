package com.xyz.newsletterbackend.repository;

import com.xyz.newsletterbackend.model.EmailRawData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailDataRepository extends JpaRepository<EmailRawData, Long> {
    Optional<EmailRawData> findByTargetEmail(String email);
}
