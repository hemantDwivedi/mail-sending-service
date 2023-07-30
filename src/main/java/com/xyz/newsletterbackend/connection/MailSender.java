package com.xyz.newsletterbackend.connection;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.stereotype.Component;

@Component
public class MailSender {
    private GenericObjectPool<Session> connectionPool;

    public MailSender() {
        // Configure the connection pool
        GenericObjectPoolConfig<Session> poolConfig = new GenericObjectPoolConfig<>();
        poolConfig.setMaxTotal(10); // Maximum number of connections in the pool

        // Create the connection pool with the custom factory
        connectionPool = new GenericObjectPool<>(new MailSessionFactory(), poolConfig);
    }

    public void sendEmail(String name, String email) throws MessagingException {
        Session session = null;
        try {
            session = connectionPool.borrowObject();
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
        } finally {
            if (session != null){
                connectionPool.returnObject(session);
            }
        }
    }
}
