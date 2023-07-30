package com.xyz.newsletterbackend.subscribe;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SubscriptionService {
    public boolean isSubscriptionValid(String name, String email){
        return name != null && !name.trim().isEmpty()
                && email != null && !email.trim().isEmpty();
    }

    public boolean isEmailValid(String email){
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
