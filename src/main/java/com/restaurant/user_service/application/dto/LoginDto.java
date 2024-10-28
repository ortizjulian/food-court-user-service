package com.restaurant.user_service.application.dto;

import com.restaurant.user_service.utils.Constants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginDto {

    @NotBlank(message = Constants.EXCEPTION_USER_EMAIL_MANDATORY)
    @Email(message = Constants.EXCEPTION_USER_EMAIL_INVALID)
    private String email;

    @NotBlank(message = Constants.EXCEPTION_USER_PASSWORD_MANDATORY)
    @Size(min = Constants.MIN_USER_PASSWORD_CHARACTERS, message = Constants.EXCEPTION_USER_PASSWORD_MIN_LENGTH)
    private String password;

    public LoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
