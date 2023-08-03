package com.xyz.newsletterbackend.utils;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SubscriberDetailsValidator {
    public boolean isSubscriptionValid(String name, String email){
        return name != null && !name.trim().isEmpty()
                && email != null && !email.trim().isEmpty() && isEmailValid(email);
    }

    private boolean isEmailValid(String email){
        String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@\" \n" +
                "        + \"[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
