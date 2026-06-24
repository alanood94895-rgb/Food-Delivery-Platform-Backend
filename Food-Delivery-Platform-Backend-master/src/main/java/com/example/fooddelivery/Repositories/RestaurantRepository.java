package com.example.fooddelivery.Repositories;

import com.example.fooddelivery.Entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Query("SELECT r FROM Restaurant r WHERE r.cuisineType = :cuisineType AND r.isActive = true")
    List<Restaurant> findByCuisineTypeIgnoreCase(@Param("cuisineType") String cuisineType);

    @Query("SELECT r FROM Restaurant r WHERE r.acceptingOrders = true AND r.isActive = true")
    List<Restaurant> findByAcceptingOrdersTrue();

    @Query("SELECT r FROM Restaurant r WHERE r.deliveryFee <= :fee AND r.isActive = true")
    List<Restaurant> findByDeliveryFeeLessThanEqual(@Param("fee") double fee);

    @Query("SELECT r FROM Restaurant r WHERE r.restaurantOwner.id = :ownerId AND r.isActive = true")
    List<Restaurant> findByRestaurantOwnerId(@Param("ownerId") Integer ownerId);

    @Query("SELECT r FROM Restaurant r WHERE LOWER(r.name) LIKE LOWER(CONCAT('%', :keyword, '%')) AND r.isActive = true")
    List<Restaurant> searchByKeyword(@Param("keyword") String keyword);
}
