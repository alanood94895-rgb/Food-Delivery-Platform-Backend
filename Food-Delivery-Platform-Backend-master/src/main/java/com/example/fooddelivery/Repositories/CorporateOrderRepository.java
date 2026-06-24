package com.example.fooddelivery.Repositories;

import com.example.fooddelivery.Entities.CorporateOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorporateOrderRepository extends JpaRepository<CorporateOrder,Integer> {
}
