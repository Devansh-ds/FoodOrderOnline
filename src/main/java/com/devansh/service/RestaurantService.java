package com.devansh.service;

import com.devansh.Model.Restaurant;
import com.devansh.Model.User;
import com.devansh.dto.RestaurantDto;
import com.devansh.request.CreateRestaurantRequest;

import java.util.List;

public interface RestaurantService {

    public Restaurant createRestaurant(CreateRestaurantRequest request, User user);

    public Restaurant updateRestaurant(Integer restaurantId, CreateRestaurantRequest updatedRestaurantRequest) throws Exception;

    public void deleteRestaurant(Integer restaurantId) throws Exception;

    public List<Restaurant> getAllRestaurants();

    public List<Restaurant> searchRestaurant(String keyword);

    public Restaurant findRestaurantById(Integer restaurantId) throws Exception;

    public Restaurant getRestaurantByUserId(Integer userId) throws Exception;

    public Restaurant addToFavourites(Integer restaurantId, User user) throws Exception;

    public Restaurant updateRestaurantStatus(Integer restaurantId) throws Exception;

}

