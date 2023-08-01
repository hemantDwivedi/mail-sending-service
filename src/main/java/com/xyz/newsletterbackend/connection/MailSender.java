package com.xyz.newsletterbackend.connection;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Component
@AllArgsConstructor
public class MailSender {

    public static void sendEmail(String name, String email) throws MessagingException {
        try {
            Session session = MailSessionFactory.getInstance();
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Thanks for Subscribing Our Newsletter");
            message.setText("Dear " + name + ",\n\n" +
                    "Welcome to our application! We are excited to have you.\n\n" +
                    "Best regards,\n" +
                    "Your Application Team");
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}