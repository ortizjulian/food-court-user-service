package com.restaurant.user_service.infrastructure.configuration;


import com.restaurant.user_service.domain.api.IAuthenticationServicePort;
import com.restaurant.user_service.domain.api.IUserServicePort;
import com.restaurant.user_service.domain.spi.IRolePersistencePort;
import com.restaurant.user_service.domain.spi.ISecurityPersistencePort;
import com.restaurant.user_service.domain.spi.IUserPersistencePort;
import com.restaurant.user_service.domain.usecase.AuthenticationUseCase;
import com.restaurant.user_service.domain.usecase.UserUseCase;
import com.restaurant.user_service.infrastructure.output.jpa.adapter.RoleJpaAdapter;
import com.restaurant.user_service.infrastructure.output.jpa.adapter.UserJpaAdapter;
import com.restaurant.user_service.infrastructure.output.jpa.entity.UserEntity;
import com.restaurant.user_service.infrastructure.output.jpa.mapper.RoleEntityMapper;
import com.restaurant.user_service.infrastructure.output.jpa.mapper.UserEntityMapper;
import com.restaurant.user_service.infrastructure.output.jpa.repository.IRoleRepository;
import com.restaurant.user_service.infrastructure.output.jpa.repository.IUserRepository;
import com.restaurant.user_service.infrastructure.output.security.adapter.SecurityAdapter;
import com.restaurant.user_service.infrastructure.output.security.entity.SecurityUser;
import com.restaurant.user_service.infrastructure.output.security.jwt.JwtTokenManager;
import com.restaurant.user_service.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final UserEntityMapper userEntityMapper;
    private final RoleEntityMapper roleEntityMapper;
    private final JwtTokenManager jwtTokenManager;
    private final AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public IRolePersistencePort rolePersistencePort(){
        return new RoleJpaAdapter(roleRepository,roleEntityMapper);
    }

    @Bean
    public IUserPersistencePort userPersistencePort(){
        return new UserJpaAdapter(userRepository,userEntityMapper,roleRepository);
    }

    @Bean
    public IAuthenticationServicePort authenticationServicePort() throws Exception {
        return new AuthenticationUseCase(userPersistencePort(),securityPersistencePort() );
    }

    @Bean
    public IUserServicePort userServicePort() throws Exception {
        return new UserUseCase(rolePersistencePort(),userPersistencePort(),securityPersistencePort());
    }

    @Bean
    public ISecurityPersistencePort securityPersistencePort() throws Exception {
        return new SecurityAdapter(encoder(),jwtTokenManager,authenticationManager(authenticationConfiguration));
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            UserEntity userEntity = userRepository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException(Constants.EXCEPTION_USER_NOT_FOUND));
            Set<String> role = new HashSet<>();
            role.add(userEntity.getRoleEntity().getName());
            return new SecurityUser(userEntity.getId(),userEntity.getEmail(),userEntity.getPassword(), role);
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
