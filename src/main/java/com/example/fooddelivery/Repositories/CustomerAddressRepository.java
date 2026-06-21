package com.example.fooddelivery.Repositories;

import com.example.fooddelivery.Entities.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Integer> {

    @Query("SELECT a FROM CustomerAddress a WHERE LOWER(a.city) = LOWER(:city) AND a.isActive = true")
    List<CustomerAddress> findByCity(@Param("city") String city);
}
