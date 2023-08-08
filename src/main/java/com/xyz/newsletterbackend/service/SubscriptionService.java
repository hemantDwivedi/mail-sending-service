package com.xyz.newsletterbackend.service;

import com.xyz.newsletterbackend.connection.MailSender;
import com.xyz.newsletterbackend.dto.SubscriberDto;
import com.xyz.newsletterbackend.exception.ResourceNotFoundException;
import com.xyz.newsletterbackend.model.Subscriber;
import com.xyz.newsletterbackend.repository.SubscriptionRepository;
import com.xyz.newsletterbackend.utils.SubscriberDetailsValidator;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
@AllArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final ModelMapper modelMapper;
    private final SubscriberDetailsValidator subscriberDetailsValidator;
    private final MailSender mailSender;

    // handler method for store new subscriber details {name, email}
    public void newSubscriber(SubscriberDto subscriberDto){
        if (subscriberDetailsValidator.isSubscriptionValid(subscriberDto.getName(), subscriberDto.getEmail())){
            throw new RuntimeException("name and email violating");
        }
        var subscriber = modelMapper.map(subscriberDto, Subscriber.class);
        // save subscriber to database
        subscriptionRepository.save(subscriber);
        // sending an email to subscriber
        mailSenderHandler(subscriber);
    }

    private void mailSenderHandler(Subscriber subscriber) {
        try {
            MailSender.sendEmail(subscriber.getName(), subscriber.getEmail());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void unsubscribe(String email) {
        var subscriberData = subscriptionRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Email not exists"));
        subscriptionRepository.delete(subscriberData);
    }
}
