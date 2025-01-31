package com.devansh.controller;

import com.devansh.Model.IngredientCategory;
import com.devansh.Model.IngredientsItem;
import com.devansh.Model.User;
import com.devansh.request.CreateIngredientRequest;
import com.devansh.service.IngredientService;
import com.devansh.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/ingredients")
@RequiredArgsConstructor
public class AdminIngredientController {

    private final IngredientService ingredientService;
    private final UserService userService;

    @PostMapping("/category")
    public ResponseEntity<IngredientCategory> createIngredientCategory(@RequestParam String categoryName,
                                                   @RequestHeader("Authorization") String token) throws Exception {
        User user = userService.findByJwtToken(token);
        return ResponseEntity.ok(ingredientService.createIngredientCategory(categoryName, user));
    }

    @PostMapping("/ingredient")
    public ResponseEntity<IngredientsItem> createIngredientsItem(@RequestBody CreateIngredientRequest request,
                                                                 @RequestHeader("Authorization") String token) throws Exception {
        User user = userService.findByJwtToken(token);
        return ResponseEntity.ok(ingredientService.createIngredientsItem(request));
    }

    @PostMapping("/{ingredientId}/stock")
    public ResponseEntity<IngredientsItem> updateStock(@PathVariable Integer ingredientId,
                                                       @RequestHeader("Authorization") String token) throws Exception {
        User user = userService.findByJwtToken(token);
        return ResponseEntity.ok(ingredientService.updateStock(ingredientId));
    }

}





















