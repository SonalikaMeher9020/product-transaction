package com.transactions.service;

import com.transactions.entity.Customer;
import com.transactions.entity.Product;
import com.transactions.entity.Transaction;
import com.transactions.exception.InvalidTransactionException;
import com.transactions.repository.CustomerRepository;
import com.transactions.repository.ProductRepository;
import com.transactions.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public void saveTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public void validateTransaction(Transaction transaction) {
        //  Transaction date must not be in the past
        if (transaction.getTransactionTime().isBefore(LocalDateTime.now())) {
            throw new InvalidTransactionException("Transaction time must not be in the past.");
        }

        //Fetch product to validate further
        Product product = productRepository.findById(transaction.getProductCode())
                .orElseThrow(() -> new InvalidTransactionException("Invalid product code: " + transaction.getProductCode()));
        //  Product must be active
        if (!"Active".equalsIgnoreCase(product.getStatus())) {
            throw new InvalidTransactionException("Product is not active: " + product.getProductCode());
        }
        //Validate Customer
        Customer customer = customerRepository.findById(Long.valueOf(transaction.getCustomerId()))
                .orElseThrow(() -> new InvalidTransactionException("Invalid Customer: " + transaction.getCustomerId()));


        //  Total cost (quantity * product cost) must not exceed 5000
        BigDecimal totalCost = product.getCost().multiply(BigDecimal.valueOf(transaction.getQuantity()));
        if (totalCost.compareTo(BigDecimal.valueOf(5000)) > 0) {
            throw new InvalidTransactionException("Total cost exceeds 5000. Cost: " + totalCost);
        }
    }

}
