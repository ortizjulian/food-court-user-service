package com.restaurant.user_service.application.handler;


import com.restaurant.user_service.application.dto.RegisterDtoRequest;
import com.restaurant.user_service.application.dto.UserPhoneResponseDto;

public interface IUserHandler {
    void registerOwner(RegisterDtoRequest registerDtoRequest);
    void registerEmployee(RegisterDtoRequest registerRequest);
    void registerClient(RegisterDtoRequest registerRequest);
    UserPhoneResponseDto getUserPhone(Long userId);
}
