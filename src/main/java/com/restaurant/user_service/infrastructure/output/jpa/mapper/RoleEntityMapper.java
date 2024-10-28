package com.restaurant.user_service.infrastructure.output.jpa.mapper;

import com.restaurant.user_service.domain.model.Role;
import com.restaurant.user_service.infrastructure.output.jpa.entity.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RoleEntityMapper {
    Role toRole(RoleEntity roleEntity);
}
