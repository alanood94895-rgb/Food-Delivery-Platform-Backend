package com.example.fooddelivery.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CorporateOrder extends BaseEntity {

    private String corporateCode;
    private String companyName;
    private String costCenter;
    private LocalDate orderDate;
    private String status;
    private Double totalAmount;

    @ManyToOne
    private Restaurant restaurant;

    @OneToMany (mappedBy = "corporateOrder")
    private List<OrderItem> orderItemList;


    @OneToMany (mappedBy = "corporateOrder")
    private List<OrderItem> orderItems;
}
