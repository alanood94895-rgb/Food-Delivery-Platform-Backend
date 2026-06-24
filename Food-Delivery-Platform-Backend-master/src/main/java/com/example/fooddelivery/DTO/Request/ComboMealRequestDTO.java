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
    @NotBlank
    private String comboName;
    private String description;
    @PositiveOrZero
    @DecimalMin("0.0")
    private double totalPrice;
    private boolean isAvailable;

    public ComboMeal toEntity() {
        ComboMeal comboMeal = new ComboMeal();

        comboMeal.setComboName(comboName);
        comboMeal.setDescription(description);
        comboMeal.setTotalPrice(totalPrice);
        comboMeal.setAvailable(isAvailable);

        return comboMeal;
    }

    public void applyTo(ComboMeal comboMeal) {
        comboMeal.setComboName(comboName);
        comboMeal.setDescription(description);
        comboMeal.setTotalPrice(totalPrice);
        comboMeal.setAvailable(isAvailable);
    }
}