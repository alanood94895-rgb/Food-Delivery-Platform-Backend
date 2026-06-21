package com.example.fooddelivery.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem extends BaseEntity {
    private String name;
    private String description;
    private int price;
    private Boolean isAvailable;
    private Boolean isVegetarian;
    private String calories;

    @ManyToOne
    private Restaurant restaurant;

    @OneToMany
    private List <OrderItem> orderItems;
}
