package com.xyz.newsletterbackend.subscribe;

import com.xyz.newsletterbackend.connection.MailSender;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

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
}
