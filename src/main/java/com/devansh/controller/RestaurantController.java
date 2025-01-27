package com.devansh.controller;

import com.devansh.Model.Restaurant;
import com.devansh.Model.User;
import com.devansh.dto.RestaurantDto;
import com.devansh.service.RestaurantService;
import com.devansh.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final UserService userService;

    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurant(
            @RequestHeader("Authorization") String token,
            @RequestParam String keyword
    ) throws Exception {
        User user = userService.findByJwtToken(token);
        List<Restaurant> restaurants = restaurantService.searchRestaurant(keyword);
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRestaurants(
            @RequestHeader("Authorization") String token
    ) {
        User user = userService.findByJwtToken(token);
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> findRestaurantById(
            @PathVariable Integer restaurantId,
            @RequestHeader("Authorization") String token
    ) throws Exception {
        User user = userService.findByJwtToken(token);
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @PutMapping("/{restaurantId}/add-favourites")
    public ResponseEntity<Restaurant> addToFavourites(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Integer restaurantId
    ) throws Exception {
        User user = userService.findByJwtToken(jwt);
        Restaurant restaurant = restaurantService.addToFavourites(restaurantId, user);
        return new ResponseEntity<>(restaurant, HttpStatus.ACCEPTED);
    }

}




















