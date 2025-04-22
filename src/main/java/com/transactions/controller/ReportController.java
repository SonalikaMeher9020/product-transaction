package com.transactions.controller;

import com.transactions.dto.CustomerCostReportDTO;
import com.transactions.dto.ProductCostReportDTO;
import com.transactions.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;


    @GetMapping("/total-cost-per-customer")
    public List<CustomerCostReportDTO> getTotalCostPerCustomer() {
        return reportService.getTotalCostPerCustomer();
    }

    @GetMapping("/total-cost-per-product")
    public List<ProductCostReportDTO> getTotalCostPerProduct() {
        return reportService.getTotalCostPerProduct();
    }

    @GetMapping("/australia-customer-count")
    public Long getAustraliaCustomerTransactionCount() {
        return reportService.getAustraliaCustomerTransactionCount();
    }
}
