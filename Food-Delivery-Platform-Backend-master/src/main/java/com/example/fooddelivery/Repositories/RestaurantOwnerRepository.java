package com.example.fooddelivery.Repositories;

import com.example.fooddelivery.Entities.RestaurantOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantOwnerRepository extends JpaRepository<RestaurantOwner,Integer> {
    @Query("select ro from RestaurantOwner ro where ro.isActive=true")
    List<RestaurantOwner> getAllOwners();

    List<RestaurantOwner> findActiveById(Integer ownerId);
}
