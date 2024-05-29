package org.example.restaurantreview.exception;

public class UserCommentAlreadyExistsException extends RuntimeException{
    public UserCommentAlreadyExistsException(String message) {
        super(message);
    }
}
