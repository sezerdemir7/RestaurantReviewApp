package org.example.restaurantreview.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {

        Map<String, String> erors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            ;
            erors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(erors, BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> productOutOfStockException(UserNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), NOT_FOUND);
    }



    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<?> restaurantNotFoundException(RestaurantNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), NOT_FOUND);
    }


    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<?> commentNotFoundException(CommentNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), NOT_FOUND);
    }



    @ExceptionHandler(UserCommentAlreadyExistsException.class)
    public ResponseEntity<?> userCommentAlreadyExistsException(UserCommentAlreadyExistsException exception) {
        return new ResponseEntity<>(exception.getMessage(), BAD_REQUEST);
    }


    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<?> emailAlreadyExistsException(EmailAlreadyExistsException exception) {
        return new ResponseEntity<>(exception.getMessage(), BAD_REQUEST);
    }

    @ExceptionHandler(UnAuthorizedAccessException.class)
    public ResponseEntity<?> unauthorizedAccessException(UnAuthorizedAccessException exception) {
        return new ResponseEntity<>(exception.getMessage(), FORBIDDEN);
    }
}