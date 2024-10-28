package com.restaurant.user_service.domain.usecase;

import com.restaurant.user_service.domain.exceptions.UserNotFoundException;
import com.restaurant.user_service.domain.model.Authentication;
import com.restaurant.user_service.domain.model.Login;
import com.restaurant.user_service.domain.model.User;
import com.restaurant.user_service.domain.spi.ISecurityPersistencePort;
import com.restaurant.user_service.domain.spi.IUserPersistencePort;
import com.restaurant.user_service.infrastructure.exceptions.InvalidCredentialsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticationUseCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort;

    @Mock
    private ISecurityPersistencePort securityPersistencePort;

    @InjectMocks
    private AuthenticationUseCase authenticationUseCase;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void AuthenticationUseCase_Login_WhenValidCredentials_ShouldReturnToken(){
        Login login = new Login("julianito@ortixs@gmail.com", "12345678");
        User user = new User(
                null,
                "Julian",
                "Ortiz",
                "12345678",
                "julianito@ortixs@gmail.com",
                "10203040",
                "192939",
                LocalDate.of(2020, 10, 10),
                null
        );

        Mockito.when(userPersistencePort.findByEmail(login.getEmail())).thenReturn(Optional.of(user));
        Mockito.doNothing().when(securityPersistencePort).authenthicate(login);

        Authentication authentication = new Authentication("GeneratedToken");

        Mockito.when(securityPersistencePort.getToken(user)).thenReturn(authentication);

        Authentication result = authenticationUseCase.login(login);

        assertNotNull(result.getToken());
        assertEquals("GeneratedToken", result.getToken());
    }

    @Test
    void AuthenticationUseCase_Login_WhenEmailDoesNotExists_ShouldThrowExceptionUserNotFound(){
        Login login = new Login("julianito@ortixs@gmail.com", "12345678");

        Mockito.when(userPersistencePort.findByEmail(login.getEmail())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            authenticationUseCase.login(login);
        });
    }

    @Test
    void AuthenticationUseCase_Login_WhenWrongPassword_ShouldThrowExceptionInvalidCredentials(){
        Login login = new Login("julianito@ortixs@gmail.com", "12345678");

        Mockito.when(userPersistencePort.findByEmail(login.getEmail())).thenReturn(Optional.empty());

        User user = new User(
                null,
                "Julian",
                "Ortiz",
                "SEGURA",
                "julianito@ortixs@gmail.com",
                "10203040",
                "192939",
                LocalDate.of(2020, 10, 10),
                null
        );

        Mockito.when(userPersistencePort.findByEmail(login.getEmail())).thenReturn(Optional.of(user));
        Mockito.doThrow(InvalidCredentialsException.class).when(securityPersistencePort).authenthicate(login);

        assertThrows(InvalidCredentialsException.class, () -> {
            authenticationUseCase.login(login);
        });
    }
}