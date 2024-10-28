package com.restaurant.user_service.domain.spi;


import com.restaurant.user_service.domain.model.Role;

import java.util.Optional;

public interface IRolePersistencePort {
    Optional<Role> findByName(String roleName);
}
