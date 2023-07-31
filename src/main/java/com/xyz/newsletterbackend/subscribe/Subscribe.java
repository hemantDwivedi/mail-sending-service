package com.xyz.newsletterbackend.subscribe;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("subscribers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Subscribe {
    @Id
    private String id;
    private String name;
    private String email;

}
