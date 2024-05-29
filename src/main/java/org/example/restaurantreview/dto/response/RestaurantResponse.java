package org.example.restaurantreview.dto.response;


public record RestaurantResponse(Long id, String name, String type, String address,double averageRaiting)  {
}