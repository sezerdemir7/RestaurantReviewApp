package org.example.restaurantreview.service.impl;

import org.example.restaurantreview.dto.request.CreateUserReq;
import org.example.restaurantreview.dto.response.UserResponse;
import org.example.restaurantreview.entity.User;
import org.example.restaurantreview.exception.UserCommentAlreadyExistsException;
import org.example.restaurantreview.exception.UserNotFoundException;
import org.example.restaurantreview.repository.UserRepository;
import org.example.restaurantreview.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(CreateUserReq request){
        Optional<User> existingEmail = userRepository.findByEmail(request.email());
        if (existingEmail.isPresent()) {
            throw new UserCommentAlreadyExistsException("E-mail already exists");
        }
        User saveUser=mapToUser(request);
        User newUser=userRepository.save(saveUser);
        return mapToResponse(newUser);
    }
    public void deleteUser(Long userId){
        userRepository.deleteById(userId);
    }
    public User getUserById(Long userId){

        User user=userRepository.findById(userId).orElseThrow(()->
                new UserNotFoundException("User not found id:"+userId));
        return user;
    }
    public UserResponse getUserByUserId(Long id) {
        User user=getUserById(id);
        return mapToResponse(user);
    }

    public List<UserResponse> getAllUser(){
        List<User> userList=userRepository.findAll();
        List<UserResponse> responseList=new ArrayList<>();
        for(User user:userList){
            responseList.add(mapToResponse(user));
        }
        return responseList;
    }


    private User mapToUser(CreateUserReq request){
        User user=new User();
        user.setUserName(request.userName());
        user.setEmail(request.email());
        user.setPassword(request.password());
        user.setRole(request.userRole());

        return user;
    }

    private UserResponse mapToResponse(User user){
        UserResponse response=new UserResponse(user.getId(),user.getUserName(), user.getEmail(),user.getRole());
        return response;
    }


}
