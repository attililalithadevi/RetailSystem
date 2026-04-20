CREATE DATABASE IF NOT EXISTS customerproductmgmtdb;
USE customerproductmgmtdb;

CREATE TABLE IF NOT EXISTS customer (
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_name VARCHAR(45),
    customer_email VARCHAR(45),
    customer_type ENUM('Silver', 'Gold', 'Platinum'),
    CONSTRAINT chk_customer_type CHECK (customer_type IN ('Silver', 'Gold', 'Platinum'))
);

CREATE TABLE IF NOT EXISTS product (
    product_id INT PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(45),
    price DOUBLE,
    stock INT
);

CREATE TABLE IF NOT EXISTS cust_order (
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    billing_amount DOUBLE,
    customer_id INT,
    order_date DATE,
    product_id INT,
    quantity INT,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
    FOREIGN KEY (product_id) REFERENCES product(product_id)
);

INSERT INTO customer (customer_name, customer_email, customer_type) VALUES
('John Doe', 'johndoe@gmailoo.com', 'Platinum'),
('Jane Smith', 'janesmith@gmailoo.com', 'Platinum'),
('Robert Brown', 'robertbrown@gmailoo.com', 'Platinum'),
('Emily Johnson', 'emilyjohnson@gmailoo.com', 'Gold'),
('Michael Wilson', 'michaelwilson@gmailoo.com', 'Silver');

INSERT INTO product (product_name, price, stock) VALUES
('Wireless Mouse', 24.99, 40),
('Bluetooth Keyboard', 79.99, 4),
('USB-C Hub', 15.50, 20),
('Laptop Stand', 45.00, 15),
('External Hard Drive', 60.75, 5);

INSERT INTO cust_order (billing_amount, customer_id, order_date, product_id, quantity) VALUES
(49.99, 1, '2024-11-01', 1, 2),
(150.50, 2, '2024-11-02', 2, 1),
(25.75, 3, '2024-11-03', 3, 3),
(200.00, 4, '2024-11-04', 4, 1),
(75.99, 5, '2024-11-05', 5, 2);