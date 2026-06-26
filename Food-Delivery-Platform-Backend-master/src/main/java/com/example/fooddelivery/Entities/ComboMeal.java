package com.example.fooddelivery.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ComboMeal extends BaseEntity{

    private String comboName;
    private String description;
    private double totalPrice;
    private boolean isAvailable;


    @ManyToOne
    private Restaurant restaurant;

    @ManyToMany
    private List<MenuItem> menuItemList;

}
