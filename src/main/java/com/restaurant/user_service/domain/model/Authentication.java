package com.restaurant.user_service.domain.model;

public class Authentication {
    private String token;

    public Authentication(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
