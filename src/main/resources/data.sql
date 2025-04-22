---- Create Customer Table
CREATE TABLE customer (
--    id BIGINT PRIMARY KEY,
    customerid BIGINT PRIMARY KEY,
    firstname VARCHAR(100),
    lastname VARCHAR(100),
    email VARCHAR(255),
    location VARCHAR(100)
);

------ Create Product Table
CREATE TABLE product (
--    id BIGINT PRIMARY KEY,
    productcode VARCHAR PRIMARY KEY,
    cost INT,
    status VARCHAR(50)
);
----
------ Create Transaction Table
CREATE TABLE transaction (
    id BIGINT PRIMARY KEY,
    transactiontime TIMESTAMP,
    customerid BIGINT,
    quantity INT,
    productcode VARCHAR,
    FOREIGN KEY (customerid) REFERENCES customer(customerid),
    FOREIGN KEY (productcode) REFERENCES product(productcode)
);
--
---- Insert Customer Data
INSERT INTO customer (customerid, firstname, lastname, email, location) VALUES
(10001, 'Tony', 'Stark', 'tony.stark@gmail.com', 'Australia'),
(10002, 'Bruce', 'Banner', 'bruce.banner@gmail.com', 'US'),
(10003, 'Steve', 'Rogers', 'steve.rogers@hotmail.com', 'Australia'),
(10004, 'Wanda', 'Maximoff', 'wanda.maximoff@gmail.com', 'US'),
(10005, 'Natasha', 'Romanoff', 'natasha.romanoff@gmail.com', 'Canada');

-------- Insert Product Data
INSERT INTO product (productcode, cost, status) VALUES
('PRODUCT_001', 50, 'Active'),
('PRODUCT_002', 100, 'Inactive'),
('PRODUCT_003', 200, 'Active');

------ Insert Transaction Data
INSERT INTO transaction (id,transactiontime, customerid, quantity, productcode) VALUES
(101,'2023-11-01 12:00:00', 10001, 1, 'PRODUCT_001'),
(102,'2023-11-02 14:00:00', 10002, 2, 'PRODUCT_002'),
(103,'2023-11-02 14:00:00', 10003, 3, 'PRODUCT_002'),
(104,'2023-11-02 14:00:00', 10004, 2, 'PRODUCT_003'),
(105,'2023-11-02 14:00:00', 10003, 3, 'PRODUCT_002');
