package com.xyz.newsletterbackend.repository;

import com.xyz.newsletterbackend.model.MailHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailRepository extends JpaRepository<MailHistory, String> {
}
