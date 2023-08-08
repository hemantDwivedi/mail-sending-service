package com.xyz.newsletterbackend.service;

import com.xyz.newsletterbackend.dto.LoginDto;
import com.xyz.newsletterbackend.dto.RegisterDto;
import com.xyz.newsletterbackend.exception.ApiException;
import com.xyz.newsletterbackend.exception.ResourceNotFoundException;
import com.xyz.newsletterbackend.model.ERole;
import com.xyz.newsletterbackend.model.Role;
import com.xyz.newsletterbackend.model.Token;
import com.xyz.newsletterbackend.repository.RoleRepository;
import com.xyz.newsletterbackend.repository.TokenRepository;
import com.xyz.newsletterbackend.security.JwtTokenProvider;
import com.xyz.newsletterbackend.model.User;
import com.xyz.newsletterbackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private TokenRepository tokenRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;


    public String register(RegisterDto registerDto){
        if(userRepository.existsByEmailOrUsername(registerDto.getEmail(), registerDto.getUsername())){
            throw new ApiException(HttpStatus.BAD_REQUEST, "User already exists with email " + registerDto.getEmail());
        }
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(ERole.ROLE_STAFF.toString()).orElse(null));

        var user = new User();
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRoles(roles);
        userRepository.save(user);
        return "You registered Successfully!";
    }

    public String login(LoginDto loginDto) {
        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),
                loginDto.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.generateToken(authentication);
    }
    public void logoutUser(String bearerToken) {
        String token = bearerToken.substring(7);
        var username = jwtTokenProvider.getUsername(token);
        var user = userRepository.findByUsername(username).orElse(null);
        if(tokenRepository.findByName(token).orElse(null) == null){
            var jwtToken = savedUserToken(user, token);
            tokenRepository.save(jwtToken);
        }
    }

    private static Token savedUserToken(User user, String jwtToken){
        return Token.builder()
                .user(user)
                .name(jwtToken)
                .build();
    }
}
