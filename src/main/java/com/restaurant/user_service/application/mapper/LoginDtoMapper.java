package com.restaurant.user_service.application.mapper;

import com.restaurant.user_service.application.dto.LoginDto;
import com.restaurant.user_service.domain.model.Login;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface LoginDtoMapper {
    Login toLogin(LoginDto loginDto);
}
