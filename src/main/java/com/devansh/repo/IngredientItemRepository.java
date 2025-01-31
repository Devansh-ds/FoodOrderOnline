package com.devansh.repo;

import com.devansh.Model.IngredientsItem;
import com.devansh.Model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientItemRepository extends JpaRepository<IngredientsItem, Integer> {


    List<IngredientsItem> findAllByRestaurant(Restaurant restaurant);

}
