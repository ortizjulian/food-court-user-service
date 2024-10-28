package com.restaurant.user_service.utils;

public class Constants {
    private Constants() {
        throw new UnsupportedOperationException(UTILITY_CLASS_SHOULD_NOT_BE_INSTANTIATED);
    }

    public static final String UTILITY_CLASS_SHOULD_NOT_BE_INSTANTIATED = "Utility class should not be instantiated";

    //Register Dto
    public static final int MAX_USER_PHONE_CHARACTERS = 13;
    public static final int MIN_USER_PASSWORD_CHARACTERS = 8;
    public static final String DOCUMENT_REG_EXP = "\\d+";
    public static final String PHONE_REG_EXP = "^\\+?\\d+$";

    //Exceptions messages
    public static final String RESPONSE_MESSAGE_KEY = "Message";
    public static final String EXCEPTION_INVALID_JSON_FORMAT = "Invalid JSON format";

    //User Exceptions
    public static final String EXCEPTION_USER_NOT_FOUND ="User not found";
    public static final String EXCEPTION_USER_NAME_MANDATORY = "Name is mandatory";
    public static final String EXCEPTION_USER_LAST_NAME_MANDATORY = "Last name is mandatory";
    public static final String EXCEPTION_USER_PASSWORD_MANDATORY = "Password is mandatory";
    public static final String EXCEPTION_USER_PASSWORD_MIN_LENGTH = "Password must be at least 8 characters long";
    public static final String EXCEPTION_USER_EMAIL_MANDATORY = "Email is mandatory";
    public static final String EXCEPTION_USER_EMAIL_INVALID = "Email should be valid";
    public static final String EXCEPTION_USER_DOCUMENT_MANDATORY = "Document is mandatory";
    public static final String EXCEPTION_USER_DOCUMENT_NUMERIC = "Document must contain only numbers";
    public static final String EXCEPTION_USER_PHONE_MANDATORY = "Phone is mandatory";
    public static final String EXCEPTION_USER_PHONE_MAX_LENGTH = "Phone number must be no more than 13 characters long";
    public static final String EXCEPTION_USER_PHONE_INVALID = "Phone must contain only numbers and an optional leading '+'";
    public static final String EXCEPTION_USER_BIRTHDATE_MANDATORY = "Birthdate is mandatory";
    public static final String EXCEPTION_AGE_NOT_VALID= "User must be at least 18 years old.";
    public static final String EXCEPTION_DOCUMENT_ALREADY_EXISTS = "Document already exists: ";
    public static final String EXCEPTION_EMAIL_ALREADY_EXISTS = "Email already exists: ";
    //Role Exceptions
    public static final String EXCEPTION_ROLE_NOT_FOUND = "Role not found";

}