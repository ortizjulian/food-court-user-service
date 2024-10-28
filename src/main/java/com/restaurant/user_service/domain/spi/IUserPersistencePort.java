package com.restaurant.user_service.domain.spi;


import com.restaurant.user_service.domain.model.User;

import java.util.Optional;

public interface IUserPersistencePort {
    Boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    User register(User user);
    Boolean existsByDocument(String document);
}
