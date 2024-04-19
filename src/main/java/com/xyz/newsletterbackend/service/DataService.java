package com.xyz.newsletterbackend.service;

import com.xyz.newsletterbackend.model.MailHistory;
import com.xyz.newsletterbackend.model.MailRequest;
import com.xyz.newsletterbackend.repository.MailRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class DataService {
    private MailRepository mailRepository;


    public void saveData(MailRequest mailRequest){
        MailHistory data = new MailHistory(mailRequest.getEmail(), mailRequest.getMessage(), getDateNow());
        mailRepository.save(data);
    }

    public List<MailHistory> getAllHistory(){
        return mailRepository.findAll();
    }

    private String getDateNow() {
        return new SimpleDateFormat("hh.mm aa dd-MM-yy").format(new Date());
    }
}
