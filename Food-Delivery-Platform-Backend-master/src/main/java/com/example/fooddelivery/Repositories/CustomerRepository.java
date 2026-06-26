package com.example.fooddelivery.Repositories;

import com.example.fooddelivery.DTO.Response.CustomerResponseDTO;
import com.example.fooddelivery.Entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository <Customer, Integer> {

    @Query("SELECT c from Customer c WHERE c.isActive=true AND c.email=:email")
    Customer  findByEmail(@Param("email") String email);

    @Query("Select c from Customer c where c.isActive=true and c.loyaltyPoints>=:points")
    List<Customer> findByLoyaltyPointsGreaterThanEqual(@Param("points") int points);
    @Query("SELECT c FROM Customer c WHERE c.isActive=true AND c.createdDate BETWEEN :createdDate AND CURRENT_TIMESTAMP")
    List<Customer> CustomerRegisterWithDateRange(@Param("createdDate") Date createdDate);
    @Query("select c from Customer c where c.isActive=true")
    List<CustomerResponseDTO> getAllCustomers();
    @Query(value = " select c from Customer c where c.isActive=true order by c.loyaltyPoints desc limit 10")
    List<Customer> getTopLoyalCustomers();
    @Query("select c from Customer c where c.isActive=true and c.firstName=:nam ")
    Page<Customer> findByFirstName(String name, Pageable pageable);

    //For Reporting
    @Query(value = "SELECT * FROM customer " + "WHERE is_active = true " + "ORDER BY loyalty_points DESC LIMIT 10", nativeQuery = true)
    List<Customer> findTop10ByLoyaltyPoints();

}