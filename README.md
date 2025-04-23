# product-transaction

Product Transaction Service - Tabcorp

## Overview

A Spring Boot microservice that accepts transaction data in **JSON or Binary format** and stores it in an **in-memory H2
database** for reporting. Built with Java 17 and Spring Boot.

## Features

- Accepts transactions via POST in JSON or binary format
- Stores transactions in H2 in-memory database
- Preloaded `Customer` and `Product` tables
- Dockerized application setup
- REST API for transaction input

## Tech Stack

- Java 17+
- Spring Boot
- Spring Data JPA
- H2 Database
- Docker & Docker Compose
- Maven

## Requirements

- Java 17+
- Maven
- Docker (for containerized setup): Optional

1. **Clone the repository**
   ```bash
   git clone https://github.com/SonalikaMeher9020/product-transaction.git
   cd product-transaction
   ```

2. **Run from containers (optional)**
   ```bash
   docker-compose up -d
   ```

3. **compile from terminal**
   ```bash
   mvn clean install
   ```
4. **Run and Run from terminal**
### Set profile
   Dspring.profiles.active=local
   ```bash
   java -jar product-transaction-0.0.1-SNAPSHOT.jar
   ```

### Configuration

The application can be configured by modifying the `src/main/resources/application.yml` file:

## Authentication

Added Basic Auth. user: admin & password:admin12

## API Endpoints

#### Create Transaction

```bash
curl --location 'http://localhost:8080/transaction' \
--header 'Accept: application/json' \
--header 'Authorization: Basic YWRtaW46YWRtaW4xMjM=' \
--header 'Content-Type: application/json' \
--data '{
    "transactionTime": "2025-04-24T14:56:00",
    "customerId": 10002,
    "quantity": 20,
    "productCode": "PRODUCT_001"
  }'
```

Response:

```json
  Transaction saved successfully.
```

#### Get Transaction Cost Per Customer

```bash
curl --location 'http://localhost:8080/report/total-cost-per-customer' \
--header 'Accept: application/json' \
--header 'Authorization: Basic YWRtaW46YWRtaW4xMjM='
```

Response:

```json
[
  {
    "customerId": 10001,
    "totalCost": 50.00
  },
  {
    "customerId": 10002,
    "totalCost": 1200.00
  },
  {
    "customerId": 10003,
    "totalCost": 600.00
  },
  {
    "customerId": 10004,
    "totalCost": 400.00
  }
]
```

#### Get Transaction Cost Per Product

```bash
curl --location 'http://localhost:8080/report/total-cost-per-product' \
--header 'Accept: application/json' \
--header 'Authorization: Basic YWRtaW46YWRtaW4xMjM='
```

Response:

```json
[
  {
    "productCode": "PRODUCT_002",
    "totalCost": 800.00
  },
  {
    "productCode": "PRODUCT_003",
    "totalCost": 400.00
  },
  {
    "productCode": "PRODUCT_001",
    "totalCost": 2050.00
  }
]
```

#### Get Australia customer count

```bash
curl --location 'http://localhost:8080/report/australia-customer-count' \
--header 'Accept: application/json' \
--header 'Authorization: Basic YWRtaW46YWRtaW4xMjM='
```

Response:

```json
3
```

