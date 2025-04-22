package com.transactions.service;

import com.transactions.dto.CustomerCostReportDTO;
import com.transactions.dto.ProductCostReportDTO;
import com.transactions.entity.Customer;
import com.transactions.entity.Product;
import com.transactions.entity.Transaction;
import com.transactions.repository.CustomerRepository;
import com.transactions.repository.ProductRepository;
import com.transactions.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class ReportServiceTest {
    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private ReportService reportService;

    private List<Transaction> transactionList;
    private List<Product> productList;
    private List<Customer> customerList;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        Customer customer1 = new Customer(10001, "Tony", "Stark", "tony.stark@gmail.com", "Australia");
        Customer customer2 = new Customer(10002, "Bruce", "Banner", "bruce.banner@gmail.com", "US");

        Product product1 = new Product("PRODUCT_001", new BigDecimal("50.00"), "Active");
        Product product2 = new Product("PRODUCT_002", new BigDecimal("100.00"), "Active");

        Transaction txn1 = new Transaction(null, LocalDateTime.now(), 10001, "PRODUCT_001", 2);
        Transaction txn2 = new Transaction(null, LocalDateTime.now(), 10002, "PRODUCT_001", 1);
        Transaction txn3 = new Transaction(null, LocalDateTime.now(), 10001, "PRODUCT_002", 3);

        transactionList = List.of(txn1, txn2, txn3);
        productList = List.of(product1,product2);
        customerList = List.of(customer1,customer2);

        when(customerRepository.findAll()).thenReturn(customerList);
        when(productRepository.findAll()).thenReturn(productList);
        when(transactionRepository.findAll()).thenReturn(transactionList);
    }

    @Test
    public void testGetTotalCostPerCustomer() {
        List<CustomerCostReportDTO> results = reportService.getTotalCostPerCustomer();

        assertEquals(2, results.size());

        CustomerCostReportDTO customerCostReportDTO = results.stream()
                .filter(r -> r.getCustomerId() == 10002)
                .findFirst()
                .orElse(null);

        assertNotNull(customerCostReportDTO);
        assertEquals(new BigDecimal("50.00"), customerCostReportDTO.getTotalCost());
    }

    @Test
    public void testGetTotalCostPerProduct() {
        List<ProductCostReportDTO> results = reportService.getTotalCostPerProduct();

        assertEquals(2, results.size());

        ProductCostReportDTO productCostReportDTO = results.stream()
                .filter(r -> r.getProductCode().equals("PRODUCT_001"))
                .findFirst()
                .orElse(null);

        assertNotNull(productCostReportDTO);
        assertEquals(new BigDecimal("150.00"), productCostReportDTO.getTotalCost());
    }

    @Test
    public void testGetAustraliaCustomerTransactionCount() {
        long count = reportService.getAustraliaCustomerTransactionCount();

        assertEquals(2, count);
    }

}
