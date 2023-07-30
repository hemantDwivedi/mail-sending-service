package com.xyz.newsletterbackend.subscribe;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificationSender {
    public void sendEmail(String name, String email, Session session, String senderEmail) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(senderEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        message.setSubject("Thanks for Subscribing Our Newsletter");
        message.setText("Dear " + name + ",\n\n" +
                "Welcome to our application! We are excited to have you.\n\n" +
                "Best regards,\n" +
                "Your Application Team");
        Transport.send(message);
    }
}
