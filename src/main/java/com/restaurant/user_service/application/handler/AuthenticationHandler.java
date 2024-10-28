package com.restaurant.user_service.application.handler;


import com.restaurant.user_service.application.dto.AuthenticationDtoResponse;
import com.restaurant.user_service.application.dto.LoginDto;
import com.restaurant.user_service.application.mapper.AuthenticationDtoResponseMapper;
import com.restaurant.user_service.application.mapper.LoginDtoMapper;
import com.restaurant.user_service.domain.api.IAuthenticationServicePort;
import com.restaurant.user_service.domain.model.Login;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationHandler implements IAuthenticationHandler {

    private final IAuthenticationServicePort authenticationServicePort;
    private final AuthenticationDtoResponseMapper authenticationDtoResponseMapper;
    private final LoginDtoMapper loginDtoMapper;

    @Override
    public AuthenticationDtoResponse login(LoginDto loginDto) {
        Login login = loginDtoMapper.toLogin(loginDto);
        return authenticationDtoResponseMapper.authenticationToAuthenticationDtoResponse(authenticationServicePort.login(login));
    }
}
