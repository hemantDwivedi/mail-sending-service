package com.xyz.newsletterbackend.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class JwtAuthenticationResource{
    @PostMapping("/authenticate")
    public Authentication authenticate(Authentication authentication){
        return authentication;
    }
}
