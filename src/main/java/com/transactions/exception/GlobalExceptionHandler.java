package com.transactions.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
//
//    @ExceptionHandler(InvalidTransactionException.class)
//    public ResponseEntity<ErrorResponse> handleInvalidTransactionException(InvalidTransactionException ex) {
//        ErrorResponse errorResponse = new ErrorResponse( HttpStatus.BAD_REQUEST.value(),ex.getMessage(),"Bad Request");
//        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//    }
}
