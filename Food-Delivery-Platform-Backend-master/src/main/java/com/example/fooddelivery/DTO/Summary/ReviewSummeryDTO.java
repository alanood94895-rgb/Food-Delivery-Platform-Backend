package com.example.fooddelivery.DTO.Summary;

import com.example.fooddelivery.Entities.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewSummeryDTO {
    private Long id;
    private Integer rating;
    private String comment;

    public static ReviewSummeryDTO fromEntity(Review review) {

        ReviewSummeryDTO dto = new ReviewSummeryDTO();

        dto.setRating(review.getRating());
        dto.setComment(review.getComment());

        return dto;
    }
}