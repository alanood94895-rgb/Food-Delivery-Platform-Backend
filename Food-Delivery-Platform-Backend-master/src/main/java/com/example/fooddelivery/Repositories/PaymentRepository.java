package com.example.fooddelivery.Repositories;

import com.example.fooddelivery.Entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
