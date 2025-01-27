package com.devansh.controller;

import com.devansh.Model.Food;
import com.devansh.Model.User;
import com.devansh.request.CreateFoodRequest;
import com.devansh.response.MessageResponse;
import com.devansh.service.FoodService;
import com.devansh.service.RestaurantService;
import com.devansh.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/food")
public class AdminFoodController {

    public final FoodService foodService;
    public final UserService userService;
    public final RestaurantService restaurantService;


    @PostMapping("/{restaurantId}")
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest request,
                                           @PathVariable Integer restaurantId,
                                           @RequestHeader("Authorization") String token) throws Exception {
        User user = userService.findByJwtToken(token);
        return new ResponseEntity<>(foodService.createFood(request, restaurantId), HttpStatus.CREATED);
    }

    @DeleteMapping("/{foodId}")
    public ResponseEntity<MessageResponse> deleteFood(@PathVariable Integer foodId,
                                     @RequestHeader("Authorization") String token) throws Exception {
        User user = userService.findByJwtToken(token);
        foodService.deleteFood(foodId);

        MessageResponse response = MessageResponse
                .builder()
                .message("Food deleted successfully with id " + foodId)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{foodId}")
    public ResponseEntity<Food> updateFoodAvailability(@PathVariable Integer foodId,
                                                       @RequestHeader("Authorization") String token) throws Exception {
        User user = userService.findByJwtToken(token);
        return ResponseEntity.ok(foodService.updateAvailabilityStatus(foodId));
    }

}












