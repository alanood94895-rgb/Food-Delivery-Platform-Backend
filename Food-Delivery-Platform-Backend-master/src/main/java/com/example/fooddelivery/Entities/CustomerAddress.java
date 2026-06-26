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
public class CustomerAddress extends BaseEntity{
    private String street;
    private String city;
    private String building;
    private Boolean isDefault;

@ManyToOne
  private Customer customer;

}
