package com.devansh.repo;

import com.devansh.Model.IngredientCategory;
import com.devansh.Model.IngredientsItem;
import com.devansh.Model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IngredientCategoryRepository extends JpaRepository<IngredientCategory, Integer> {
    
    Optional<List<IngredientCategory>> findByRestaurant(Restaurant restaurant);


}
