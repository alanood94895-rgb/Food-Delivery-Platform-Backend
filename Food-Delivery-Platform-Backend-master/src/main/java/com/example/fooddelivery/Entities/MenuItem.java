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
public class MenuItem extends BaseEntity {
    private String name;
    private String description;
    private Double price;
    private Boolean isAvailable;
    private Boolean isVegetarian;
    private Integer calories;



    @ManyToOne
    private Restaurant restaurant;

    @OneToMany (mappedBy = "menuItem")
    private List<OrderItem> orderItemList;
}