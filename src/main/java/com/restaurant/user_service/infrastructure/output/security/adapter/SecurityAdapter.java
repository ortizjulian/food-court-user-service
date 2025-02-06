package com.restaurant.user_service.infrastructure.output.security.adapter;


import com.restaurant.user_service.domain.model.Authentication;
import com.restaurant.user_service.domain.model.Login;
import com.restaurant.user_service.domain.model.User;
import com.restaurant.user_service.domain.spi.ISecurityPersistencePort;
import com.restaurant.user_service.infrastructure.exceptions.AuthenticationException;
import com.restaurant.user_service.infrastructure.exceptions.InvalidCredentialsException;
import com.restaurant.user_service.infrastructure.output.security.entity.SecurityUser;
import com.restaurant.user_service.infrastructure.output.security.jwt.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;


@RequiredArgsConstructor
public class SecurityAdapter implements ISecurityPersistencePort {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenManager jwtTokenManager;
    private final AuthenticationManager authenticationManager;

    @Override
    public String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public Authentication getToken(User user) {
        Set<String> role = new HashSet<>();
        role.add(user.getRole().getName());
        SecurityUser securityUser = new SecurityUser(user.getId(),user.getEmail(),null, user.getPhone(), role);
        return new Authentication(jwtTokenManager.getToken(securityUser));
    }

    @Override
    public void authenthicate(Login login){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            login.getEmail(),
                            login.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new InvalidCredentialsException();
        } catch (Exception e) {
            throw new AuthenticationException();
        }
    }
}
