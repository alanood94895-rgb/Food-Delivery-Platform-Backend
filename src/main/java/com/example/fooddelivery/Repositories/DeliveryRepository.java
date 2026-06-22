package com.example.fooddelivery.Repositories;

import com.example.fooddelivery.Entities.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {

    @Query("""
           SELECT d
           FROM Delivery d
           WHERE d.driver.id = :driverId
           AND d.status = :status
           AND d.isActive = true
           """)
    List<Delivery> findByDeliveryDriverIdAndStatus(
            @Param("driverId") Integer driverId,
            @Param("status") String status);
}