package com.devansh.controller;

import com.devansh.Model.Category;
import com.devansh.Model.User;
import com.devansh.response.MessageResponse;
import com.devansh.service.CategoryService;
import com.devansh.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/category")
public class AdminCategoryController {

    private final CategoryService categoryService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestParam String name,
                                                   @RequestHeader("Authorization") String token) throws Exception {
        User user = userService.findByJwtToken(token);
        return ResponseEntity.ok(categoryService.createCategory(name, user.getId()));
    }

    @GetMapping
    public ResponseEntity<List<Category>> findCategoryByUserId(@RequestHeader("Authorization") String token) throws Exception {
        User user = userService.findByJwtToken(token);
        return ResponseEntity.ok(categoryService.findCategoryByUserId(user.getId()));
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<MessageResponse> deleteCategoryById(@PathVariable Integer categoryId,
                                                              @RequestHeader("Authorization") String token) throws Exception {
        User user = userService.findByJwtToken(token);
        categoryService.deleteCategory(categoryId);
        MessageResponse response = MessageResponse
                .builder()
                .message("deleted successfully")
                .build();
        return ResponseEntity.ok(response);
    }



}
