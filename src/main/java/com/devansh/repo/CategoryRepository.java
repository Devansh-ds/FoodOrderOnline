package com.devansh.repo;

import com.devansh.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("""
      select c from Category c
      where c.name like %:keyword%
""")
    List<Category> findByCategoryName(String keyword);

    Optional<List<Category>> findByRestaurantId(Integer restaurantId);

}
