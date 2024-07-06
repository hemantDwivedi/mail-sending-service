package com.xyz.newsletterbackend.controller;

import com.xyz.newsletterbackend.model.MailHistory;
import com.xyz.newsletterbackend.model.MailRequest;
import com.xyz.newsletterbackend.service.DataService;
import com.xyz.newsletterbackend.service.EmailSenderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class HomeController {
    private final EmailSenderService emailSenderService;
    private final DataService dataService;
    @PostMapping("mails")
    public ResponseEntity<String> mailService(@RequestBody MailRequest mailRequest) {
        emailSenderService.newMail(mailRequest);
        return ResponseEntity.ok("You are subscribed successfully");
    }

    @GetMapping("mails")
    public ResponseEntity<List<MailHistory>> fetchMails(){
        return ResponseEntity.ok(dataService.getAllHistory());
    }
}
