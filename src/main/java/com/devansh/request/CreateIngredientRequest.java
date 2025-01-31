package com.devansh.request;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreateIngredientRequest {
    private String ingredientName;
    private Integer categoryId;
    private Integer restaurantId;
}
