package com.example.fooddelivery.Controllers;

import com.example.fooddelivery.DTO.Response.ReviewResponseDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    ReviewService reviewService;
    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @PostMapping("/restaurant/{restaurantId}/customer/{customerId}")
    public ResponseEntity<ReviewResponseDTO> submitRestaurantReview(@PathVariable Integer restaurantId, @PathVariable Integer customerId,
                                                                    @RequestParam int rating, @RequestParam String comment) {
        ReviewResponseDTO response = reviewService.leaveRestaurantReview(customerId, restaurantId, rating, comment);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PostMapping("/driver/{driverId}/customer/{customerId}")
    public ResponseEntity<ReviewResponseDTO> submitDriverReview(@PathVariable Integer driverId, @PathVariable Integer customerId,
                                                                @RequestParam int rating, @RequestParam String comment) {
        ReviewResponseDTO response = reviewService.leaveDriverReview(customerId, driverId, rating, comment);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<ReviewResponseDTO>> getRestaurantReviews(@PathVariable Integer restaurantId) {
        return ResponseEntity.ok(reviewService.getRestaurantReviews(restaurantId));
    }
    @GetMapping("/driver/{driverId}")
    public ResponseEntity<List<ReviewResponseDTO>> getDriverReviews(@PathVariable Integer driverId) {
        return ResponseEntity.ok(reviewService.getDriverReviews(driverId));
    }
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Integer reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }
}