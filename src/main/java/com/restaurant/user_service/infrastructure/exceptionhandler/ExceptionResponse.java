package com.restaurant.user_service.infrastructure.exceptionhandler;


import com.restaurant.user_service.utils.Constants;

public enum ExceptionResponse {
    INVALID_JSON_FORMAT(Constants.EXCEPTION_INVALID_JSON_FORMAT),
    AGE_NOT_VALID(Constants.EXCEPTION_AGE_NOT_VALID),
    ROLE_NOT_FOUND(Constants.EXCEPTION_ROLE_NOT_FOUND);

    private String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}