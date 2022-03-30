create database student_management;
create table `student`(
id int not null primary key,
name varchar(45),
age int,
country varchar(45)
);
create table `class`(
id int not null primary key,
name varchar(45)
);
create table `teacher`(
id int not null primary key,
name varchar(45),
age int,
country varchar(45)
);