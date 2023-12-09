package com.xyz.newsletterbackend.controller;

import com.xyz.newsletterbackend.model.MailRequest;
import com.xyz.newsletterbackend.service.EmailSenderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
@AllArgsConstructor
public class EmailSenderController {
    private final EmailSenderService emailSenderService;
    @PostMapping("/mail")
    public ResponseEntity<String> mailService(@RequestBody MailRequest mailRequest) {
        emailSenderService.newMail(mailRequest);
        return ResponseEntity.ok("You are subscribed successfully");
    }
}
