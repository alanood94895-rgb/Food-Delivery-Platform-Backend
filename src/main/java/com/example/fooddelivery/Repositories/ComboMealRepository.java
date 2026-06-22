package com.example.fooddelivery.Repositories;

import com.example.fooddelivery.Entities.ComboMeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComboMealRepository extends JpaRepository <ComboMeal, Integer> {
}
