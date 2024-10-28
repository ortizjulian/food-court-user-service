package com.restaurant.user_service.domain.spi;

public interface ISecurityPersistencePort {
    String encryptPassword(String password);
}
