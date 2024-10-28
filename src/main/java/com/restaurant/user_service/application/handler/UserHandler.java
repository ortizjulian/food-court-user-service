package com.restaurant.user_service.application.handler;


import com.restaurant.user_service.application.dto.RegisterDtoRequest;
import com.restaurant.user_service.application.mapper.RegisterDtoRequestMapper;
import com.restaurant.user_service.domain.api.IUserServicePort;
import com.restaurant.user_service.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserHandler implements IUserHandler {

    private final IUserServicePort userServicePort;
    private final RegisterDtoRequestMapper registerDtoRequestMapper;

    @Override
    public User registerOwner(RegisterDtoRequest registerDtoRequest) {
        User user = registerDtoRequestMapper.registerDtoRequestToUser(registerDtoRequest);
        return userServicePort.registerOwner(user);
    }

    @Override
    public void registerEmployee(RegisterDtoRequest registerRequest) {
        User user = registerDtoRequestMapper.registerDtoRequestToUser(registerRequest);
        userServicePort.registerEmployee(user);
    }
}
