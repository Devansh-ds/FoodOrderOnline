package com.devansh.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDto {

    private Integer id;
    private String title;

    @ElementCollection
    private List<String> images = new ArrayList<>();

    private String description;
}
