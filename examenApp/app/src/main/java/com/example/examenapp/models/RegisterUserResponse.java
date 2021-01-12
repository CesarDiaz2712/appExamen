package com.example.examenapp.models;

public class RegisterUserResponse {
    private User user;
    private String token;

    public RegisterUserResponse(User user, String token) {
        this.user = user;
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }
}
