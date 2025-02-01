package com.devansh.controller;

import com.devansh.Model.Food;
import com.devansh.Model.User;
import com.devansh.service.FoodService;
import com.devansh.service.RestaurantService;
import com.devansh.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;
    private final RestaurantService restaurantService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<Food>> searchFood(@RequestParam String keyword,
                                                 @RequestHeader("Authorization") String token) {
        User user = userService.findByJwtToken(token);
        return ResponseEntity.ok(foodService.searchFood(keyword));
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> getRestaurantFood(@PathVariable Integer restaurantId,
                                                        @RequestParam Boolean vegetarian,
                                                        @RequestParam Boolean seasonal,
                                                        @RequestParam String categoryName) throws Exception {
        return ResponseEntity.ok(foodService.getRestaurantFoods(
                restaurantId,
                vegetarian,
                seasonal,
                categoryName
        ));
    }

    @GetMapping("/{foodId}")
    public ResponseEntity<Food> getFood(@PathVariable Integer foodId,
                                        @RequestHeader("Authorization") String token) throws Exception {
        User user = userService.findByJwtToken(token);
        return ResponseEntity.ok(foodService.findFoodById(foodId));
    }



}
