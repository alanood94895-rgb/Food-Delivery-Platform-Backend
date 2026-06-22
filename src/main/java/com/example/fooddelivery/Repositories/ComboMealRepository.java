package com.example.fooddelivery.Repositories;

import com.example.fooddelivery.Entities.ComboMeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComboMealRepository extends JpaRepository <ComboMeal, Integer> {
    @Query("SELECT c FROM ComboMeal c WHERE c.restaurant.id = :id AND c.isActive = true")
    List<ComboMeal> findByRestaurantId(@Param("id") Integer id);

    @Query("""
           SELECT c
           FROM ComboMeal c
           JOIN c.menuItems m
           WHERE m.id = :menuItemId
           AND c.isActive = true
           """)
    List<ComboMeal> findComboMealsContainingMenuItem(
            @Param("menuItemId") Integer menuItemId);
}
