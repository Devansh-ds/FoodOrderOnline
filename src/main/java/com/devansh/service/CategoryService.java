package com.devansh.service;

import com.devansh.Model.Category;

import java.util.List;

public interface CategoryService {

    Category createCategory(String name, Integer userId) throws Exception;

    List<Category> findCategoryByRestaurantId(Integer restaurantId) throws Exception;

    List<Category> findCategoryByUserId(Integer userId) throws Exception;

    void deleteCategory(Integer categoryId) throws Exception;

    List<Category> findCategoryByName(String name) throws Exception;

}
