package com.transactions.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

//@Table("product")
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "product")
public class Product {

   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
   @Column(name = "productcode")
    private String productCode;

    private BigDecimal cost;

    private String status;

}
