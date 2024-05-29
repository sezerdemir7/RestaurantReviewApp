package org.example.restaurantreview.service;

import org.example.restaurantreview.dto.request.CreateUserReq;
import org.example.restaurantreview.dto.response.UserResponse;
import org.example.restaurantreview.entity.User;

import java.util.List;

public interface UserService {

    UserResponse createUser(CreateUserReq request) ;

    void deleteUser(Long userId);

    User getUserById(Long userId) ;

    UserResponse getUserByUserId(Long id);

    List<UserResponse> getAllUser();
}