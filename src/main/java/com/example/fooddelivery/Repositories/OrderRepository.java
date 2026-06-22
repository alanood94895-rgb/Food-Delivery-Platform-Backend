package com.example.fooddelivery.Repositories;

import jakarta.persistence.criteria.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT o FROM Order o WHERE o.customer.id = :customerId AND o.isActive = true")
    List<Order> findByCustomerId(@Param("customerId") Integer customerId);

    @Query("""
           SELECT o
           FROM Order o
           WHERE o.restaurant.id = :restaurantId
           AND o.status = :status
           AND o.isActive = true
           """)
    List<Order> findByRestaurantIdAndStatus(
            @Param("restaurantId") Integer restaurantId,
            @Param("status") String status);

    @Query("""
           SELECT o
           FROM Order o
           WHERE o.orderDate BETWEEN :startDate AND :endDate
           AND o.isActive = true
           """)
    List<Order> findByOrderDateBetween(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    @Query("""
           SELECT COUNT(o)
           FROM Order o
           WHERE o.restaurant.id = :restaurantId
           AND o.status = 'COMPLETED'
           AND o.isActive = true
           """)
    Long countCompletedOrders(
            @Param("restaurantId") Integer restaurantId);

    @Query("""
           SELECT COALESCE(SUM(o.totalAmount),0)
           FROM Order o
           WHERE o.status = 'DELIVERED'
           AND DATE(o.orderDate) = :date
           AND o.isActive = true
           """)
    Double sumDeliveredRevenueByDate(
            @Param("date") LocalDate date);
}