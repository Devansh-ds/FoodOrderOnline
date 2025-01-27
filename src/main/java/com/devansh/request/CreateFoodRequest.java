package com.devansh.request;

import com.devansh.Model.Category;
import com.devansh.Model.IngredientsItem;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CreateFoodRequest {

    private String name;
    private String description;
    private BigDecimal price;
    private Category category;
    private List<String> images;
    private boolean isVegetarian;
    private boolean isSeasonal;
    private List<IngredientsItem> ingredients;

}
