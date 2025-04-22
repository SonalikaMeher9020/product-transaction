package com.transactions.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@Entity(name = "transaction")
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(name = "transactiontime")
    private LocalDateTime transactionTime;


    @Column(name = "customerid")
    private Integer customerId;


    @Column(name = "productcode")
    private String productCode;


    private Integer quantity;

}
