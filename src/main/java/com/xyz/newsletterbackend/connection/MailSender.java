package com.xyz.newsletterbackend.connection;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(MailSender.class);

    public static void sendEmail(String email, String customMessage) throws MessagingException {
        try {
            Session session = MailSessionFactory.getInstance();
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Mail From " + email);
            message.setContent("<h2>" + customMessage + "</h2>", "text/html");
            Transport.send(message);
        } catch (Exception e) {
            logger.error("MailSender Class Error: { }", e);
        }
    }
}