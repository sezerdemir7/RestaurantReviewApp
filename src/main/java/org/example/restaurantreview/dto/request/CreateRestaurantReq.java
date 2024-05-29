package org.example.restaurantreview.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.restaurantreview.constant.RestaurantType;

public record CreateRestaurantReq(
        @NotBlank(message = "Restaurant name cannot be blank") String name,
        @NotNull(message = "Restaurant type cannot be null") RestaurantType type,
        @NotBlank(message = "Address cannot be blank") String address,
        @NotNull(message = "User ID cannot be null") Long userId) {
}