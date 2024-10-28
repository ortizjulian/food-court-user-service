package com.restaurant.user_service.application.dto;

public class AuthenticationDtoResponse {
    private String token;

    public AuthenticationDtoResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

