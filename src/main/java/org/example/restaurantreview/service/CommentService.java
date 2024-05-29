package org.example.restaurantreview.service;

import org.example.restaurantreview.dto.request.CreateCommentReq;
import org.example.restaurantreview.dto.request.UpdateCommentReq;
import org.example.restaurantreview.dto.response.CommentResponse;
import org.example.restaurantreview.entity.Comment;

import java.util.List;

public interface CommentService {
    CommentResponse createComment(CreateCommentReq request) ;

    CommentResponse getCommentByUserIdAndRestaurantId(Long userId, Long restaurantId);

    Comment getCommentById(Long id) ;

    List<CommentResponse> getUserComment(Long userId);

    List<CommentResponse> getRestaurantCommentByRestaurantId(Long restaurantId);

    CommentResponse updateComment(UpdateCommentReq request);

    void deleteComment(Long id);
}
