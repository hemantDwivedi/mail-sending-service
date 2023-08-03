package com.xyz.newsletterbackend.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("users")
public class User {
    @Id
    private String id;
    private String username;
    private String email;
    private String password;
    private Set<Role> roles = new HashSet<>();
}
