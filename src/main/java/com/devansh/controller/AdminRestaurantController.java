package com.devansh.controller;

import com.devansh.Model.Restaurant;
import com.devansh.Model.User;
import com.devansh.request.CreateRestaurantRequest;
import com.devansh.response.MessageResponse;
import com.devansh.service.RestaurantService;
import com.devansh.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/restaurants")
@RequiredArgsConstructor
public class AdminRestaurantController {

    private final RestaurantService restaurantService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(
            @RequestBody CreateRestaurantRequest request,
            @RequestHeader("Authorization") String token
            ) throws Exception {
        User user = userService.findByJwtToken(token);

        System.out.println("user: " + user);
        Restaurant restaurant = restaurantService.createRestaurant(request, user);
        System.out.println("restaurant: " + restaurant);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> updateRestaurant(
            @RequestBody CreateRestaurantRequest request,
            @RequestHeader("Authorization") String token,
            @PathVariable Integer restaurantId
    ) throws Exception {
        User user = userService.findByJwtToken(token);
        Restaurant restaurant = restaurantService.updateRestaurant(restaurantId, request);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<MessageResponse> deleteRestaurant(
            @PathVariable Integer restaurantId,
            @RequestHeader("Authorization") String token
    ) throws Exception {

        User user = userService.findByJwtToken(token);

        if (restaurantService.findRestaurantById(restaurantId).getOwner().equals(user)) {
            restaurantService.deleteRestaurant(restaurantId);
        }

        MessageResponse response = MessageResponse
                .builder()
                .message("restaurant deleted successfully")
                .build();
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{restaurantId}/status")
    public ResponseEntity<Restaurant> updateRestaurantStatus(
            @RequestHeader("Authorization") String token,
            @PathVariable Integer restaurantId
    ) throws Exception {
        User user = userService.findByJwtToken(token);
        Restaurant restaurant = restaurantService.updateRestaurantStatus(restaurantId);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<Restaurant> getRestaurantByUserId(
            @RequestHeader("Authorization") String token
    ) throws Exception {
        User user = userService.findByJwtToken(token);
        Restaurant restaurant = restaurantService.getRestaurantByUserId(user.getId());

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

}



























