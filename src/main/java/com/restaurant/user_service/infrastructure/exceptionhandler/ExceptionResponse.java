package com.restaurant.user_service.infrastructure.exceptionhandler;


import com.restaurant.user_service.utils.Constants;
import com.restaurant.user_service.utils.SecurityConstants;

public enum ExceptionResponse {
    INVALID_JSON_FORMAT(Constants.EXCEPTION_INVALID_JSON_FORMAT),
    AGE_NOT_VALID(Constants.EXCEPTION_AGE_NOT_VALID),
    ROLE_NOT_FOUND(Constants.EXCEPTION_ROLE_NOT_FOUND),
    USER_NOT_FOUND(Constants.EXCEPTION_USER_NOT_FOUND),
    INVALID_CREDENTIALS(SecurityConstants.EXCEPTION_INVALID_CREDENTIALS),
    AUTHENTICATION_EXCEPTION(SecurityConstants.EXCEPTION_AUTHENTICATION_EXCEPTION);

    private String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}