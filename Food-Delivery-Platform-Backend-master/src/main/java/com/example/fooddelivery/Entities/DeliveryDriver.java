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
public class DeliveryDriver extends BaseEntity {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String passwordHash;
    private String driverCode;
    private String vehicleType;
    private String vehiclePlate;
    private Double currentLat;
    private Double currentLng;
    private boolean isOnline;


    @OneToMany(mappedBy = "deliveryDriver")
    private List<Delivery> deliveries;
}