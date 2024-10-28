package com.restaurant.user_service.infrastructure.output.jpa.mapper;


import com.restaurant.user_service.domain.model.User;
import com.restaurant.user_service.infrastructure.output.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface UserEntityMapper {
    @Mapping(target = "roleEntity", ignore = true)
    UserEntity toEntity(User article);

    @Mapping(source = "roleEntity", target = "role")
    User toUser(UserEntity userEntity);
}
