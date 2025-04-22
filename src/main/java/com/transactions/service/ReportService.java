package com.transactions.service;


import com.transactions.dto.CustomerCostReportDTO;
import com.transactions.dto.ProductCostReportDTO;
import com.transactions.entity.Customer;
import com.transactions.entity.Product;
import com.transactions.entity.Transaction;
import com.transactions.repository.CustomerRepository;
import com.transactions.repository.ProductRepository;
import com.transactions.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private TransactionRepository transactionRepository;


    public List<CustomerCostReportDTO> getTotalCostPerCustomer() {


        List<Transaction> transactions = transactionRepository.findAll();
        Map<String, BigDecimal> productCostMap = productRepository.findAll().stream()
                .collect(Collectors.toMap(Product::getProductCode, Product::getCost));

        Map<Integer, BigDecimal> customerCostMap = new HashMap<>();

        for (Transaction tx : transactions) {
            BigDecimal productCost = productCostMap.getOrDefault(tx.getProductCode(), BigDecimal.ZERO);
            BigDecimal txCost = productCost.multiply(BigDecimal.valueOf(tx.getQuantity()));
            customerCostMap.merge(tx.getCustomerId(), txCost, BigDecimal::add);
        }
        return customerCostMap.entrySet().stream()
                .map(e -> new CustomerCostReportDTO(e.getKey(), e.getValue()))
                .collect(Collectors.toList());


    }

    public List<ProductCostReportDTO> getTotalCostPerProduct() {
        List<Transaction> transactions = transactionRepository.findAll();
        Map<String, BigDecimal> productCostMap = productRepository.findAll().stream()
                .collect(Collectors.toMap(Product::getProductCode, Product::getCost, BigDecimal::add));

        Map<String, BigDecimal> productTotalCostMap = new HashMap<>();

        for (Transaction tx : transactions) {
            BigDecimal unitCost = productCostMap.getOrDefault(tx.getProductCode(), BigDecimal.ZERO);
            BigDecimal txCost = unitCost.multiply(BigDecimal.valueOf(tx.getQuantity()));
            productTotalCostMap.merge(tx.getProductCode(), txCost, BigDecimal::add);
        }

        return productTotalCostMap.entrySet().stream()
                .map(e -> new ProductCostReportDTO(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }

    public Long getAustraliaCustomerTransactionCount() {
        Set<Integer> australianCustomerIds = customerRepository.findAll().stream()
                .filter(c -> "Australia".equalsIgnoreCase(c.getLocation()))
                .map(Customer::getCustomerId)
                .collect(Collectors.toSet());

        return transactionRepository.findAll().stream()
                .filter(tx -> australianCustomerIds.contains(tx.getCustomerId()))
                .count();
    }
}

