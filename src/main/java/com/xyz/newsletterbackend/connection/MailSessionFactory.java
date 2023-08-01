package com.xyz.newsletterbackend.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MailSessionFactory {
    private static final HikariDataSource dataSource = new HikariDataSource();

    static {
        // Create and configure a new JavaMail session
        dataSource.setMaximumPoolSize(10);
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


        dataSource.setDataSourceProperties(properties);
    }

    public static Session getInstance() {
        var props = dataSource.getDataSourceProperties();
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(props.getProperty("mail.smtp.username"), props.getProperty("mail.smtp.password"));
            }
        };
        return Session.getInstance(dataSource.getDataSourceProperties(), authenticator);
    }
}
