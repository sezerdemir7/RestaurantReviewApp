package org.example.restaurantreview.service;

import org.example.restaurantreview.constant.UserRole;
import org.example.restaurantreview.dto.response.UserResponse;
import org.example.restaurantreview.entity.User;
import org.example.restaurantreview.repository.UserRepository;
import org.example.restaurantreview.service.impl.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    private UserServiceImpl userServiceImpl;
    private UserRepository userRepository;

    @BeforeEach
    void setUp(){
        userRepository = Mockito.mock(UserRepository.class);
        userServiceImpl = new UserServiceImpl(userRepository);
    }

    @Test
    void whenShouldReturnAllUser() {
        User user1 = new User();
        user1.setId(1L);
        user1.setUserName("Test user1");
        user1.setEmail("test@example.com");
        user1.setRole(UserRole.STANDART);

        User user2 = new User();
        user2.setId(2L);
        user2.setUserName("Test user2");
        user2.setEmail("test2@example.com");
        user2.setRole(UserRole.STANDART);

        List<User> userList = Arrays.asList(user1, user2);

        UserResponse userResponse1=new UserResponse(user1.getId(), user1.getUserName(), user1.getEmail(),user1.getRole());
        UserResponse userResponse2=new UserResponse(user2.getId(), user2.getUserName(), user2.getEmail(),user2.getRole());

        List<UserResponse> responseList=new ArrayList<>();
        responseList.add(userResponse1);
        responseList.add(userResponse2);

        Mockito.when(userRepository.findAll()).thenReturn(userList);
        List<UserResponse> result= userServiceImpl.getAllUser();

        Assertions.assertIterableEquals(result,responseList);
        verify(userRepository).findAll();

    }
    @Test
    void whenUserExists_thenReturnUserResponse(){
        User user=new User();
        user.setId(1L);
        user.setUserName("test name");
        user.setEmail("test@example.com");
        user.setRole(UserRole.STANDART);

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        UserResponse result= userServiceImpl.getUserByUserId(user.getId());

        assertNotNull(result);
        assertEquals(user.getId(), result.id());
        assertEquals("test name", result.userName());
        assertEquals("test@example.com", result.email());
        assertEquals(UserRole.STANDART, result.userRole());
        verify(userRepository).findById(user.getId());

    }






    @AfterEach
    void tearDown() {

    }
}