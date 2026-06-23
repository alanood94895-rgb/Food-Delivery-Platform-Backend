package com.example.fooddelivery.DTO;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
    public class OrderItemRequestDTO {

        @NotNull
        private Integer menuItemId;

        @Min(1)
        private Integer quantity;

        private String specialInstructions;
    }

