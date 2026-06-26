package com.example.fooddelivery.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Restaurant {
    private String name;
    private String description;
    private String cuisineType;
    private String openingTime;
    private String closingTime;
    private Double minOrderAmount;
    private Double deliveryFee;
    private Boolean acceptingOrders;


    @ManyToOne
    private RestaurantOwner restaurantOwner;

    @OneToMany (mappedBy = "restaurant")
    private List<MenuItem> menuItems;

    @OneToMany (mappedBy = "restaurant")
    private List<ComboMeal> comboMeals;
}
