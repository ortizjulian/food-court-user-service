package com.restaurant.user_service.application.handler;


import com.restaurant.user_service.application.dto.RegisterDtoRequest;
import com.restaurant.user_service.domain.model.User;

public interface IUserHandler {
    User registerOwner(RegisterDtoRequest registerDtoRequest);
}
