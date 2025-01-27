package com.devansh.controller;

import com.devansh.Model.Category;
import com.devansh.Model.User;
import com.devansh.service.CategoryService;
import com.devansh.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final UserService userService;

    @GetMapping("/{restaurantId}")
    public ResponseEntity<List<Category>> findByRestaurantId(@PathVariable Integer restaurantId,
                                                             @RequestHeader("Authorization") String token) throws Exception {
        User User = userService.findByJwtToken(token);
        return ResponseEntity.ok(categoryService.findCategoryByRestaurantId(restaurantId));
    }

    @GetMapping
    public ResponseEntity<List<Category>> findCategoryByName(@RequestParam String keyword) throws Exception {
        return ResponseEntity.ok(categoryService.findCategoryByName(keyword));
    }


}















