package com.xyz.newsletterbackend.model;

import com.xyz.newsletterbackend.model.ERole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("roles")
public class Role {
    private String id;
    private ERole name;
}
