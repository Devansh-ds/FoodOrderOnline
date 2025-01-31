package com.devansh.service;

import com.devansh.Model.IngredientCategory;
import com.devansh.Model.IngredientsItem;
import com.devansh.Model.Restaurant;
import com.devansh.Model.User;
import com.devansh.repo.IngredientCategoryRepository;
import com.devansh.repo.IngredientItemRepository;
import com.devansh.request.CreateIngredientRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private final IngredientCategoryRepository ingredientCategoryRepository;
    private final IngredientItemRepository ingredientItemRepository;
    private final RestaurantService restaurantService;
    private final UserService userService;


    @Override
    public IngredientCategory createIngredientCategory(String ingredientCategoryName, User user) throws Exception {
        Restaurant restaurant = restaurantService.getRestaurantByUserId(user.getId());
        IngredientCategory ingredientCategory = IngredientCategory
                .builder()
                .name(ingredientCategoryName)
                .restaurant(restaurant)
                .build();
        return ingredientCategoryRepository.save(ingredientCategory);

    }

    @Override
    public IngredientCategory findIngredientCategoryById(Integer categoryId) throws Exception {
        return ingredientCategoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Ingredient category not found with id: " + categoryId));
    }

    @Override
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Integer restaurantId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        return ingredientCategoryRepository
                .findByRestaurant(restaurant)
                .orElseThrow(() ->
                        new EntityNotFoundException("No such category has restaurant with id: " + restaurantId));
    }

    @Override
    public List<IngredientsItem> findRestaurantIngredients(Integer restaurantId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        return ingredientItemRepository.findAllByRestaurant(restaurant);
    }

    @Override
    public IngredientsItem createIngredientsItem(CreateIngredientRequest request) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(request.getRestaurantId());
        IngredientCategory ingredientCategory = findIngredientCategoryById(request.getCategoryId());
        IngredientsItem ingredientsItem = IngredientsItem
                .builder()
                .category(ingredientCategory)
                .name(request.getIngredientName())
                .restaurant(restaurant)
                .build();
        return ingredientItemRepository.save(ingredientsItem);
    }

    @Override
    public IngredientsItem updateStock(Integer ingredientId) throws Exception {
        IngredientsItem ingredientsItem = ingredientItemRepository
                .findById(ingredientId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Ingredient item not found with id: " + ingredientId));
        ingredientsItem.setInStock(!ingredientsItem.isInStock());
        return ingredientItemRepository.save(ingredientsItem);
    }
}











