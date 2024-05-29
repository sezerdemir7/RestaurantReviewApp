package org.example.restaurantreview.controller;

import jakarta.validation.Valid;
import org.example.restaurantreview.dto.request.CreateRestaurantReq;
import org.example.restaurantreview.dto.request.UpdateRestaurantReq;
import org.example.restaurantreview.dto.response.RestaurantResponse;
import org.example.restaurantreview.service.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/restaurant")
public class RestaurantController {
    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<RestaurantResponse>> getAllRestaurant() {
        return new ResponseEntity<>(restaurantService.getAllRestaurant(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponse> getRestaurantById(@PathVariable Long id) {
        return new ResponseEntity<>(restaurantService.getRestaurantById(id), HttpStatus.OK);
    }

    @GetMapping("/getRestaurantOwnerId/{id}")
    public ResponseEntity<List<RestaurantResponse>> getAllRestaurantByOwnerId(@PathVariable Long id) {
        return new ResponseEntity<>(restaurantService.getRestaurantByOwnerId(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<RestaurantResponse> createRestaurant(@RequestBody @Valid CreateRestaurantReq request) {
        return new ResponseEntity<>(restaurantService.createRestaurant(request), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<RestaurantResponse> updateRestaurant(@RequestBody @Valid UpdateRestaurantReq request) {
        return new ResponseEntity<>(restaurantService.updateRestaurant(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteRestaurantById(@RequestParam Long userId, @RequestParam Long restaurantId) {
        restaurantService.deleteRestaurant(userId, restaurantId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
