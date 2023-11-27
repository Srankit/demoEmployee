package com.demoEmployee.restApi.exception;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


    public static class ErrorResponse {
        private String message;
        private int status;

        public ErrorResponse(String message, int status) {
            this.message = message;
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public int getStatus() {
            return status;
        }
    }
}


