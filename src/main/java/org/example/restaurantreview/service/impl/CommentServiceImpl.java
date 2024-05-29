package org.example.restaurantreview.service.impl;

import org.example.restaurantreview.dto.request.CreateCommentReq;
import org.example.restaurantreview.dto.request.UpdateCommentReq;
import org.example.restaurantreview.dto.response.CommentResponse;
import org.example.restaurantreview.entity.Comment;
import org.example.restaurantreview.entity.Restaurant;
import org.example.restaurantreview.entity.User;
import org.example.restaurantreview.exception.CommentNotFoundException;
import org.example.restaurantreview.exception.UserCommentAlreadyExistsException;
import org.example.restaurantreview.repository.CommentRepository;
import org.example.restaurantreview.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserServiceImpl userServiceImpl;
    private final RestaurantServiceImpl restaurantServiceImpl;

    public CommentServiceImpl(CommentRepository commentRepository, UserServiceImpl userServiceImpl, RestaurantServiceImpl restaurantServiceImpl) {
        this.commentRepository = commentRepository;
        this.userServiceImpl = userServiceImpl;
        this.restaurantServiceImpl = restaurantServiceImpl;
    }


    public CommentResponse createComment(CreateCommentReq request) {

        Optional<Comment> existingComment = commentRepository.findByUserIdAndRestaurantId(request.userId(), request.restaurantId());
        if (existingComment.isPresent()) {
            throw new UserCommentAlreadyExistsException("User has already commented on this restaurant");
        }
        Comment saveComent = mapToComment(request);
        Comment newComment = commentRepository.save(saveComent);
        updateRestaurantAverageRating(newComment);
        return mapToResponse(newComment);
    }

    public CommentResponse getCommentByUserIdAndRestaurantId(Long userId, Long restaurantId) {
        Comment comment = commentRepository.findByUserIdAndRestaurantId(userId, restaurantId)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found "));
        return mapToResponse(comment);
    }

    public Comment getCommentById(Long id) {
        return commentRepository.findById(id).orElseThrow(() ->
                new CommentNotFoundException("comment not found id:" + id));
    }

    public List<CommentResponse> getUserComment(Long userId) {
        List<Comment> commentList = commentRepository.findByUserId(userId);
        List<CommentResponse> responseList = new ArrayList<>();
        for (Comment comment : commentList) {
            responseList.add(mapToResponse(comment));
        }
        return responseList;
    }

    public List<CommentResponse> getRestaurantCommentByRestaurantId(Long restaurantId) {
        List<Comment> commentList = commentRepository.findByRestaurantId(restaurantId);
        List<CommentResponse> responseList = new ArrayList<>();
        for (Comment comment : commentList) {
            responseList.add(mapToResponse(comment));
        }
        return responseList;
    }

    public CommentResponse updateComment(UpdateCommentReq request) {
        Comment toUpdate = getCommentById(request.id());
        toUpdate.setTasteRating(request.tasteRating());
        toUpdate.setServiceRating(request.serviceRating());
        toUpdate.setPriceRating(request.priceRating());
        toUpdate.setCommentText(request.commentText());
        Comment updatedComment = commentRepository.save(toUpdate);
        updateRestaurantAverageRating(updatedComment);
        return mapToResponse(updatedComment);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    private Comment mapToComment(CreateCommentReq request) {
        Comment comment = new Comment();
        User user = userServiceImpl.getUserById(request.userId());
        Restaurant restaurant = restaurantServiceImpl.getRestaurantByRestaurantId(request.restaurantId());

        comment.setUser(user);
        comment.setRestaurant(restaurant);
        comment.setServiceRating(request.serviceRating());
        comment.setPriceRating(request.priceRating());
        comment.setTasteRating(request.tasteRating());
        comment.setCommentText(request.commentText());

        return comment;
    }

    public CommentResponse mapToResponse(Comment comment) {
        CommentResponse response = new CommentResponse(comment.getId(), comment.getUser().getUserName(), comment.getRestaurant().getName(),
                comment.getServiceRating(), comment.getTasteRating(), comment.getPriceRating(), comment.getCommentText());

        return response;
    }

    private void updateRestaurantAverageRating(Comment comment) {
        double averageRating = (comment.getServiceRating() + comment.getPriceRating() + comment.getTasteRating()) / 3.0;
        restaurantServiceImpl.updateRestaurantAverageRating(comment.getRestaurant().getId(), averageRating);
    }
}

