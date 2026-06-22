package com.example.fooddelivery.DTO;

import lombok.AllArgsConstructor;

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

    private RestaurantSummaryDTO owner;

}
