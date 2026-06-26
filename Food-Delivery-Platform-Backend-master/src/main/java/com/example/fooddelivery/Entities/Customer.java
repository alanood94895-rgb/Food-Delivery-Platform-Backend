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

    @OneToMany (mappedBy = "Customer")
    private List<CustomerAddress> customerAddressList;

    @OneToMany (mappedBy = "Customer")
    private  List<Order> orderList;

    @OneToMany (mappedBy = "Customer")
    private List<Review> reviewList;
}