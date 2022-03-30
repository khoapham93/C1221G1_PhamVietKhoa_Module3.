create database product_manager;
use product_manager;

create table material(
material_code varchar(20) not null primary key,
material_name varchar(50)
);

create table supplier(
supplier_code varchar(20) not null primary key,
supplier_name varchar(50),
address varchar(200)
);

create table supplier_phone(
supplier_code varchar(20),
phone varchar(15),
primary key (supplier_code,phone),
foreign key (supplier_code) references supplier(supplier_code)
);

create table `order`(
order_number int not null primary key,
order_date date,
supplier_code varchar(20),
foreign key (supplier_code) references supplier(supplier_code)
);

create table order_detail(
order_number int,
material_code varchar(20),
primary key (order_number,material_code),
foreign key (order_number) references `order`(order_number),
foreign key (material_code) references material(material_code)
);

create table delivery_note(
delivery_note_number int not null primary key,
delivery_note_date date
);

create table received_note(
received_note_number int not null primary key,
received_note_date date
);

create table delivery_note_detail(
delivery_note_number int,
material_code varchar(20),
delivery_unit_price double,
delivery_amount double,
primary key (delivery_note_number,material_code),
foreign key (delivery_note_number) references delivery_note(delivery_note_number),
foreign key (material_code) references material(material_code)
);

create table received_note_detail(
received_note_number int,
material_code varchar(20),
received_unit_price double,
received_amount double,
primary key (received_note_number,material_code),
foreign key (received_note_number) references received_note(received_note_number),
foreign key (material_code) references material(material_code)
);
