package com.restaurant.user_service.domain.api;


import com.restaurant.user_service.domain.model.User;

public interface IUserServicePort {
    User registerOwner(User user);
    User registerEmployee(User user);

    User registerClient(User user);
    String getUserPhone(Long userId);
}
