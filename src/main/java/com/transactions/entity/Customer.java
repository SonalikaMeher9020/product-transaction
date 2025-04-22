package com.transactions.entity;

import jakarta.persistence.*;
import lombok.*;

//@Table("customer")
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "customer")
public class Customer {

//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Id
//    private Integer id;

    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "customerid")
    private Integer customerId;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    private String email;

    private String location;
}