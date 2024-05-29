package org.example.restaurantreview.service.impl;

import org.example.restaurantreview.constant.UserRole;
import org.example.restaurantreview.dto.request.CreateRestaurantReq;
import org.example.restaurantreview.dto.request.UpdateRestaurantReq;
import org.example.restaurantreview.dto.response.RestaurantResponse;

import org.example.restaurantreview.entity.Restaurant;
import org.example.restaurantreview.entity.User;
import org.example.restaurantreview.exception.RestaurantNotFoundException;
import org.example.restaurantreview.exception.UnAuthorizedAccessException;
import org.example.restaurantreview.repository.RestaurantRepository;
import org.example.restaurantreview.service.RestaurantService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final UserServiceImpl userServiceImpl;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, UserServiceImpl userServiceImpl) {
        this.restaurantRepository = restaurantRepository;
        this.userServiceImpl = userServiceImpl;
    }

    public RestaurantResponse createRestaurant(CreateRestaurantReq request){
        User user= userServiceImpl.getUserById(request.userId());

        if (user.getRole() != UserRole.KIDEMLI) {
            throw new UnAuthorizedAccessException("Only authorized users can create restaurants.");
        }

        Restaurant saveRestaurant= mapToRestaurant(request);
        Restaurant newRestaurant=restaurantRepository.save(saveRestaurant);
        return mapToResponse(newRestaurant);
    }

    public RestaurantResponse updateRestaurant(UpdateRestaurantReq request){
        Restaurant toUpdate= getRestaurantByRestaurantId(request.id());
        toUpdate.setId(request.id());
        toUpdate.setName(request.name());
        toUpdate.setAddress(request.address());
        toUpdate.setType(request.type());
        Restaurant newRestaurant=restaurantRepository.save(toUpdate);
        return mapToResponse(newRestaurant);
    }

    public void updateRestaurantAverageRating(Long restaurantId,double avarageRaiting){
        Restaurant restaurant=getRestaurantByRestaurantId(restaurantId);
        restaurant.setAverageRaiting(avarageRaiting);
        restaurantRepository.save(restaurant);
    }
    public List<RestaurantResponse> getAllRestaurant(){
        List<Restaurant> restaurantList=restaurantRepository.findAll();
        return getRestaurantResponseList(restaurantList);
    }

    public Restaurant getRestaurantByRestaurantId(Long id){
        return restaurantRepository.findById(id).orElseThrow(()->
                new RestaurantNotFoundException("restaurant not found id:"+id));
    }
    public RestaurantResponse getRestaurantById(Long id){
        Restaurant restaurant=restaurantRepository.findById(id).orElseThrow(()->
                new RestaurantNotFoundException("restaurant not found id:"+id));
        return mapToResponse(restaurant);
    }

    public List<RestaurantResponse> getRestaurantByOwnerId(Long id){
        List<Restaurant> restaurantList=restaurantRepository.findByOwnerId(id);
        return getRestaurantResponseList(restaurantList);
    }

    public void deleteRestaurant(Long userId,Long restaurantId){
        User user= userServiceImpl.getUserById(userId);
        if(user.getRole()!= UserRole.YONETICI){
            throw new UnAuthorizedAccessException("Only administrators can delete restaurants.");
        }
        restaurantRepository.deleteById(restaurantId);
    }



    private List<RestaurantResponse> getRestaurantResponseList(List<Restaurant> restaurantList) {
        List<RestaurantResponse> responseList=new ArrayList<>();
        for(Restaurant restaurant: restaurantList){
            responseList.add(mapToResponse(restaurant));
        }
        return responseList;
    }


    private Restaurant mapToRestaurant(CreateRestaurantReq request){
        Restaurant restaurant =new Restaurant();
        restaurant.setName(request.name());
        restaurant.setAddress(request.address());
        restaurant.setType(request.type());
        User owner= userServiceImpl.getUserById(request.userId());
        restaurant.setOwner(owner);
        return restaurant;
    }

    private RestaurantResponse mapToResponse(Restaurant restaurant){
        RestaurantResponse response=
                new RestaurantResponse(restaurant.getId(), restaurant.getName(), restaurant.getType().getType()
                        ,restaurant.getAddress(),restaurant.getAverageRaiting());
        return response;
    }

}
