package com.devansh.request;

import lombok.*;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddCartItemRequest {

    private Integer foodId;
    private Integer quantity;
    private List<String> ingredients;
}
