package com.transactions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.transactions.repository")
@EntityScan("com.transactions.entity")
public class ProductTransactionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductTransactionApplication.class, args);
	}

}
