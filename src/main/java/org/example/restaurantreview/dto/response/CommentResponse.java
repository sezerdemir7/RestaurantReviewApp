package org.example.restaurantreview.dto.response;



public record CommentResponse(Long id,String userName,String restaurantName,
        int serviceRating, int tasteRating, int priceRating,String commentText) {

}