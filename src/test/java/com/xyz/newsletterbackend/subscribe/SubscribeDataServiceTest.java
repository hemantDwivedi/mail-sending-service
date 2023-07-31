package com.xyz.newsletterbackend.subscribe;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SubscribeDataServiceTest {
     @InjectMocks
    private SubscribeDataService subscribeDataService;

     @Test
    public void subscribeDataServiceTest(){
         subscribeDataService.newSubscriber(new SubscribeDto("hemant kumar", "justhemant.d@gmail.com"));
     }
}