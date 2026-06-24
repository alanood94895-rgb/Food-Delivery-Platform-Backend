package com.example.fooddelivery.DTO.Response;

import com.example.fooddelivery.Entities.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantResponseDTO {
    private int id;
    private String name;
    private String description;
    private String cuisineType;
    private LocalDateTime openingTime;
    private LocalDateTime closingTime;
    private int minOrderAmount;
    private double deliveryFee;
    private boolean  isActive;

    public static RestaurantResponseDTO fromEntity(Restaurant restaurant){
        RestaurantResponseDTO dto = new RestaurantResponseDTO();
        dto.setId(restaurant.getId());
        dto.setName(restaurant.getName());
        dto.setDescription(restaurant.getDescription());
        dto.setCuisineType(restaurant.getCuisineType());
        dto.setOpeningTime(restaurant.getOpeningTime());
        dto.setClosingTime(restaurant.getClosingTime());
        dto.setMinOrderAmount(restaurant.getMinOrderAmount());
        dto.setDeliveryFee(restaurant.getDeliveryFee());
        dto.setActive(restaurant.isActive());

        return dto;
    }
}