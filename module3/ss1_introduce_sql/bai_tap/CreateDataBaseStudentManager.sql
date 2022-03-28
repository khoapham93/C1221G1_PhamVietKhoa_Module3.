create table `student-management`.`Student`(
id int not null primary key,
name varchar(45),
age int,
country varchar(45)
);
create table `student-management`.`Class`(
id int not null primary key,
name varchar(45)
);
create table `student-management`.`Teacher`(
id int not null primary key,
name varchar(45),
age int,
country varchar(45)
);