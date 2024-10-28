package com.restaurant.user_service.application.handler;

import com.restaurant.user_service.application.dto.AuthenticationDtoResponse;
import com.restaurant.user_service.application.dto.LoginDto;

public interface IAuthenticationHandler {
    AuthenticationDtoResponse login(LoginDto loginDto);
}
