package com.example.fooddelivery.Repositories;

import com.example.fooddelivery.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Customer, Integer> {
}
