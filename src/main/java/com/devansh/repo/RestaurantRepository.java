package com.devansh.repo;

import com.devansh.Model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Query("""
        select r from Restaurant r 
        where lower(r.name) like lower(concat('%',:query,'%')) or 
              lower(r.cuisineType) like lower(concat('%',:query,'%')) 
""")
    List<Restaurant> findBySearchQuery(String query);

    Optional<Restaurant> findByOwnerId(Integer id);


}
