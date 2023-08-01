package com.xyz.newsletterbackend.subscribe;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class SubscribeController {
    private final SubscribeDataService subscribeDataService;
    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribe(@RequestBody SubscribeDto subscribeDto) {
        subscribeDataService.newSubscriber(subscribeDto);
        return ResponseEntity.ok("You are subscribed successfully");
    }
    @DeleteMapping("/unsubscribe")
    public ResponseEntity<String> unsubscribe(@RequestParam String email){
        subscribeDataService.unsubscribe(email);
        return ResponseEntity.ok("You are unsubscribed successfully");
    }
}
