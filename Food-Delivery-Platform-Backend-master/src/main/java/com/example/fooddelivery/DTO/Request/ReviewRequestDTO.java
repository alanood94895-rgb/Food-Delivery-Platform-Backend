package com.example.fooddelivery.DTO.Request;

import com.example.fooddelivery.Entities.Review;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequestDTO {
    private String targetType;
    private int rating;
    private String comment;

    public Review toEntity() {
        Review review = new Review();
        review.setTargetType(targetType);
        review.setRating(rating);
        review.setComment(comment);

        return review;
    }

    public void applyTo(Review review) {
        review.setTargetType(targetType);
        review.setRating(rating);
        review.setComment(comment);
    }
}