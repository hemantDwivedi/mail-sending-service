package com.xyz.newsletterbackend.user;

import com.xyz.newsletterbackend.role.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("users")
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private Role role;

    public Role getRoleName() {
        return role;
    }

    public void setRoleName(String roleName) {
        this.role = new Role();
        this.role.setName(roleName);
    }
}
