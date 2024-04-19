package com.xyz.newsletterbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class MailHistory {
    @Id
    private String email; // MailRequest > var:email
    private String body; // MailRequest > var:message
    private String time;
}
