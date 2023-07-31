package com.xyz.newsletterbackend.subscribe;

public class SubscribeDto {
    private String name;
    private String email;

    public SubscribeDto() {
    }

    public SubscribeDto(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
