package com.restaurant.user_service.application.handler;


import com.restaurant.user_service.application.dto.RegisterDtoRequest;

public interface IUserHandler {
    void registerOwner(RegisterDtoRequest registerDtoRequest);
    void registerEmployee(RegisterDtoRequest registerRequest);

    void registerClient(RegisterDtoRequest registerRequest);
}
