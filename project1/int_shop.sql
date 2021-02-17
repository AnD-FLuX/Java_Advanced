DROP DATABASE IF EXISTS int_shop;
CREATE DATABASE int_shop CHAR SET utf8;
USE int_shop;

CREATE TABLE user(
	id INT NOT NULL primary key auto_increment,
    email VARCHAR(50) NOT NULL,
	firstName VARCHAR(50) NOT NULL,
	lastName VARCHAR(50) NOT NULL,
    role  VARCHAR(25) NOT NULL
);

CREATE TABLE product(
	id INT NOT NULL primary key auto_increment,
	name VARCHAR(45) NOT NULL,
    description text NOT NULL,
	price INT(11) NOT NULL
);

CREATE TABLE bucket(
	id INT NOT NULL primary key auto_increment,
	userId	INT NOT NULL,
	productId INT NOT NULL,
    nowDate Timestamp default now()
    );

CREATE INDEX idx_email ON user(email);

ALTER TABLE bucket ADD FOREIGN KEY (userId) REFERENCES user (id);
ALTER TABLE bucket ADD FOREIGN KEY (productId) REFERENCES product (id);


SELECT * FROM user;

SELECT * FROM product;

SELECT * FROM bucket;