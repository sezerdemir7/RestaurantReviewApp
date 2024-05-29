package org.example.restaurantreview.service;

import org.example.restaurantreview.dto.request.CreateRestaurantReq;
import org.example.restaurantreview.dto.request.UpdateRestaurantReq;
import org.example.restaurantreview.dto.response.RestaurantResponse;
import org.example.restaurantreview.entity.Restaurant;

import java.util.List;

public interface RestaurantService {
    RestaurantResponse createRestaurant(CreateRestaurantReq request);

    RestaurantResponse updateRestaurant(UpdateRestaurantReq request);

    void updateRestaurantAverageRating(Long restaurantId, double averageRating) ;

    List<RestaurantResponse> getAllRestaurant();

    Restaurant getRestaurantByRestaurantId(Long id) ;

    RestaurantResponse getRestaurantById(Long id) ;
    List<RestaurantResponse> getRestaurantByOwnerId(Long id);

    void deleteRestaurant(Long userId, Long restaurantId) ;
}
