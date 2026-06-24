package com.example.fooddelivery.Repositories;

import com.example.fooddelivery.Entities.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Integer> {

    @Query("Select ca from CustomerAddress ca where ca.isActive=true and ca.city=:city")
    CustomerAddress findByCity(@Param("city") String city);
}
