package com.restaurant.user_service.domain.usecase;

import com.restaurant.user_service.domain.api.IAuthenticationServicePort;
import com.restaurant.user_service.domain.exceptions.UserNotFoundException;
import com.restaurant.user_service.domain.model.Authentication;
import com.restaurant.user_service.domain.model.Login;
import com.restaurant.user_service.domain.model.User;
import com.restaurant.user_service.domain.spi.ISecurityPersistencePort;
import com.restaurant.user_service.domain.spi.IUserPersistencePort;

import java.util.Optional;

public class AuthenticationUseCase implements IAuthenticationServicePort {

    private IUserPersistencePort userPersistencePort;
    private ISecurityPersistencePort securityPersistencePort;

    public AuthenticationUseCase(IUserPersistencePort userPersistencePort, ISecurityPersistencePort securityPersistencePort) {
        this.userPersistencePort = userPersistencePort;
        this.securityPersistencePort = securityPersistencePort;
    }

    @Override
    public Authentication login(Login login) {
        Optional<User> authUser= userPersistencePort.findByEmail(login.getEmail());

        if(authUser.isEmpty()) {
            throw new UserNotFoundException();
        }

        securityPersistencePort.authenthicate(login);

        return securityPersistencePort.getToken(authUser.get());

    }
}
