package com.transactions.service;

import com.transactions.entity.Product;
import com.transactions.entity.Transaction;
import com.transactions.exception.InvalidTransactionException;
import com.transactions.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private TransactionService transactionService;

    private Transaction transaction;
    private Product product;

    @BeforeEach
    public void setUp() {
        transaction = new Transaction(101L, LocalDateTime.now().plusMinutes(10), 10001, "PRODUCT_001", 2);
        product = new Product("PRODUCT_001", new BigDecimal("100.00"), "Active");
    }

    @Test
    void shouldThrowExceptionWhenTransactionTimeIsInThePast() {
        transaction.setTransactionTime(LocalDateTime.now().minusDays(1));
        assertThrows(InvalidTransactionException.class,
                () -> transactionService.validateTransaction(transaction));
    }

    @Test
    void shouldThrowExceptionWhenProductDoesNotExist() {
        when(productRepository.findById("PRODUCT_001")).thenReturn(Optional.empty());
        assertThrows(InvalidTransactionException.class,
                () -> transactionService.validateTransaction(transaction));
    }

    @Test
    void shouldThrowExceptionWhenProductIsNotActive() {
        product.setStatus("Inactive");
        when(productRepository.findById("PRODUCT_001")).thenReturn(Optional.of(product));
        assertThrows(InvalidTransactionException.class,
                () -> transactionService.validateTransaction(transaction));
    }

    @Test
    void shouldThrowExceptionWhenTotalCostExceedsLimit() {
        product.setCost(new BigDecimal("3000")); // 3000 * 2 = 6000 > 5000
        when(productRepository.findById("PRODUCT_001")).thenReturn(Optional.of(product));
        assertThrows(InvalidTransactionException.class,
                () -> transactionService.validateTransaction(transaction));
    }

    @Test
    void shouldPassValidationForValidTransaction() {
        when(productRepository.findById("PRODUCT_001")).thenReturn(Optional.of(product));
        assertDoesNotThrow(() -> transactionService.validateTransaction(transaction));
    }
}