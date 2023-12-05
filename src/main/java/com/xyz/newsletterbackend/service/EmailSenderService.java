package com.xyz.newsletterbackend.service;

import com.xyz.newsletterbackend.connection.MailSender;
import com.xyz.newsletterbackend.dto.EmailRawDataDto;
import com.xyz.newsletterbackend.model.EmailRawData;
import com.xyz.newsletterbackend.repository.EmailDataRepository;
import com.xyz.newsletterbackend.utils.EmailDetailsValidator;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
@AllArgsConstructor
public class EmailSenderService {
    private final EmailDataRepository emailDataRepository;
    private final ModelMapper modelMapper;
    private final EmailDetailsValidator emailDetailsValidator;

    public void newMail(EmailRawDataDto emailRawDataDto){
        if (emailDetailsValidator.isSubscriptionValid(emailRawDataDto.getName(), emailRawDataDto.getTargetEmail())){
            throw new RuntimeException("name and email violating");
        }
        var emailRawData = modelMapper.map(emailRawDataDto, EmailRawData.class);
        emailDataRepository.save(emailRawData);
        mailSenderHandler(emailRawData);
    }

    private void mailSenderHandler(EmailRawData emailRawData) {
        try {
            MailSender.sendEmail(emailRawData.getName(), emailRawData.getTargetEmail(), emailRawData.getMessage());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
