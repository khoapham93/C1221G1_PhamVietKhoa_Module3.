-- create database
CREATE DATABASE furama_manager;
USE furama_manager;

CREATE TABLE `position`
(
    position_id   int AUTO_INCREMENT PRIMARY KEY,
    position_name varchar(100)
);

CREATE TABLE academic_level
(
    academic_id int AUTO_INCREMENT PRIMARY KEY,
    level_name  varchar(50)
);

CREATE TABLE department
(
    department_id   int AUTO_INCREMENT PRIMARY KEY,
    department_name varchar(100)
);

CREATE TABLE employee
(
    employee_id   int AUTO_INCREMENT PRIMARY KEY,
    `name`        varchar(50) NOT NULL,
    birthday      date        NOT NULL,
    id_card       varchar(20) NOT NULL,
    salary        double      NOT NULL,
    phone         varchar(20) NOT NULL,
    email         varchar(45),
    address       varchar(200),
    position_id   int,
    academic_id   int,
    department_id int,
    FOREIGN KEY (position_id) REFERENCES `position` (position_id),
    FOREIGN KEY (academic_id) REFERENCES academic_level (academic_id),
    FOREIGN KEY (department_id) REFERENCES department (department_id)
);

CREATE TABLE customer_type
(
    customer_type_id int AUTO_INCREMENT PRIMARY KEY,
    `value`          varchar(45)
);

CREATE TABLE customer
(
    customer_id      int AUTO_INCREMENT PRIMARY KEY,
    customer_type_id int         NOT NULL,
    `name`           varchar(50) NOT NULL,
    birthday         date        NOT NULL,
    gender           bit(1)      NOT NULL,
    id_card          varchar(20) NOT NULL,
    phone            varchar(20) NOT NULL,
    email            varchar(45),
    address          varchar(200),
    FOREIGN KEY (customer_type_id) REFERENCES customer_type (customer_type_id)
);

CREATE TABLE rent_type
(
    rent_type_id   int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    rent_type_name varchar(45)
);

CREATE TABLE facility_type
(
    facility_type_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `value`          varchar(45)
);

CREATE TABLE facility
(
    facility_id      int         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    facility_name    varchar(45) NOT NULL,
    floor_square     double,
    rental_fee       double      NOT NULL,
    maximum_people   int,
    facility_type_id int         NOT NULL,
    rent_type_id     int         NOT NULL,
    room_standard    varchar(45),
    description      varchar(45),
    pool_square      double,
    floor            int,
    FOREIGN KEY (facility_type_id) REFERENCES facility_type (facility_type_id),
    FOREIGN KEY (rent_type_id) REFERENCES rent_type (rent_type_id)
);

CREATE TABLE contract
(
    contract_id int    NOT NULL AUTO_INCREMENT PRIMARY KEY,
    date_signed date   NOT NULL,
    date_end    date   NOT NULL,
    deposit     double NOT NULL,
    employee_id int    NOT NULL,
    customer_id int    NOT NULL,
    facility_id int    NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employee (employee_id),
    FOREIGN KEY (customer_id) REFERENCES customer (customer_id),
    FOREIGN KEY (facility_id) REFERENCES facility (facility_id)

);

CREATE TABLE service_include
(
    service_include_id   int         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    service_include_name varchar(45) NOT NULL,
    price                double      NOT NULL,
    unit                 varchar(10),
    status               varchar(45)
);

CREATE TABLE contract_detail
(
    contract_detail_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    contract_id        int NOT NULL,
    service_include_id int NOT NULL,
    amount             int NOT NULL,
    FOREIGN KEY (contract_id) REFERENCES contract (contract_id),
    FOREIGN KEY (service_include_id) REFERENCES service_include (service_include_id)
);

-- insert data into database
INSERT INTO furama_manager.position (position_id, position_name)
VALUES (1, 'Quản Lý'),
       (2, 'Nhân Viên');

INSERT INTO furama_manager.academic_level (academic_id, level_name)
VALUES (1, 'Trung Cấp'),
       (2, 'Cao Đẳng'),
       (3, 'Đại Học'),
       (4, 'Sau Đại Học');

INSERT INTO furama_manager.department (department_id, department_name)
VALUES (1, 'Sale-Marketing'),
       (2, 'Hành chính'),
       (3, 'Phục vụ'),
       (4, 'Quản lý');

INSERT INTO furama_manager.employee (employee_id, name, birthday, id_card, salary, phone, email,
                                     address, position_id, academic_id, department_id)
VALUES (1, 'Nguyễn Văn An', '1970-11-07', 456231786, 10000000, 0901234121, 'annguyen@gmail.com',
        '295 Nguyễn Tất Thành, Đà Nẵng', 1, 3, 1),
       (2, 'Lê Văn Bình', '1997-04-09', 654231234, 7000000, 0934212314, 'binhlv@gmail.com',
        '22 Yên Bái, Đà Nẵng', 1, 2, 2),
       (3, 'Hồ Thị Yến', '1995-12-12', 999231723, 14000000, 0412352315, 'thiyen@gmail.com',
        'K234/11 Điện Biên Phủ, Gia Lai', 1, 3, 2),
       (4, 'Võ Công Toản', '1980-04-04', 123231365, 17000000, 0374443232, 'toan0404@gmail.com',
        '77 Hoàng Diệu, Quảng Trị', 1, 4, 4),
       (5, 'Nguyễn Bỉnh Phát', '1999-12-09', 454363232, 6000000, 0902341231, 'phatphat@gmail.com',
        '43 Yên Bái, Đà Nẵng', 2, 1, 1),
       (6, 'Khúc Nguyễn An Nghi', '2000-11-08', 964542311, 7000000, 0978653213, 'annghi20@gmail.com',
        '294 Nguyễn Tất Thành, Đà Nẵng', 2, 2, 3),
       (7, 'Nguyễn Hữu Hà', '1993-01-01', 534323231, 8000000, 0941234553, 'nhh0101@gmail.com',
        '4 Nguyễn Chí Thanh, Huế', 2, 3, 2),
       (8, 'Nguyễn Hà Đông', '1989-09-03', 234414123, 9000000, 0642123111, 'donghanguyen@gmail.com',
        '111 Hùng Vương, Hà Nội', 2, 4, 4),
       (9, 'Tòng Hoang', '1982-09-03', 256781231, 6000000, 0245144444, 'hoangtong@gmail.com',
        '213 Hàm Nghi, Đà Nẵng', 2, 4, 4),
       (10, 'Nguyễn Công Đạo', '1994-01-08', 755434343, 8000000, 0988767111, 'nguyencongdao12@gmail.com',
        '6 Hoà Khánh, Đồng Nai', 2, 3, 2);

INSERT INTO furama_manager.customer_type (customer_type_id, value)
VALUES (1, 'Diamond'),
       (2, 'Platinium'),
       (3, 'Gold'),
       (4, 'Silver'),
       (5, 'Member');

INSERT INTO furama_manager.customer (customer_id, customer_type_id, name, birthday, gender, id_card, phone, email,
                                     address)
VALUES (1, 5, 'Nguyễn Thị Hào', '1970-11-07', FALSE, 643431213, 0945423362, 'thihao07@gmail.com',
        '23 Nguyễn Hoàng, Đà Nẵng'),
       (2, 3, 'Phạm Xuân Diệu', '1992-08-08', TRUE, 865342123, 0954333333, 'xuandieu92@gmail.com',
        'K77/22 Thái Phiên, Quảng Trị'),
       (3, 1, 'Trương Đình Nghệ', '1990-02-27', TRUE, 488645199, 0373213122, 'nghenhan2702@gmail.com',
        'K323/12 Ông Ích Khiêm, Vinh'),
       (4, 1, 'Dương Văn Quan', '1981-07-08', TRUE, 543432111, 0490039241, 'duongquan@gmail.com',
        'K453/12 Lê Lợi, Đà Nẵng'),
       (5, 4, 'Hoàng Trần Nhi Nhi', '1995-12-09', FALSE, 795453345, 0312345678, 'nhinhi123@gmail.com',
        '224 Lý Thái Tổ, Gia Lai'),
       (6, 4, 'Tôn Nữ Mộc Châu', '2005-12-06', FALSE, 732434215, 0988888844, 'tonnuchau@gmail.com',
        '37 Yên Thế, Đà Nẵng'),
       (7, 1, 'Nguyễn Mỹ Kim', '1984-04-08', FALSE, 856453123, 0912345698, 'kimcuong84@gmail.com',
        'K123/45 Lê Lợi, Hồ Chí Minh'),
       (8, 3, 'Nguyễn Thị Hào', '1999-04-08', FALSE, 965656433, 0763212345, 'haohao99@gmail.com',
        '55 Nguyễn Văn Linh, Kon Tum'),
       (9, 1, 'Trần Đại Danh', '1994-07-01', TRUE, 432341235, 0643343433, 'danhhai99@gmail.com',
        '24 Lý Thường Kiệt, Quảng Ngãi'),
       (10, 2, 'Nguyễn Tâm Đắc', '1989-07-01', TRUE, 344343432, 0987654321, 'dactam@gmail.com',
        '22 Ngô Quyền, Đà Nẵng');

INSERT INTO furama_manager.rent_type (rent_type_id, rent_type_name)
VALUES (1, 'year'),
       (2, 'month'),
       (3, 'day'),
       (4, 'hour');

INSERT INTO furama_manager.facility_type (facility_type_id, value)
VALUES (1, 'Villa'),
       (2, 'House'),
       (3, 'Room');

INSERT INTO furama_manager.facility (facility_id, facility_name, floor_square, rental_fee, maximum_people,
                                     facility_type_id, rent_type_id, room_standard, description, pool_square, floor)
VALUES (1, 'Villa Beach Front', 25000, 10000000, 10, 1, 3, 'vip', 'Có hồ bơi', 500, 4),
       (2, 'House Princess 01', 14000, 5000000, 7, 2, 2, 'vip', 'Có thêm bếp nướng', NULL, 3),
       (3, 'Room Twin 01', 5000, 1000000, 2, 3, 4, 'normal', 'Có tivi', NULL, NULL),
       (4, 'Villa No Beach Front', 22000, 9000000, 8, 1, 3, 'normal', 'Có hồ bơi', 300, 3),
       (5, 'House Princess 02', 10000, 4000000, 5, 2, 3, 'normal', 'Có thêm bếp nướng', NULL, 2),
       (6, 'Room Twin 02', 3000, 900000, 2, 3, 4, 'normal', 'Có tivi', NULL, NULL);

INSERT INTO furama_manager.service_include (service_include_id, service_include_name, price, unit, status)
VALUES (1, 'Karaoke', 10000, 'giờ', 'tiện nghi, hiện tại'),
       (2, 'Thuê xe máy', 10000, 'chiếc', 'hỏng 1 xe'),
       (3, 'Thuê xe đạp', 20000, 'chiếc', 'tốt'),
       (4, 'Buffet buổi sáng', 15000, 'suất', 'đầy đủ đồ ăn, tráng miệng'),
       (5, 'Buffet buổi trưa', 90000, 'suất', 'đầy đủ đồ ăn, tráng miệng'),
       (6, 'Buffet buổi tối', 16000, 'suất', 'đầy đủ đồ ăn, tráng miệng');

INSERT INTO furama_manager.contract (contract_id, date_signed, date_end, deposit, employee_id, customer_id, facility_id)
VALUES (1, '2020-12-08', '2020-12-08', 0, 3, 1, 3),
       (2, '2020-07-14', '2020-07-21', 200000, 7, 3, 1),
       (3, '2021-03-15', '2021-03-17', 50000, 3, 4, 2),
       (4, '2021-01-14', '2021-01-18', 100000, 7, 5, 5),
       (5, '2021-07-14', '2021-07-15', 0, 7, 2, 6),
       (6, '2021-06-01', '2021-06-03', 0, 7, 7, 6),
       (7, '2021-09-02', '2021-09-05', 100000, 7, 4, 4),
       (8, '2021-06-17', '2021-06-18', 150000, 3, 4, 1),
       (9, '2020-11-19', '2020-11-19', 0, 3, 4, 3),
       (10, '2021-04-12', '2021-04-14', 0, 10, 3, 5),
       (11, '2021-04-25', '2021-04-25', 0, 2, 2, 1),
       (12, '2021-05-25', '2021-05-27', 0, 7, 10, 1);

INSERT INTO furama_manager.contract_detail (contract_detail_id, contract_id, service_include_id, amount)
VALUES (1, 2, 4, 5),
       (2, 2, 5, 8),
       (3, 2, 6, 15),
       (4, 3, 1, 1),
       (5, 3, 2, 11),
       (6, 1, 3, 1),
       (7, 1, 2, 2),
       (8, 12, 2, 2);

-- DROP DATABASE furama_manager;