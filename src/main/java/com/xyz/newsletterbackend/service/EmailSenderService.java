package com.xyz.newsletterbackend.service;

import com.xyz.newsletterbackend.connection.MailSender;
import com.xyz.newsletterbackend.exception.ApiException;
import com.xyz.newsletterbackend.model.MailRequest;
import com.xyz.newsletterbackend.utils.EmailDetailsValidator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
@AllArgsConstructor
public class EmailSenderService {
    private EmailDetailsValidator validator;

    public void newMail(MailRequest mailRequest){
        System.out.println(mailRequest.getEmail());
        if (!validator.isSubscriptionValid(mailRequest.getEmail())){
            throw new ApiException(HttpStatus.BAD_REQUEST, "Invalid email");
        }
        mailSenderHandler(mailRequest);
    }

    private void mailSenderHandler(MailRequest mailRequest) {
        try {
            MailSender.sendEmail(mailRequest.getEmail(), mailRequest.getMessage());
        } catch (MessagingException e) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Service unavailable");
        }
    }
}
