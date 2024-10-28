package com.restaurant.user_service.domain.api;

import com.restaurant.user_service.domain.model.Authentication;
import com.restaurant.user_service.domain.model.Login;

public interface IAuthenticationServicePort {
    Authentication login(Login login);

}
