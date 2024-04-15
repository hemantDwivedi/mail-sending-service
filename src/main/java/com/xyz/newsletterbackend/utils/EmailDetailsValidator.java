package com.xyz.newsletterbackend.utils;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmailDetailsValidator {
    public boolean isSubscriptionValid(String email){
        return EmailValidator.getInstance().isValid(email);
    }
}