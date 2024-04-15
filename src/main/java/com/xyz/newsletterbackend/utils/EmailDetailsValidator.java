package com.xyz.newsletterbackend.utils;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmailDetailsValidator {
    public boolean isSubscriptionValid(String email){
        return email != null && !email.trim().isEmpty() && isEmailValid(email);
    }

    private boolean isEmailValid(String email){
        String regex = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
