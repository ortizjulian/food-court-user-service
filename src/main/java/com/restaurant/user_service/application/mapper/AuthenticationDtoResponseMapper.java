package com.restaurant.user_service.application.mapper;

import com.restaurant.user_service.application.dto.AuthenticationDtoResponse;
import com.restaurant.user_service.domain.model.Authentication;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AuthenticationDtoResponseMapper {
    AuthenticationDtoResponse authenticationToAuthenticationDtoResponse(Authentication authentication);
}
