package org.example.restaurantreview.repository;

import org.example.restaurantreview.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findByUserIdAndRestaurantId(Long userId, Long restaurantId);
    List<Comment> findByRestaurantId(Long restaurantId);
    List<Comment> findByUserId(Long restaurantId);

}