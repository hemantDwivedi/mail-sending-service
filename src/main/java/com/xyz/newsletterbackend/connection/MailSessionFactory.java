package com.xyz.newsletterbackend.connection;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MailSessionFactory extends BasePooledObjectFactory<Session> {
    @Override
    public Session create() {
        // Create and configure a new JavaMail session
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(".env");
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String senderEmail = properties.getProperty("SENDER_EMAIL");
        String senderPassword = properties.getProperty("SENDER_PASSWORD");
        properties.put("mail.smtp.username", senderEmail);
        properties.put("mail.smtp.password", senderPassword);

        return Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });
    }

    @Override
    public PooledObject<Session> wrap(Session session) {
        return new DefaultPooledObject<>(session);
    }
}
