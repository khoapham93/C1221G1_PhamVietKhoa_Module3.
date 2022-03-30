create database furama_manager;
use furama_manager;

create table `position`
(
	position_id   int auto_increment primary key,
	position_name varchar(100)
);

create table academic_level
(
	academic_id int auto_increment primary key,
	level_name  varchar(50)
);

create table department
(
	department_id   int auto_increment primary key,
	department_name varchar(100)
);

create table employee
(
	employee_id   int auto_increment primary key,
	`name`        varchar(50) not null,
	birthday      date        not null,
	id_card       varchar(20) not null,
	salary        double      not null,
	phone         varchar(20) not null,
	email         varchar(45),
	address       varchar(200),
	position_id   int,
	academic_id   int,
	department_id int,
	foreign key (position_id) references `position` (position_id),
	foreign key (academic_id) references academic_level (academic_id),
	foreign key (department_id) references department (department_id)
);

create table customer_type
(
	customer_type_id int auto_increment primary key,
	`value`          varchar(45)
);

create table customer
(
	customer_id      int auto_increment primary key,
	`name`           varchar(50) not null,
	birthday         date        not null,
	id_card          varchar(20) not null,
	salary           double      not null,
	phone            varchar(20) not null,
	email            varchar(45),
	address          varchar(200),
	position_id      int,
	academic_id      int,
	department_id    int,
	customer_type_id int unique,
	foreign key (position_id) references `position` (position_id),
	foreign key (academic_id) references academic_level (academic_id),
	foreign key (department_id) references department (department_id)
);

create table rent_type
(
	rent_type_id   int not null auto_increment primary key,
	rent_type_name varchar(45)
);

create table facility_type
(
	facility_type_id int not null auto_increment primary key,
	`value`          varchar(45)
);

create table facility
(
	facility_id      int         not null auto_increment primary key,
	facility_name    varchar(45) not null,
	floor_square     double,
	rental_fee       double      not null,
	maximum_people   int,
	facility_type_id int         not null,
	rent_type_id     int         not null,
	room_standard    varchar(45),
	description      varchar(45),
	pool_square      double,
	floor            int,
	foreign key (facility_type_id) references facility_type (facility_type_id),
	foreign key (rent_type_id) references rent_type (rent_type_id)
);

create table contract
(
	contract_id int    not null auto_increment primary key,
	date_signed date   not null,
	date_end    date   not null,
	deposit     double not null,
	employee_id int    not null,
	customer_id int    not null,
	facility_id int    not null,
	foreign key (employee_id) references employee (employee_id),
	foreign key (customer_id) references customer (customer_id),
	foreign key (facility_id) references facility (facility_id)

);

create table service_include
(
	service_include_id   int         not null auto_increment primary key,
	service_include_name varchar(45) not null,

);