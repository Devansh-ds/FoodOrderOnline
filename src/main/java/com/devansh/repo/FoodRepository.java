package com.devansh.repo;

import com.devansh.Model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Integer> {

    List<Food> findByRestaurantId(Integer restaurantId);


    @Query("""
    Select f from Food f 
    where f.name like %:keyword% or f.foodCategory.name like %:keyword%
""")
    List<Food> searchFood(@Param("keyword") String keyword);

}


















