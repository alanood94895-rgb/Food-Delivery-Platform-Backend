package com.example.fooddelivery.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RestaurantOwner {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String passwordHash;
    private String businessLicenseCode;


    @OneToMany (mappedBy = "restaurantOwner")
    private List<Restaurant> restaurants;
}
