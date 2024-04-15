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
    private final EmailDetailsValidator emailDetailsValidator;

    public void newMail(MailRequest mailRequest){
        if (emailDetailsValidator.isSubscriptionValid(mailRequest.getTargetEmail())){
            throw new ApiException(HttpStatus.BAD_REQUEST, "Email violating");
        }
        mailSenderHandler(mailRequest);
    }

    private void mailSenderHandler(MailRequest mailRequest) {
        try {
            MailSender.sendEmail(mailRequest.getTargetEmail(), mailRequest.getMessage());
        } catch (MessagingException e) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Service unavailable");
        }
    }
}
