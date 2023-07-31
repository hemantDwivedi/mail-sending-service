package com.xyz.newsletterbackend.subscribe;

import com.xyz.newsletterbackend.connection.MailSender;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class SubscribeDataService {
    private final SubscribeRepository subscribeRepository;
    private final ModelMapper modelMapper;
    private final SubscriptionService subscriptionService;
    private final MailSender mailSender;

    public SubscribeDataService(SubscribeRepository subscribeRepository, ModelMapper modelMapper, SubscriptionService subscriptionService, MailSender mailSender) {
        this.subscribeRepository = subscribeRepository;
        this.modelMapper = modelMapper;
        this.subscriptionService = subscriptionService;
        this.mailSender = mailSender;
    }

    // handler method for store new subscriber details {name, email}
    public void newSubscriber(SubscribeDto subscribeDto){
        if (subscriptionService.isSubscriptionValid(subscribeDto.getName(), subscribeDto.getEmail())){
            throw new RuntimeException("name and email violating");
        }
        var subscriber = modelMapper.map(subscribeDto, Subscribe.class);
        subscriber.setId(new ObjectId().toString());
        subscribeRepository.save(subscriber);
        mailSenderHandler(subscriber);
    }

    private void mailSenderHandler(Subscribe subscriber) {
        try {
            mailSender.sendEmail(subscriber.getName(), subscriber.getEmail());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
