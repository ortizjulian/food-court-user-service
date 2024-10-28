package com.restaurant.user_service.infrastructure.output.jpa.adapter;


import com.restaurant.user_service.domain.model.Role;
import com.restaurant.user_service.domain.spi.IRolePersistencePort;
import com.restaurant.user_service.infrastructure.output.jpa.entity.RoleEntity;
import com.restaurant.user_service.infrastructure.output.jpa.mapper.RoleEntityMapper;
import com.restaurant.user_service.infrastructure.output.jpa.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class RoleJpaAdapter implements IRolePersistencePort {
    private final IRoleRepository roleRepository;
    private final RoleEntityMapper roleEntityMapper;

    @Override
    public Optional<Role> findByName(String roleName) {
        Optional<RoleEntity> roleEntityOptional = roleRepository.findByName(roleName);

        if (roleEntityOptional.isPresent()) {
            RoleEntity roleEntity = roleEntityOptional.get();
            Role role = roleEntityMapper.toRole(roleEntity);
            return Optional.of(role);
        } else {
            return Optional.empty();
        }
    }
}
