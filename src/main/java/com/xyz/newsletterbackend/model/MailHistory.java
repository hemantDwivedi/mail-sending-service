package com.xyz.newsletterbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class MailHistory {
    @Id
    private String to; // MailRequest > var:email
    private String body; // MailRequest > var:message
    private LocalDateTime time;
}
