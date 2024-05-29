package org.example.restaurantreview.exception;

public class UnAuthorizedAccessException extends RuntimeException{
    public UnAuthorizedAccessException(String message) {
        super(message);
    }
}
