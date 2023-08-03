package com.xyz.newsletterbackend.controller;

import com.xyz.newsletterbackend.dto.SubscriberDto;
import com.xyz.newsletterbackend.service.SubscriptionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class SubscribeController {
    private final SubscriptionService subscribeDataService;
    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribe(@RequestBody SubscriberDto subscriberDto) {
        subscribeDataService.newSubscriber(subscriberDto);
        return ResponseEntity.ok("You are subscribed successfully");
    }
    @DeleteMapping("/unsubscribe")
    public ResponseEntity<String> unsubscribe(@RequestParam String email){
        subscribeDataService.unsubscribe(email);
        return ResponseEntity.ok("You are unsubscribed successfully");
    }
}
