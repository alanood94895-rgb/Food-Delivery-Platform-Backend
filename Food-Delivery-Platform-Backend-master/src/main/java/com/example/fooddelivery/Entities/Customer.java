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
public class Customer extends BaseEntity {
    private String firstName;
    private String lastName;
    private String customerEmail;
    private String phone;
    private String passwordHash;
    private Integer loyaltyPoints;
    private String customerCode;

    @OneToMany
    private List<CustomerAddress> customerAddressList;
    @OneToMany
    private  List<Order> orderList;
    @OneToMany
    private List<Review> reviewList;
}