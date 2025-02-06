package com.restaurant.user_service.application.dto;

public class UserPhoneResponseDto {
    private String phone;

    public UserPhoneResponseDto(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
