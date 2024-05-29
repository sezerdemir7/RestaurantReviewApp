package org.example.restaurantreview.repository;

import org.example.restaurantreview.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {


    List<Restaurant> findByOwnerId(Long ownerId);
}