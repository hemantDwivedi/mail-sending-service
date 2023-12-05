package com.xyz.newsletterbackend.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmailRawDataDto {
    private String name;
    private String targetEmail;
    private String message;
}
