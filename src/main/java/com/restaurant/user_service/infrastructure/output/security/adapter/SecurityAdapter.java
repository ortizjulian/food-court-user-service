package com.restaurant.user_service.infrastructure.output.security.adapter;


import com.restaurant.user_service.domain.spi.ISecurityPersistencePort;
import com.restaurant.user_service.infrastructure.output.security.jwt.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;


@RequiredArgsConstructor
public class SecurityAdapter implements ISecurityPersistencePort {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenManager jwtTokenManager;
    private final AuthenticationManager authenticationManager;

    @Override
    public String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
