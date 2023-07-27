package com.xyz.newsletterbackend.subscribe;

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
@CrossOrigin("*")
public class NewsLetterController {
    private  EmailNotificationSender sender;

    public NewsLetterController(EmailNotificationSender sender) {
        this.sender = sender;
    }

    @PostMapping("/api/subscribe")
    public void subscribe(@RequestBody Subscribe subscribe) {
        MainSender(subscribe.getName(), subscribe.getEmail());
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

        try{
            sender.sendEmail(name, email, session, senderEmail);
        } catch (MessagingException e){
            e.printStackTrace();
        }
    }
}
