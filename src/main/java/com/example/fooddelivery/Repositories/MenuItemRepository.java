package com.example.fooddelivery.Repositories;

import com.example.fooddelivery.Entities.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {

    @Query("SELECT m FROM MenuItem m WHERE m.restaurant.id = :id AND m.isActive = true")
    List<MenuItem> findByRestaurantId(@Param("id") Integer id);

    @Query("""
           SELECT m FROM MenuItem m
           WHERE m.restaurant.id = :id
           AND m.isAvailable = true
           AND m.isActive = true
           """)
    List<MenuItem> findByRestaurantIdAndIsAvailableTrue(@Param("id") Integer id);

    @Query("SELECT m FROM MenuItem m WHERE m.isVegetarian = true AND m.isActive = true")
    List<MenuItem> findByIsVegetarianTrue();

    @Query("""
           SELECT m FROM MenuItem m
           WHERE m.price BETWEEN :min AND :max
           AND m.isActive = true
           """)
    List<MenuItem> findByPriceBetween(
            @Param("min") double min,
            @Param("max") double max);
}