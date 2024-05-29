package org.example.restaurantreview.service;

import org.example.restaurantreview.constant.RestaurantType;
import org.example.restaurantreview.dto.response.RestaurantResponse;
import org.example.restaurantreview.entity.Restaurant;
import org.example.restaurantreview.exception.RestaurantNotFoundException;
import org.example.restaurantreview.repository.RestaurantRepository;
import org.example.restaurantreview.service.impl.RestaurantServiceImpl;
import org.example.restaurantreview.service.impl.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RestaurantServiceImplTest {

    private RestaurantServiceImpl restaurantServiceImpl;
    private UserServiceImpl userServiceImpl;
    private RestaurantRepository restaurantRepository;

    @BeforeEach
    void setUp() {
        restaurantRepository = Mockito.mock(RestaurantRepository.class);
        userServiceImpl = Mockito.mock(UserServiceImpl.class);
        restaurantServiceImpl = new RestaurantServiceImpl(restaurantRepository, userServiceImpl);
    }

    @Test
    void whenShouldReturnAllRestaurants() {
        Restaurant restaurant1 = new Restaurant();
        restaurant1.setId(1L);
        restaurant1.setName("Restaurant 1");
        restaurant1.setType(RestaurantType.DONERCI);
        restaurant1.setAddress("test address");
        restaurant1.setAverageRaiting(9);

        Restaurant restaurant2 = new Restaurant();
        restaurant2.setId(2L);
        restaurant2.setName("Restaurant 2");
        restaurant2.setType(RestaurantType.DONERCI);
        restaurant2.setAddress("test address");
        restaurant2.setAverageRaiting(9);

        List<Restaurant> restaurantList = Arrays.asList(restaurant1, restaurant2);

        RestaurantResponse response1 = new RestaurantResponse(restaurant1.getId(), restaurant1.getName(),restaurant1.getType().getType(),restaurant1.getAddress(),restaurant1.getAverageRaiting());
        RestaurantResponse response2 = new RestaurantResponse(restaurant2.getId(), restaurant2.getName(), restaurant1.getType().getType(),restaurant2.getAddress(),restaurant2.getAverageRaiting());

        List<RestaurantResponse> expectedResponseList = Arrays.asList(response1, response2);

        when(restaurantRepository.findAll()).thenReturn(restaurantList);

        List<RestaurantResponse> result = restaurantServiceImpl.getAllRestaurant();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(expectedResponseList, result);

        verify(restaurantRepository).findAll();
    }

    @Test
    void whenRestaurantExists_thenReturnRestaurant() {
        Long restaurantId = 1L;
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantId);
        restaurant.setName("Test Restaurant");

        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(restaurant));

        Restaurant result = restaurantServiceImpl.getRestaurantByRestaurantId(restaurantId);

        assertNotNull(result);
        assertEquals(restaurantId, result.getId());
        assertEquals("Test Restaurant", result.getName());
        verify(restaurantRepository).findById(restaurantId);
    }

    @Test
    void whenRestaurantDoesNotExist_thenThrowException() {
        Long restaurantId = 1L;

        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.empty());

        RestaurantNotFoundException exception = assertThrows(RestaurantNotFoundException.class, () -> {
            restaurantServiceImpl.getRestaurantByRestaurantId(restaurantId);
        });

        assertEquals("restaurant not found id:" + restaurantId, exception.getMessage());
        verify(restaurantRepository).findById(restaurantId);
    }

    @AfterEach
    void tearDown() {

    }

}