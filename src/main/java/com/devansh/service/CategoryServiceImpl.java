package com.devansh.service;

import com.devansh.Model.Category;
import com.devansh.Model.Restaurant;
import com.devansh.repo.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final RestaurantService restaurantService;


    @Override
    public Category createCategory(String name, Integer userId) throws Exception {
        Restaurant restaurant = restaurantService.getRestaurantByUserId(userId);
        Category category = Category
                .builder()
                .name(name)
                .restaurant(restaurant)
                .build();
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findCategoryByRestaurantId(Integer restaurantId) throws Exception {
        return categoryRepository.findByRestaurantId(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException("No Category found with restaurant id: " + restaurantId));
    }

    @Override
    public List<Category> findCategoryByUserId(Integer userId) throws Exception {
        Restaurant restaurant = restaurantService.getRestaurantByUserId(userId);
        return findCategoryByRestaurantId(restaurant.getId());
    }

    @Override
    public void deleteCategory(Integer categoryId) throws Exception {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("No Category found with id: " + categoryId));
        categoryRepository.delete(category);
    }

    @Override
    public List<Category> findCategoryByName(String name) throws Exception {
        return categoryRepository.findByCategoryName(name);
    }
}
