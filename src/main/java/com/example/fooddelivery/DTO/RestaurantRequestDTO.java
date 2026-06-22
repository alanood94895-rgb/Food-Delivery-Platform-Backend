package com.example.fooddelivery.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantRequestDTO {
    @NotBlank
    private String name;

    private String description;

    @NotBlank
    private String cuisineType;

    @NotBlank
    private String openingTime;

    @NotBlank
    private String closingTime;

    @PositiveOrZero
    private Double minOrderAmount;

    @PositiveOrZero
    private Double deliveryFee;
}
