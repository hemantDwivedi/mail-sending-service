package com.xyz.newsletterbackend.subscribe;

import com.xyz.newsletterbackend.connection.MailSender;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
@AllArgsConstructor
public class SubscribeDataService {
    private final SubscribeRepository subscribeRepository;
    private final ModelMapper modelMapper;
    private final SubscriptionService subscriptionService;
    private final MailSender mailSender;

    // handler method for store new subscriber details {name, email}
    public void newSubscriber(SubscribeDto subscribeDto){
        if (subscriptionService.isSubscriptionValid(subscribeDto.getName(), subscribeDto.getEmail())){
            throw new RuntimeException("name and email violating");
        }
        var subscriber = modelMapper.map(subscribeDto, Subscribe.class);
        // set subscriber ID
        subscriber.setId(new ObjectId().toString());
        // save subscriber to database
        subscribeRepository.save(subscriber);
        // sending an email to subscriber
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
