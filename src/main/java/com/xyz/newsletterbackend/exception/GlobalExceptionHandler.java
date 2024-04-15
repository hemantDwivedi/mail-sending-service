package com.xyz.newsletterbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorDetails> handleApiException(ApiException exception){
        return new ResponseEntity<>(
                new ErrorDetails(
                        exception.getMessage(),
                        exception.getStatus()
                )
                , HttpStatus.BAD_REQUEST
        );
    }
}
