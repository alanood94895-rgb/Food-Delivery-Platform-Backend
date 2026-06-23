package com.example.fooddelivery.Repositories;

import com.example.fooddelivery.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository <Customer, Integer> {

    @Query("SELECT c FROM Customer c WHERE c.customerEmail = :email AND c.isActive = true")
    Optional<Customer> findByEmail(@Param("email") String email);

    @Query("SELECT c FROM Customer c WHERE c.loyaltyPoints >= :points AND c.isActive = true")
    List<Customer> findByLoyaltyPointsGreaterThanEqual(@Param("points") int points);

    @Query("SELECT c FROM Customer c WHERE c.createdDate BETWEEN :startDate AND :endDate AND c.isActive = true")
    List<Customer> findCustomersRegisteredBetween(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);
}
