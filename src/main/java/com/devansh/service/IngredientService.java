package com.devansh.service;

import com.devansh.Model.IngredientCategory;
import com.devansh.Model.IngredientsItem;
import com.devansh.Model.User;
import com.devansh.request.CreateIngredientRequest;

import java.util.List;

public interface IngredientService {

    public IngredientCategory createIngredientCategory(String ingredientCategoryName, User user) throws Exception;

    public IngredientCategory findIngredientCategoryById(Integer categoryId) throws Exception;

    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Integer restaurantId) throws Exception;

    public List<IngredientsItem> findRestaurantIngredients(Integer restaurantId) throws Exception;

    public IngredientsItem createIngredientsItem(CreateIngredientRequest request) throws Exception;

    public IngredientsItem updateStock(Integer id) throws Exception;

}
