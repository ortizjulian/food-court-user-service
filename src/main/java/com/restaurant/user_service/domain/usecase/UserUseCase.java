package com.restaurant.user_service.domain.usecase;



import com.restaurant.user_service.domain.api.IUserServicePort;
import com.restaurant.user_service.domain.exceptions.AgeNotValidException;
import com.restaurant.user_service.domain.exceptions.DocumentAlreadyExistsException;
import com.restaurant.user_service.domain.exceptions.EmailAlreadyExistsException;
import com.restaurant.user_service.domain.exceptions.RoleNotFoundException;
import com.restaurant.user_service.domain.model.Role;
import com.restaurant.user_service.domain.model.User;
import com.restaurant.user_service.domain.spi.IRolePersistencePort;
import com.restaurant.user_service.domain.spi.ISecurityPersistencePort;
import com.restaurant.user_service.domain.spi.IUserPersistencePort;
import com.restaurant.user_service.utils.Constants;
import com.restaurant.user_service.utils.SecurityConstants;
import java.time.LocalDate;
import java.time.Period;

public class UserUseCase implements IUserServicePort {

    private IRolePersistencePort rolePersistencePort;
    private IUserPersistencePort userPersistencePort;
    private ISecurityPersistencePort securityPersistencePort;

    public UserUseCase( IRolePersistencePort rolePersistencePort, IUserPersistencePort userPersistencePort, ISecurityPersistencePort securityPersistencePort) {
        this.rolePersistencePort = rolePersistencePort;
        this.userPersistencePort = userPersistencePort;
        this.securityPersistencePort = securityPersistencePort;
    }

    private User register(User user, String roleName) {
        if (userPersistencePort.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistsException(Constants.EXCEPTION_EMAIL_ALREADY_EXISTS + user.getEmail());
        }

        if (userPersistencePort.existsByDocument(user.getDocument())) {
            throw new DocumentAlreadyExistsException(Constants.EXCEPTION_DOCUMENT_ALREADY_EXISTS + user.getDocument());
        }

        Boolean isAdult = Period.between(user.getBirthDate(), LocalDate.now()).getYears() >= 18;

        if(!isAdult) {
            throw new AgeNotValidException();
        }
        Role role = rolePersistencePort.findByName(roleName).orElseThrow(RoleNotFoundException::new);

        user.setRole(role);

        user.setPassword(securityPersistencePort.encryptPassword(user.getPassword()));
        return userPersistencePort.register(user);
    }

    @Override
    public User registerOwner(User user) {
        return this.register(user, SecurityConstants.ROLE_OWNER);
    }

    @Override
    public User registerEmployee(User user) {
       return this.register(user, SecurityConstants.ROLE_EMPLOYEE);
    }

    @Override
    public User registerClient(User user) {
        return this.register(user, SecurityConstants.ROLE_CLIENT);
    }

}
