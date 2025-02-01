package com.devansh.service;

import com.devansh.Model.Category;
import com.devansh.Model.Food;
import com.devansh.Model.IngredientsItem;
import com.devansh.Model.Restaurant;
import com.devansh.repo.CategoryRepository;
import com.devansh.repo.FoodRepository;
import com.devansh.repo.IngredientItemRepository;
import com.devansh.repo.RestaurantRepository;
import com.devansh.request.CreateFoodRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;
    private final RestaurantService restaurantService;
    private final IngredientItemRepository ingredientItemRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Food createFood(CreateFoodRequest request, Integer restaurantId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);

        List<IngredientsItem> ingredientsItemList = new ArrayList<>();
        for (Integer itemId : request.getIngredientsId()) {
            var item = ingredientItemRepository.findById(itemId).orElseThrow(EntityNotFoundException::new);
            ingredientsItemList.add(item);
        }

        Category category = categoryRepository
                .findById(request.getCategoryId())
                .orElseThrow(EntityNotFoundException::new);

        Food food = Food
                .builder()
                .foodCategory(category)
                .restaurant(restaurant)
                .description(request.getDescription())
                .images(request.getImages())
                .name(request.getName())
                .price(request.getPrice())
                .creationDate(new Date())
                .isSeasonal(request.isSeasonal())
                .isVegetarian(request.isVegetarian())
                .available(false)
                .ingredients(ingredientsItemList)
                .build();

        Food savedFood = foodRepository.save(food);
        restaurant.getFoods().add(savedFood);
        restaurantRepository.save(restaurant);

        return savedFood;
    }

    @Override
    public void deleteFood(Integer foodId) throws Exception {

        Food food = foodRepository.findById(foodId)
                .orElseThrow(() -> new EntityNotFoundException("Food not found with id: " + foodId));

        Restaurant restaurant = restaurantService.findRestaurantById(food.getRestaurant().getId());
        restaurant.getFoods().remove(food);
        restaurantRepository.save(restaurant);

        foodRepository.delete(food);

    }

    @Override
    public List<Food> getRestaurantFoods(Integer restaurantId,
                                         Boolean isVegetarian,
                                         Boolean isSeasonal,
                                         String categoryName) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        List<Food> allFoods = restaurant.getFoods();

        if (isVegetarian != null) {
            allFoods = filterByVegeterian(allFoods);
        }
        if (isSeasonal != null) {
            allFoods = filterBySeasonal(allFoods);
        }
        if (categoryName != null) {
            allFoods = filterByCategory(allFoods, categoryName);
        }

        return allFoods;
    }

    private List<Food> filterByCategory(List<Food> allFoods, String categoryName) {
        return allFoods
                .stream()
                .filter(food -> {
                    if (food.getFoodCategory() != null) {
                        return food.getFoodCategory().getName().equals(categoryName);
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

    private List<Food> filterBySeasonal(List<Food> allFoods) {
        return allFoods
                .stream()
                .filter(Food::isSeasonal)
                .collect(Collectors.toList());
    }

    private List<Food> filterByVegeterian(List<Food> allFoods) {
        return allFoods
                .stream()
                .filter(Food::isVegetarian)
                .collect(Collectors.toList());
    }

    @Override
    public List<Food> searchFood(String keyword) {
        return foodRepository.searchFood(keyword);
    }

    @Override
    public Food findFoodById(Integer foodId) throws Exception {
        return foodRepository
                .findById(foodId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Food not found with id: " + foodId));
    }

    @Override
    public Food updateAvailabilityStatus(Integer foodId) throws Exception {
        Food food = findFoodById(foodId);
        food.setAvailable(!food.isAvailable());
        return foodRepository.save(food);
    }
}












