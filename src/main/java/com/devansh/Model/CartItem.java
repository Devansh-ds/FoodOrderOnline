package com.devansh.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JsonIgnore
    private Cart cart;

    @ManyToOne
    private Food food;

    private Integer quantity;

    @ElementCollection
    private List<String> ingredients;

    private BigDecimal totalPrice;


}
