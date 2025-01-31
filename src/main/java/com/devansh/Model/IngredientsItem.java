package com.devansh.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class IngredientsItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne
    private IngredientCategory category;

    @ManyToOne
    @JsonIgnore
    private Restaurant restaurant;

    private boolean inStock = true;

}
