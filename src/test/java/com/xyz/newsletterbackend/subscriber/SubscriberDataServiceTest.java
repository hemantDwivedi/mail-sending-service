package com.xyz.newsletterbackend.subscriber;

import com.xyz.newsletterbackend.dto.SubscriberDto;
import com.xyz.newsletterbackend.service.SubscriptionService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SubscriberDataServiceTest {
     @InjectMocks
    private SubscriptionService subscribeDataService;

     @Test
    public void subscribeDataServiceTest(){
         subscribeDataService.newSubscriber(new SubscriberDto("hemant kumar", "justhemant.d@gmail.com"));
     }
}