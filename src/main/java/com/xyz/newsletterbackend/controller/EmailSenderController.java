package com.xyz.newsletterbackend.controller;

import com.xyz.newsletterbackend.dto.EmailRawDataDto;
import com.xyz.newsletterbackend.service.EmailSenderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/newsletter")
@AllArgsConstructor
public class EmailSenderController {
    private final EmailSenderService emailSenderService;
    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribe(@RequestBody EmailRawDataDto emailRawDataDto) {
        emailSenderService.newMail(emailRawDataDto);
        return ResponseEntity.ok("You are subscribed successfully");
    }
}
