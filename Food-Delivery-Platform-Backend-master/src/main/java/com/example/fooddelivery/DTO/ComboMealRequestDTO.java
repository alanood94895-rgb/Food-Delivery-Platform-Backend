package com.example.fooddelivery.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
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
    private Double totalPrice;

    private Boolean isAvailable;

    @NotEmpty
    private List<Integer> menuItemIds;
}
