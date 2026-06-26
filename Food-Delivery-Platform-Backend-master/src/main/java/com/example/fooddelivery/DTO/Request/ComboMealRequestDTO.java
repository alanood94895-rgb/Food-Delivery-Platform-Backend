package com.example.fooddelivery.DTO.Request;

import com.example.fooddelivery.Entities.ComboMeal;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComboMealRequestDTO {
    @NotBlank(message = "Combo name is required")
    private String comboName;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Total price is required")
    @Min(value = 0, message = "Total price cannot be negative")
    private Double totalPrice;

    private Boolean isAvailable;

    @NotNull(message = "Restaurant ID is required")
    private Integer restaurantId;

    public ComboMeal toEntity() { // For Creating
        ComboMeal comboMeal = new ComboMeal();

        comboMeal.setComboName(comboName);
        comboMeal.setDescription(description);
        comboMeal.setTotalPrice(totalPrice);
        comboMeal.setAvailable(isAvailable);

        return comboMeal;
    }

    public void applyTo(ComboMeal comboMeal) { // For Updating
        comboMeal.setComboName(comboName);
        comboMeal.setDescription(description);
        comboMeal.setTotalPrice(totalPrice);
        comboMeal.setAvailable(isAvailable);
    }


}