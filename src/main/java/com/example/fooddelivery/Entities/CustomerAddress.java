package com.example.fooddelivery.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAddress extends BaseEntity{

    private String street;
    private String city;
    private String building;
    private Boolean isDefault;

@ManyToOne
  private Customer customer;

}
