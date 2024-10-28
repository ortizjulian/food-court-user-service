package com.restaurant.user_service.infrastructure.output.jpa.repository;

import com.restaurant.user_service.infrastructure.output.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByEmail(String name);
    Boolean existsByEmail(String email);
    Boolean existsByDocument(String document);
}
