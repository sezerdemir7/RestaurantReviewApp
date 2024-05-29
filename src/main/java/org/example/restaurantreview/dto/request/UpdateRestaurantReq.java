package org.example.restaurantreview.dto.request;


import org.example.restaurantreview.constant.RestaurantType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateRestaurantReq(
        @NotNull(message = "Restaurant ID cannot be null") Long id,
        @NotBlank(message = "Restaurant name cannot be blank") String name,
        @NotNull(message = "Restaurant type cannot be null") RestaurantType type,
        @NotBlank(message = "Address cannot be blank") String address) {
}