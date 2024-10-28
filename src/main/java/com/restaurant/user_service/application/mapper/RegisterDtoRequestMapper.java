package com.restaurant.user_service.application.mapper;


import com.restaurant.user_service.application.dto.RegisterDtoRequest;
import com.restaurant.user_service.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel ="spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RegisterDtoRequestMapper {
    User registerDtoRequestToUser(RegisterDtoRequest registerDtoRequest);
}
