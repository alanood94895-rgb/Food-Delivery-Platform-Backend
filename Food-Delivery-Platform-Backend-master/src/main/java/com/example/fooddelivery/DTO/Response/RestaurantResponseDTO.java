package com.example.fooddelivery.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantResponseDTO {
    private Integer id;

    private String name;

    private String description;

    private String cuisineType;

    private Double deliveryFee;

    private Boolean acceptingOrders;

    private RestaurantResponseDTO owner;

}
