package com.devansh.controller;

import com.devansh.Model.IngredientCategory;
import com.devansh.Model.IngredientsItem;
import com.devansh.Model.User;
import com.devansh.service.IngredientService;
import com.devansh.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
@RequiredArgsConstructor
public class IngredientsController {

    private final IngredientService ingredientService;
    private final UserService userService;

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<IngredientCategory> getIngredientCategory(@PathVariable Integer categoryId,
                                                                    @RequestHeader("Authorization") String token) throws Exception {
        User user = userService.findByJwtToken(token);
        return ResponseEntity.ok(ingredientService.findIngredientCategoryById(categoryId));
    }

    @GetMapping("/category/restaurant/{restaurantId}")
    public ResponseEntity<List<IngredientCategory>> getAllCategoryByRestaurantId(@PathVariable Integer restaurantId,
                                                                                 @RequestHeader("Authorization") String token) throws Exception {
        User user = userService.findByJwtToken(token);
        return ResponseEntity.ok(ingredientService.findIngredientCategoryByRestaurantId(restaurantId));
    }

    @GetMapping("/ingredient/restaurant/{restaurantId}")
    public ResponseEntity<List<IngredientsItem>> getAllIngredientsByRestaurantId(@PathVariable Integer restaurantId,
                                                                                 @RequestHeader("Authorization") String token) throws Exception {
        User user = userService.findByJwtToken(token);
        return ResponseEntity.ok(ingredientService.findRestaurantIngredients(restaurantId));
    }

}











