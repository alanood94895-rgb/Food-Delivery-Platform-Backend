package com.example.fooddelivery.Repositories;

import com.example.fooddelivery.Entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {
}
