package org.example.restaurantreview.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record UpdateCommentReq (Long id,
                                @Min(message = "Service rating must be between 1 and 10", value = 1) @Max(message = "Service rating must be between 1 and 10", value = 10) int serviceRating,
                                @Min(message = "Taste rating must be between 1 and 10", value = 1) @Max(message = "Taste rating must be between 1 and 10", value = 10) int tasteRating,
                                @Min(message = "Price rating must be between 1 and 10", value = 1) @Max(message = "Price rating must be between 1 and 10", value = 10) int priceRating,
                                String commentText){
}
