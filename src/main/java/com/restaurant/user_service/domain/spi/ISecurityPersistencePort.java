package com.restaurant.user_service.domain.spi;

import com.restaurant.user_service.domain.model.Authentication;
import com.restaurant.user_service.domain.model.Login;
import com.restaurant.user_service.domain.model.User;

public interface ISecurityPersistencePort {
    String encryptPassword(String password);
    Authentication getToken(User user);
    void authenthicate(Login login);
}
