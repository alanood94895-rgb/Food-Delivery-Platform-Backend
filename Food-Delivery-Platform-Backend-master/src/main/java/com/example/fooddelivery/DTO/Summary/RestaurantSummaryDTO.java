package com.example.fooddelivery.DTO.Summary;

import com.example.fooddelivery.DTO.Response.RestaurantResponseDTO;
import com.example.fooddelivery.Entities.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantSummaryDTO {
    private String name;
    private String cuisineType;
    private Double deliveryFee;
    private Boolean acceptingOrders;

    public static RestaurantSummaryDTO fromEntity(Restaurant restaurant) {
        if (restaurant == null) {
            return null;
        }

        RestaurantSummaryDTO dto = new RestaurantSummaryDTO();

        dto.setName(restaurant.getName());
        dto.setCuisineType(restaurant.getCuisineType());
        dto.setDeliveryFee(restaurant.getDeliveryFee());
        dto.setAcceptingOrders(restaurant.getAcceptingOrders());


        return dto;
    }
}