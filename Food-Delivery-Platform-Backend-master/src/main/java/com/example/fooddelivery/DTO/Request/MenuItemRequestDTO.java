package com.example.fooddelivery.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemRequestDTO {
    @NotBlank
    private String name;

    private String description;

    @PositiveOrZero
    private Double price;

    private Boolean isAvailable;

    private Boolean isVegetarian;

    private Integer calories;
}
