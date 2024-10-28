package com.restaurant.user_service.infrastructure.input.rest;

import com.restaurant.user_service.application.dto.RegisterDtoRequest;
import com.restaurant.user_service.application.handler.IUserHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
@Validated
public class UserRestController {

    private final IUserHandler userHandler;

    @Operation(
            summary = "Register a new owner",
            description = "This endpoint allows the registration of a new owner."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully registered"),
            @ApiResponse(responseCode = "404", description = "Role not found"),
            @ApiResponse(responseCode = "400", description = "Invalid request, check the submitted data"),
            @ApiResponse(responseCode = "409", description = "Email or document already exists")
    })
    @PostMapping("/register/owner")
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterDtoRequest registerRequest) {
        userHandler.registerOwner(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



}
