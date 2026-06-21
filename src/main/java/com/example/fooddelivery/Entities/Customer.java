package com.example.fooddelivery.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.criteria.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private String firstName;
    private String lastName;
    private String email;
    private int phone;
    private int passwordHash;
    private int loyaltyPoints;
    private int customerCode;

    @OneToMany
    private List<CustomerAddress> addresses;

    @OneToMany
    private List<Order> orders;

    @OneToMany
    private List<Review> reviews;
}
