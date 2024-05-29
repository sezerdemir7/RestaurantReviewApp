package org.example.restaurantreview.controller;

import jakarta.validation.Valid;
import org.example.restaurantreview.dto.request.CreateUserReq;
import org.example.restaurantreview.dto.response.UserResponse;
import org.example.restaurantreview.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/create")
    public ResponseEntity<UserResponse> saveUser(@RequestBody @Valid CreateUserReq request) {
        return new ResponseEntity<>(userService.createUser(request), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> getAllUser() {
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserByUserId(id), HttpStatus.FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
