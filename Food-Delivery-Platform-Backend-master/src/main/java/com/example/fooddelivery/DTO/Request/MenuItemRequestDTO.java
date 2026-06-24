package com.example.fooddelivery.DTO.Request;

import com.example.fooddelivery.Entities.MenuItem;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MenuItemRequestDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @PositiveOrZero
    @DecimalMin("0.0")
    private double price;
    private boolean isAvailable;
    private boolean isVegetarian;
    @DecimalMin("0.0")
    private double calories;

    public MenuItem toEntity() {

        MenuItem item = new MenuItem();
        item.setName(name);
        item.setDescription(description);
        item.setPrice(price);
        item.setAvailable(isAvailable);
        item.setVegetarian(isVegetarian);
        item.setCalories(calories);

        return item;
    }

    public void applyTo(MenuItem item) {

        item.setName(name);
        item.setDescription(description);
        item.setPrice(price);
        item.setAvailable(isAvailable);
        item.setVegetarian(isVegetarian);
        item.setCalories(calories);
    }
}