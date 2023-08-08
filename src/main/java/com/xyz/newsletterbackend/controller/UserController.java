package com.xyz.newsletterbackend.controller;

import com.xyz.newsletterbackend.dto.UserDto;
import com.xyz.newsletterbackend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class UserController {
    private UserService userService;
    @GetMapping("/users")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<UserDto>> retrieveAllUsers(){
        return ResponseEntity.ok(userService.retrieveAllUsers());
    }

    @PutMapping("/update-user/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable Long id){
        return ResponseEntity.ok(userService.updateUser(userDto, id));
    }
}
