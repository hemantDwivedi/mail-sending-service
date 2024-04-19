package com.xyz.newsletterbackend.controller;

import com.xyz.newsletterbackend.model.MailHistory;
import com.xyz.newsletterbackend.model.MailRequest;
import com.xyz.newsletterbackend.service.DataService;
import com.xyz.newsletterbackend.service.EmailSenderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class HomeController {
    private final EmailSenderService emailSenderService;
    private final DataService dataService;
    @PostMapping("/api/v1/mail")
    public ResponseEntity<String> mailService(@RequestBody MailRequest mailRequest) {
        emailSenderService.newMail(mailRequest);
        return ResponseEntity.ok("You are subscribed successfully");
    }

    @GetMapping
    public String index(Model model){
        List<MailHistory> mails = dataService.getAllHistory();

        model.addAttribute("mails", mails);

        return "index";
    }
}
