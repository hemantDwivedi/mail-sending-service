package com.xyz.newsletterbackend.subscribe;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import java.util.Properties;

@RestController
public class NewsLetterController {
    private EmailNotificationSender sender;
    private SubscriptionService subscriptionService;

    public NewsLetterController(EmailNotificationSender sender, SubscriptionService subscriptionService) {
        this.sender = sender;
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

        MainSender(subscribe.getName(), subscribe.getEmail());
        return ResponseEntity.ok("You are subscribed successfully");
    }

    public void MainSender(String name, String email) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        try {
            InputStream inputStream = new FileInputStream(".env");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String senderEmail = properties.getProperty("SENDER_EMAIL");
        String senderPassword = properties.getProperty("SENDER_PASSWORD");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            sender.sendEmail(name, email, session, senderEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
