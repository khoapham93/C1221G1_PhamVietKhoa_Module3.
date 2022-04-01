CREATE DATABASE demo;
USE demo;

CREATE TABLE product
(
    id                  int         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    product_code        varchar(20) NOT NULL,
    product_name        varchar(55),
    product_price       double,
    product_amount      int,
    product_description varchar(200),
    product_status      varchar(50)
);
INSERT INTO product
VALUES (1, 'sp01', 'Clear men', 50000, 50, 'clear men 2021', 'còn hàng'),
       (2, 'sp02', 'Hảo hảo', 4000, 200, 'aciecook việt nam', 'năm 2022'),
       (3, 'sp03', 'gấu đỏ', 3500, 50, 'gấu đỏ 2021', 'còn hàng'),
       (4, 'sp04', 'Omachi', 8000, 60, 'masan 2022', 'gần hết hạn'),
       (5, 'sp05', 'Custas', 55000, 20, 'orion 2021', 'còn hàng'),
       (6, 'sp06', 'chocopie', 30000, 15, 'orion 2021', 'còn hàng'),
       (7, 'sp07', 'Xmen', 80000, 23, 'Xmen 2021', 'còn hàng'),
       (8, 'sp08', 'Romano', 90000, 21, 'Romano 2021', 'còn hàng');
INSERT INTO product
VALUES (9, 'sp09', 'Hảo hảo', 3500, 50, 'Hảo hảo 2020', 'còn hàng');


-- index
-- Tạo Unique Index trên bảng Products (sử dụng cột productCode để tạo chỉ mục)
CREATE UNIQUE INDEX product_code_idx ON product (product_code);
-- Tạo Composite Index trên bảng Products (sử dụng 2 cột productName và productPrice)
CREATE UNIQUE INDEX product_name_price_idx ON product (product_name, product_price);
-- Sử dụng câu lệnh EXPLAIN để biết được câu lệnh SQL của bạn thực thi như nào
EXPLAIN
SELECT *
FROM product
WHERE product_code = 'sp01';
EXPLAIN
SELECT *
FROM product
WHERE product_name = 'Hảo hảo'
  AND product_price > 3500;


-- view
-- Tạo view lấy về các thông tin: productCode, productName, productPrice, productStatus từ bảng products.
CREATE VIEW product_views AS
SELECT product_code, product_name, product_price, product_status
FROM product;

SELECT *
FROM product_views;

CREATE OR REPLACE VIEW product_views AS
SELECT product_code, product_name, product_price, product_status
FROM product
WHERE product_status = 'còn hàng';

SELECT *
FROM product_views;
DROP VIEW demo.product_views;

-- Store procedure
-- Tạo store procedure lấy tất cả thông tin của tất cả các sản phẩm trong bảng product
DELIMITER //
CREATE PROCEDURE find_all_product()
BEGIN
    SELECT * FROM product;
END //
DELIMITER ;
CALL find_all_product();

-- Tạo store procedure thêm một sản phẩm mới
DELIMITER //
CREATE PROCEDURE add_new_product(IN code varchar(20),
                                 IN `name` varchar(55),
                                 IN price double,
                                 IN amount int)
BEGIN
    INSERT INTO product (product_code, product_name, product_price, product_amount) VALUE (code, `name`, price, amount);
END //
DELIMITER ;

CALL add_new_product('sp11', 'Aone', 35000, 50);
CALL find_all_product();

-- Tạo store procedure sửa thông tin sản phẩm theo id
DELIMITER //
CREATE PROCEDURE update_product_name_by_id(IN input_id int,
                                           IN `name` varchar(55))
BEGIN
    UPDATE product SET product_name = `name` WHERE id = input_id;
END //
DELIMITER ;

CALL update_product_name_by_id(9, 'Mì ba miền');
CALL find_all_product();

-- Tạo store procedure xoá sản phẩm theo id
DELIMITER //
CREATE PROCEDURE delete_product_by_id(IN input_id int)
BEGIN
    DELETE FROM demo.product where id = input_id;
END //
DELIMITER ;

CALL delete_product_by_id(9);
CALL find_all_product();