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
    @NotBlank(message = "Item name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Price is required")
    @Min(value = 0, message = "Price cannot be negative")
    private Double price;

    private Boolean isAvailable;
    private Boolean isVegetarian;
    private Integer calories;

    @NotNull(message = "Restaurant ID is required")
    private Integer restaurantId;

    public MenuItem toEntity(){
        MenuItem menuItem = new MenuItem();

        menuItem.setName(name);
        menuItem.setDescription(description);
        menuItem.setPrice(price);
        menuItem.setIsAvailable(isAvailable);
        menuItem.setIsVegetarian(isVegetarian);
        menuItem.setCalories(calories);

        return menuItem;
    }

    public void applyTo(MenuItem menuItem){
        menuItem.setName(name);
        menuItem.setDescription(description);
        menuItem.setPrice(price);
        menuItem.setIsAvailable(isAvailable);
        menuItem.setIsVegetarian(isVegetarian);
        menuItem.setCalories(calories);
    }
}