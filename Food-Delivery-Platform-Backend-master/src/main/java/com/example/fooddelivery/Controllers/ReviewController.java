package com.example.fooddelivery.Controllers;

import com.example.fooddelivery.DTO.Request.ReviewRequestDTO;
import com.example.fooddelivery.DTO.Response.ReviewResponseDTO;
import com.example.fooddelivery.Services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    // Submit restaurant review
    @PostMapping("/restaurant/{restaurantId}/customer/{customerId}")
    public ResponseEntity<ReviewResponseDTO> leaveRestaurantReview(@PathVariable Integer restaurantId, @PathVariable Integer customerId, @RequestBody ReviewRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.leaveRestaurantReview(customerId, restaurantId, dto.getRating(), dto.getComment()));
    }

    // Submit driver review → 201
    @PostMapping("/driver/{driverId}/customer/{customerId}")
    public ResponseEntity<ReviewResponseDTO> leaveDriverReview(@PathVariable Integer driverId, @PathVariable Integer customerId, @RequestBody ReviewRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.leaveDriverReview(customerId, driverId, dto.getRating(), dto.getComment()));
    }

    // Get all reviews for a restaurant
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<Page<ReviewResponseDTO>> getRestaurantReviews(
            @PathVariable Integer restaurantId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(
                reviewService.getReviewsByRestaurant(
                        restaurantId,
                        page,
                        size
                )
        );
    }

    // Get all reviews for a driver
    @GetMapping("/driver/{driverId}")
    public ResponseEntity<List<ReviewResponseDTO>> getDriverReviews(@PathVariable Integer driverId) {

        return ResponseEntity.ok(reviewService.getReviewsByDriver(driverId));
    }

    // Soft-delete a review
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Integer reviewId) {

        reviewService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }

    // Extended Endpoints

    // GET /api/reviews/restaurant/{restaurantId}/average
    @GetMapping("/restaurant/{restaurantId}/average")
    public ResponseEntity<Double> getRestaurantAverageRating(
            @PathVariable Integer restaurantId) {

        return ResponseEntity.ok(
                reviewService.getRestaurantAverageRating(restaurantId)
        );
    }


    @GetMapping("/driver/{driverId}/average")
    public ResponseEntity<Double> getDriverAverageRating(
            @PathVariable Integer driverId) {

        return ResponseEntity.ok(
                reviewService.getDriverAverageRating(driverId)
        );
    }

}