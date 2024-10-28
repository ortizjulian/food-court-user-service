package com.restaurant.user_service.domain.spi;


import com.restaurant.user_service.domain.model.Role;

import java.util.Optional;

public interface IRolePersistencePort {
    Boolean existsById(Long roleId);
    Optional<Role> findByName(String roleName);
}
