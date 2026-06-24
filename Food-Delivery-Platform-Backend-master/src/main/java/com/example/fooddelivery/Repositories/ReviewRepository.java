package com.example.fooddelivery.Repositories;

import com.example.fooddelivery.Entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Integer> {
}
