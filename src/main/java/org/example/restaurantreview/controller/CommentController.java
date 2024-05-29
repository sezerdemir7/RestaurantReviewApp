package org.example.restaurantreview.controller;

import jakarta.validation.Valid;
import org.example.restaurantreview.dto.request.CreateCommentReq;
import org.example.restaurantreview.dto.request.UpdateCommentReq;
import org.example.restaurantreview.dto.response.CommentResponse;
import org.example.restaurantreview.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/create")
    public ResponseEntity<CommentResponse> createComment(@RequestBody @Valid CreateCommentReq request) {
        return new ResponseEntity<>(commentService.createComment(request), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<CommentResponse> updateComment(@RequestBody @Valid UpdateCommentReq request) {
        return new ResponseEntity<>(commentService.updateComment(request), HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<CommentResponse>> getRestaurantComment(@PathVariable Long id) {
        return new ResponseEntity<>(commentService.getRestaurantCommentByRestaurantId(id), HttpStatus.OK);
    }

    @GetMapping("/user-restaurant")
    public ResponseEntity<CommentResponse> getCommentByUserIdAndRestaurantId(@RequestParam Long userId, @RequestParam Long restaurantId) {
        return new ResponseEntity<>(commentService.getCommentByUserIdAndRestaurantId(userId, restaurantId), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<CommentResponse>> getCommentByUserId(@RequestParam Long userId) {
        return new ResponseEntity<>(commentService.getUserComment(userId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCommentByCommentId(@PathVariable Long id) {
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
