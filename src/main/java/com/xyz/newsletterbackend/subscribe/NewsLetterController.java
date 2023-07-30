package com.xyz.newsletterbackend.subscribe;

import com.xyz.newsletterbackend.connection.MailSender;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class NewsLetterController {
    private MailSender mailSender;
    private final SubscriptionService subscriptionService;

    public NewsLetterController(MailSender mailSender, SubscriptionService subscriptionService) {
        this.mailSender = mailSender;
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/api/subscribe")
    public ResponseEntity<String> subscribe(@RequestBody Subscribe subscribe) {

        // validation
        if (!subscriptionService.isSubscriptionValid(subscribe.getName(), subscribe.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please provide both you name and email");
        }

        if (subscriptionService.isEmailValid(subscribe.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please provide a valid email");
        }

        try {
            mailSender.sendEmail(subscribe.getName(), subscribe.getEmail());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok("You are subscribed successfully");
    }
}
