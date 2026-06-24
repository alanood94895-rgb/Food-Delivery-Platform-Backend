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
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerCode;
    private String firstName;
    private String lastName;
    private String email;
    private int phone;
    private String passwordHash;
    private int loyaltyPoints;
    private Date createdDate;
    private Date updatedDate;
    private boolean  isActive;


    @OneToMany (mappedBy = "customer")
    private List<CustomerAddress> addresses;

    @OneToMany (mappedBy = "customer")
    private List<Order> orders;

    @OneToMany (mappedBy = "customer")
    private List<Review> reviews;
}