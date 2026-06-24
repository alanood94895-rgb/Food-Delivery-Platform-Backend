package com.example.fooddelivery.DTO.Summary;

import com.example.fooddelivery.Entities.ComboMeal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComboMealSummeryDTO {
    private int id;
    private String comboName;
    private double totalPrice;
    private boolean isAvailable;

    public static ComboMealSummeryDTO fromEntity(ComboMeal comboMeal) {

        ComboMealSummeryDTO dto = new ComboMealSummeryDTO();
        dto.setId(comboMeal.getId());
        dto.setComboName(comboMeal.getComboName());
        dto.setTotalPrice(comboMeal.getTotalPrice());
        dto.setAvailable(comboMeal.isAvailable());

        return dto;
    }
}