package com.xyz.newsletterbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorDetails> handleApiException(ApiException exception, WebRequest request){
        return new ResponseEntity<>(
                new ErrorDetails(
                        LocalDateTime.now(),
                        exception.getMessage(),
                        request.getDescription(false)
                )
                , HttpStatus.BAD_REQUEST
        );
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request){
        return new ResponseEntity<>(
                new ErrorDetails(
                        LocalDateTime.now(),
                        exception.getMessage(),
                        request.getDescription(false)
                )
                , HttpStatus.BAD_REQUEST
        );
    }
}
