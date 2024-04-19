package com.xyz.newsletterbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class MailHistory {
    @Id
    @GeneratedValue
    private Integer id;
    private String email; // MailRequest > var:email
    private String body; // MailRequest > var:message
    private String date;
}
