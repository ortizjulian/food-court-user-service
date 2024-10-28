package com.restaurant.user_service.infrastructure.input.rest;

import com.restaurant.user_service.application.dto.AuthenticationDtoResponse;
import com.restaurant.user_service.application.dto.LoginDto;
import com.restaurant.user_service.application.handler.IAuthenticationHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Validated
public class AuthenticateRestController {
    private final IAuthenticationHandler authenticationHandler;

    @Operation(
            summary = "Login",
            description = "This endpoint allows to login."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logged in successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request, check the submitted data"),
            @ApiResponse(responseCode = "401", description = "Invalid Credentials"),
    })
    @PostMapping("/login")
    public ResponseEntity<AuthenticationDtoResponse> login(@Valid @RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(authenticationHandler.login(loginDto));
    }
}
