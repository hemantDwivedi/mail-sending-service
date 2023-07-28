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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@CrossOrigin("*")
public class NewsLetterController {
    private EmailNotificationSender sender;

    public NewsLetterController(EmailNotificationSender sender) {
        this.sender = sender;
    }

    @PostMapping("/api/subscribe")
    public ResponseEntity<String> subscribe(@RequestBody Subscribe subscribe) {

        // validation
        if (subscribe.getName() == null || subscribe.getName().trim().isEmpty() || subscribe.getEmail() == null || subscribe.getEmail().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please provide both your name and email");
        }
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(subscribe.getEmail());

        if (!matcher.matches()) {
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
