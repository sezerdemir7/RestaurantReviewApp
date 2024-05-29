package org.example.restaurantreview.dto.response;


import org.example.restaurantreview.constant.UserRole;


public record UserResponse(Long id, String userName, String email, UserRole userRole)  {
}