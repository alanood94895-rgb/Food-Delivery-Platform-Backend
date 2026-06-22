package com.example.fooddelivery.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequestDTO {

    @Min(1)
    @Max(5)
    private Integer rating;

    @NotBlank
    private String comment;
}