package com.devansh.service;

import com.devansh.Model.Category;
import com.devansh.Model.Food;
import com.devansh.Model.Restaurant;
import com.devansh.request.CreateFoodRequest;

import java.util.List;
import java.util.Optional;

public interface FoodService {

    Food createFood(CreateFoodRequest request, Integer restaurantId) throws Exception;

    void deleteFood(Integer foodId) throws Exception;

    List<Food> getRestaurantFoods(Integer restaurantId,
                                         boolean isVegetarian,
                                         boolean isSeasonal,
                                         String categoryName) throws Exception;

    List<Food> searchFood(String keyword);

    Food findFoodById(Integer foodId) throws Exception;

    Food updateAvailabilityStatus(Integer foodId) throws Exception;



}
