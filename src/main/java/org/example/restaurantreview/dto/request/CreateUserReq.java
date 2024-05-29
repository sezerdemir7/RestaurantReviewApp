package org.example.restaurantreview.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.restaurantreview.constant.UserRole;


public record CreateUserReq(
        @NotBlank(message = "Username cannot be blank") String userName,
        @NotBlank(message = "Email cannot be blank")
        @Email(message = "Invalid email address") String email,
        @NotBlank(message = "Password cannot be blank") String password,
        @NotNull(message = "User role cannot be null") UserRole userRole){
}