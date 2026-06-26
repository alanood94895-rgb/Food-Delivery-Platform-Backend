package com.example.fooddelivery.Services;

import com.example.fooddelivery.DTO.Response.CustomerResponseDTO;
import com.example.fooddelivery.DTO.Response.ReviewResponseDTO;
import com.example.fooddelivery.Entities.*;
import com.example.fooddelivery.Exceptions.ResourceNotFoundException;
import com.example.fooddelivery.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    DeliveryDriverRepository deliveryDriverRepository;


    @Autowired
    OrderRepository orderRepository;

    @Autowired
    DeliveryRepository deliveryRepository;
    private Integer restaurantId;


    public ReviewResponseDTO leaveRestaurantReview(Integer customerId, Integer restaurantId, int rating, String comment) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Active Customer not found with id: " + customerId));

        Restaurant restaurant = restaurantRepository.findActiveById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Active Restaurant not found with id: " + restaurantId));

        Review review = new Review();
        review.setTargetType("RESTAURANT");
        review.setRating(rating);
        review.setComment(comment);
        review.setCreatedAt(LocalDateTime.now());
        review.setCustomer(customer);
        review.setRestaurant(restaurant);
        review.setDeliveryDriver(null);

        review.setCreatedDate(LocalDateTime.now());
        review.setUpdatedDate(LocalDateTime.now());
        review.setIsActive(true);

        Review savedReview = reviewRepository.save(review);
        return ReviewResponseDTO.fromEntity(savedReview);
    }


    public ReviewResponseDTO leaveDriverReview(Integer customerId, Integer driverId, int rating, String comment) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Active Customer not found with id: " + customerId));

        DeliveryDriver driver = deliveryDriverRepository.findById(driverId)
                .orElseThrow(() -> new ResourceNotFoundException("Active Delivery Driver not found with id: " + driverId));

        Review review = new Review();
        review.setTargetType("DRIVER");
        review.setRating(rating);
        review.setComment(comment);
        review.setCreatedAt(LocalDateTime.now());
        review.setCustomer(customer);
        review.setRestaurant(null);
        review.setDeliveryDriver(driver);

        review.setCreatedDate(LocalDateTime.now());
        review.setUpdatedDate(LocalDateTime.now());
        review.setIsActive(true);

        Review savedReview = reviewRepository.save(review);
        return ReviewResponseDTO.fromEntity(savedReview);
    }

    // Get Reviews By Restaurant
    public List<ReviewResponseDTO> getReviewsByRestaurant(Integer restaurantId) {
        List<Review> reviews = reviewRepository.findByRestaurantId(restaurantId);
        return ReviewResponseDTO.fromEntity(reviews);
    }


    // Get Reviews By Driver
    public List<ReviewResponseDTO> getReviewsByDriver(Integer driverId) {
        List<Review> reviews = reviewRepository.findByDeliveryDriverId(driverId);
        return ReviewResponseDTO.fromEntity(reviews);
    }

    // Soft-delete Review
    public void deleteReview(Integer reviewId) {
        Review review = reviewRepository.findActiveById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id: " + reviewId));

        review.setIsActive(false);
        review.setUpdatedDate(LocalDateTime.now());
        reviewRepository.save(review);
    }



    //For Reporting
    // Revenue for a restaurant on a specific day
    public Double getRevenueForRestaurantOnDate(Integer restaurantId, LocalDate date) {
        return orderRepository.sumDeliveredRevenueForDate(restaurantId, date);
    }

    // Total lifetime orders for a restaurant
    public Long getTotalOrdersForRestaurant(Integer restaurantId) {
        return orderRepository.countCompletedOrdersForRestaurant(restaurantId);
    }

    // Top 10 customers by loyalty points
    public List<CustomerResponseDTO> getTopLoyaltyCustomers() {
        return CustomerResponseDTO.fromEntity(
                customerRepository.findTop10ByLoyaltyPoints());
    }

    // Top drivers by completed deliveries
    public List<Map<String, Object>> getDriversLeaderboard() {
        return deliveryRepository.findDriversLeaderboard();
    }

    // Platform daily summary
    public Map<String, Object> getPlatformDailySummary(LocalDate date) {
        Long totalOrders = orderRepository.countOrdersForDate(date);
        Double totalRevenue = orderRepository.sumDeliveredRevenueForDate(restaurantId, date);
        Double totalDeliveryFees = orderRepository.sumDeliveryFeesForDate(date);

        Map<String, Object> summary = new HashMap<>();
        summary.put("date", date);
        summary.put("totalOrders", totalOrders);
        summary.put("totalRevenue", totalRevenue != null ? totalRevenue : 0.0);
        summary.put("totalDeliveryFees", totalDeliveryFees != null ? totalDeliveryFees : 0.0);

        return summary;
    }
}