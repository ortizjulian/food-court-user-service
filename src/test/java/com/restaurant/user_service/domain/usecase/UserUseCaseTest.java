package com.restaurant.user_service.domain.usecase;

import com.restaurant.user_service.domain.exceptions.AgeNotValidException;
import com.restaurant.user_service.domain.exceptions.DocumentAlreadyExistsException;
import com.restaurant.user_service.domain.exceptions.EmailAlreadyExistsException;
import com.restaurant.user_service.domain.exceptions.UserNotFoundException;
import com.restaurant.user_service.domain.model.Role;
import com.restaurant.user_service.domain.model.User;
import com.restaurant.user_service.domain.spi.IRolePersistencePort;
import com.restaurant.user_service.domain.spi.ISecurityPersistencePort;
import com.restaurant.user_service.domain.spi.IUserPersistencePort;
import com.restaurant.user_service.utils.SecurityConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class UserUseCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort;

    @Mock
    private IRolePersistencePort rolePersistencePort;

    @Mock
    private ISecurityPersistencePort securityPersistencePort;

    @InjectMocks
    private UserUseCase userUseCase;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void UserUseCase_RegisterOwner_WhenCalled_ShouldCallSaveOnPersistencePort() {
        User user = new User(
                null,
                "Julian",
                "Ortiz",
                "SEGURA",
                "julian.ortiz@gmail.com",
                "10203040",
                "192939",
                LocalDate.of(2000, 10, 10),
                null
        );
        Mockito.when(userPersistencePort.existsByEmail("julian.ortiz@gmail.com")).thenReturn(false);
        Mockito.when(userPersistencePort.existsByDocument("10203040")).thenReturn(false);
        Mockito.when(securityPersistencePort.encryptPassword("SEGURA")).thenReturn("encryptedPassword");
        Mockito.when(userPersistencePort.register(user)).thenReturn(user);
        Mockito.when(rolePersistencePort.findByName(SecurityConstants.ROLE_OWNER)).thenReturn(Optional.of(new Role(1L, "Propietario", "Rol Propietario")));

        User registeredUser = userUseCase.registerOwner(user);
        assertEquals(user, registeredUser);
    }

    @Test
    void UserUseCase_RegisterOwner_WhenUserIsNotAdult_ShouldThrowExceptionAgeNotValid() {
        User user = new User(
                null,
                "Julian",
                "Ortiz",
                "SEGURA",
                "julian.ortiz@gmail.com",
                "10203040",
                "192939",
                LocalDate.of(2020, 10, 10),
                null
        );

        Mockito.when(userPersistencePort.existsByEmail("julian.ortiz@gmail.com")).thenReturn(false);
        Mockito.when(userPersistencePort.existsByDocument("10203040")).thenReturn(false);

        assertThrows(AgeNotValidException.class, () -> {
            userUseCase.registerOwner(user);
        });

    }

    @Test
    void UserUseCase_RegisterOwner_WhenEmailAlreadyExists_ShouldThrowExceptionEmailAlreadyExists() {
        User user = new User(
                null,
                "Julian",
                "Ortiz",
                "SEGURA",
                "julian.ortiz@gmail.com",
                "10203040",
                "192939",
                LocalDate.of(2000, 10, 10),
                null
        );

        Mockito.when(userPersistencePort.existsByEmail("julian.ortiz@gmail.com")).thenReturn(true);

       assertThrows(EmailAlreadyExistsException.class, () -> {
           userUseCase.registerOwner(user);
        });
    }

    @Test
    void UserUseCase_RegisterOwner_WhenDocumentAlreadyExists_ShouldThrowExceptionExceptionDocumentAlreadyExists() {
        User user = new User(
                null,
                "Julian",
                "Ortiz",
                "SEGURA",
                "julian.ortiz@gmail.com",
                "10203040",
                "192939",
                LocalDate.of(2000, 10, 10),
                null
        );

        Mockito.when(userPersistencePort.existsByEmail("julian.ortiz@gmail.com")).thenReturn(false);
        Mockito.when(userPersistencePort.existsByDocument("10203040")).thenReturn(true);

        assertThrows(DocumentAlreadyExistsException.class, () -> {
            userUseCase.registerOwner(user);
        });

    }

    @Test
    void UserUseCase_RegisterEmployee_WhenCalled_ShouldCallSaveOnPersistencePort() {
        User user = new User(
                null,
                "Julian",
                "Ortiz",
                "SEGURA",
                "julian.empleado@gmail.com",
                "10203040",
                "192939",
                LocalDate.of(2000, 10, 10),
                null
        );
        Mockito.when(userPersistencePort.existsByEmail("julian.empleado@gmail.com")).thenReturn(false);
        Mockito.when(userPersistencePort.existsByDocument("10203040")).thenReturn(false);
        Mockito.when(securityPersistencePort.encryptPassword("SEGURA")).thenReturn("encryptedPassword");
        Mockito.when(userPersistencePort.register(user)).thenReturn(user);
        Mockito.when(rolePersistencePort.findByName(SecurityConstants.ROLE_EMPLOYEE)).thenReturn(Optional.of(new Role(1L, "Empleado", "Rol Empleado")));

        User registeredUser = userUseCase.registerEmployee(user);
        assertEquals(user, registeredUser);
    }

    @Test
    void UserUseCase_RegisterClient_WhenCalled_ShouldCallSaveOnPersistencePort() {
        User user = new User(
                null,
                "Julian",
                "Ortiz",
                "SEGURA",
                "julian.cliente@gmail.com",
                "10203040",
                "192939",
                LocalDate.of(2000, 10, 10),
                null
        );
        Mockito.when(userPersistencePort.existsByEmail("julian.cliente@gmail.com")).thenReturn(false);
        Mockito.when(userPersistencePort.existsByDocument("10203040")).thenReturn(false);
        Mockito.when(securityPersistencePort.encryptPassword("SEGURA")).thenReturn("encryptedPassword");
        Mockito.when(userPersistencePort.register(user)).thenReturn(user);
        Mockito.when(rolePersistencePort.findByName(SecurityConstants.ROLE_CLIENT)).thenReturn(Optional.of(new Role(1L, "Cliente", "Rol Cliente")));

        User registeredUser = userUseCase.registerClient(user);
        assertEquals(user, registeredUser);
    }

    @Test
    void UserUseCase_GetUserPhone_WhenUserNotFound_ShouldThrowUserNotFoundException(){
        Mockito.when(userPersistencePort.findById(5L)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> {
            userUseCase.getUserPhone(5L);
        });
    }

    @Test
    void UserUseCase_GetUserPhone_WhenUserFound_ShouldReturnUserPhone() {
        Long userId = 5L;
        String expectedPhone = "123456789";
        User user = new User();
        user.setId(userId);
        user.setPhone(expectedPhone);

        Mockito.when(userPersistencePort.findById(userId)).thenReturn(Optional.of(user));

        String actualPhone = userUseCase.getUserPhone(userId);

        assertEquals(expectedPhone, actualPhone);
    }

}