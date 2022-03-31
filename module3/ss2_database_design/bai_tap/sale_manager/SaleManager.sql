Create database sale_manager;
use sale_manager;

create table customer(
customer_id int not null primary key,
customer_name varchar(50) not null,
customer_age int
);

create table `order`(
order_id int not null primary key,
customer_id int not null,
order_date datetime not null,
order_total_price double,
foreign key (customer_id) references customer(customer_id)
);

create table product(
product_id int not null primary key,
product_name varchar(50) not null,
product_price double
);

create table order_detail(
order_id int not null,
product_id int not null,
order_quantity int,
primary key(order_id,product_id),
foreign key(order_id) references `order`(order_id),
foreign key(product_id) references product(product_id)
);
