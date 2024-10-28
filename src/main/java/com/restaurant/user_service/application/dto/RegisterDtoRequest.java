package com.restaurant.user_service.application.dto;

import com.restaurant.user_service.utils.Constants;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class RegisterDtoRequest {

    @NotBlank(message = Constants.EXCEPTION_USER_NAME_MANDATORY)
    private String name;

    @NotBlank(message = Constants.EXCEPTION_USER_LAST_NAME_MANDATORY)
    private String lastName;

    @NotBlank(message = Constants.EXCEPTION_USER_PASSWORD_MANDATORY)
    @Size(min = Constants.MIN_USER_PASSWORD_CHARACTERS, message = Constants.EXCEPTION_USER_PASSWORD_MIN_LENGTH)
    private String password;

    @NotBlank(message = Constants.EXCEPTION_USER_EMAIL_MANDATORY)
    @Email(message = Constants.EXCEPTION_USER_EMAIL_INVALID)
    private String email;

    @NotBlank(message = Constants.EXCEPTION_USER_DOCUMENT_MANDATORY)
    @Pattern(regexp = Constants.DOCUMENT_REG_EXP, message = Constants.EXCEPTION_USER_DOCUMENT_NUMERIC)
    private String document;

    @NotBlank(message = Constants.EXCEPTION_USER_PHONE_MANDATORY)
    @Size(max = Constants.MAX_USER_PHONE_CHARACTERS, message = Constants.EXCEPTION_USER_PHONE_MAX_LENGTH)
    @Pattern(regexp = Constants.PHONE_REG_EXP, message = Constants.EXCEPTION_USER_PHONE_INVALID)
    private String phone;

    @NotNull(message = Constants.EXCEPTION_USER_BIRTHDATE_MANDATORY)
    private LocalDate birthDate;

    public RegisterDtoRequest(String name, String lastName, String password, String email, String document, String phone, LocalDate birthDate) {
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.document = document;
        this.phone = phone;
        this.birthDate = birthDate;
    }

    public @NotBlank(message = Constants.EXCEPTION_USER_NAME_MANDATORY) String getName() {
        return name;
    }

    public void setName(@NotBlank(message = Constants.EXCEPTION_USER_NAME_MANDATORY) String name) {
        this.name = name;
    }

    public @NotBlank(message = Constants.EXCEPTION_USER_LAST_NAME_MANDATORY) String getLastName() {
        return lastName;
    }

    public void setLastName(@NotBlank(message = Constants.EXCEPTION_USER_LAST_NAME_MANDATORY) String lastName) {
        this.lastName = lastName;
    }

    public @NotBlank(message = Constants.EXCEPTION_USER_PASSWORD_MANDATORY) @Size(min = Constants.MIN_USER_PASSWORD_CHARACTERS, message = Constants.EXCEPTION_USER_PASSWORD_MIN_LENGTH) String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = Constants.EXCEPTION_USER_PASSWORD_MANDATORY) @Size(min = Constants.MIN_USER_PASSWORD_CHARACTERS, message = Constants.EXCEPTION_USER_PASSWORD_MIN_LENGTH) String password) {
        this.password = password;
    }

    public @NotBlank(message = Constants.EXCEPTION_USER_EMAIL_MANDATORY) @Email(message = Constants.EXCEPTION_USER_EMAIL_INVALID) String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = Constants.EXCEPTION_USER_EMAIL_MANDATORY) @Email(message = Constants.EXCEPTION_USER_EMAIL_INVALID) String email) {
        this.email = email;
    }

    public @NotBlank(message = Constants.EXCEPTION_USER_DOCUMENT_MANDATORY) @Pattern(regexp = "\\d+", message = Constants.EXCEPTION_USER_DOCUMENT_NUMERIC) String getDocument() {
        return document;
    }

    public void setDocument(@NotBlank(message = Constants.EXCEPTION_USER_DOCUMENT_MANDATORY) @Pattern(regexp = "\\d+", message = Constants.EXCEPTION_USER_DOCUMENT_NUMERIC) String document) {
        this.document = document;
    }

    public @NotBlank(message = Constants.EXCEPTION_USER_PHONE_MANDATORY) @Size(max = Constants.MAX_USER_PHONE_CHARACTERS, message = Constants.EXCEPTION_USER_PHONE_MAX_LENGTH) @Pattern(regexp = "^\\+?\\d+$", message = Constants.EXCEPTION_USER_PHONE_INVALID) String getPhone() {
        return phone;
    }

    public void setPhone(@NotBlank(message = Constants.EXCEPTION_USER_PHONE_MANDATORY) @Size(max = Constants.MAX_USER_PHONE_CHARACTERS, message = Constants.EXCEPTION_USER_PHONE_MAX_LENGTH) @Pattern(regexp = "^\\+?\\d+$", message = Constants.EXCEPTION_USER_PHONE_INVALID) String phone) {
        this.phone = phone;
    }

    public @NotNull(message = Constants.EXCEPTION_USER_BIRTHDATE_MANDATORY) LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(@NotNull(message = Constants.EXCEPTION_USER_BIRTHDATE_MANDATORY) LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
