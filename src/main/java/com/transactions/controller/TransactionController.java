package com.transactions.controller;

import com.transactions.entity.Transaction;
import com.transactions.exception.ErrorResponse;
import com.transactions.exception.InvalidTransactionException;
import com.transactions.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Object> createTransaction(@RequestBody Transaction transaction) {
        try {
            transactionService.validateTransaction(transaction);
            transactionService.saveTransaction(transaction);
            return ResponseEntity.ok("Transaction saved successfully.");
        } catch (InvalidTransactionException ex) {
            ErrorResponse errorResponse = new ErrorResponse(
                    400,
                    ex.getMessage(),
                    HttpStatus.BAD_REQUEST.toString()
            );
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}
