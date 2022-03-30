create database score_manager;
use score_manager;

create table student
(
student_code varchar(20) not null primary key,
student_name varchar(50),
birthday date,
classes varchar(20),
gender varchar(20)
);

create table teacher
(
teacher_code varchar(20) not null primary key,
teacher_name varchar(50),
phone varchar(15)
);

create table `subject`
(
subject_code varchar(20) not null primary key,
subject_name varchar(50),
teacher_code varchar(20),
foreign key (teacher_code) references teacher(teacher_code)
);

create table score_table
(
student_code varchar(20) not null,
subject_code varchar(20) not null,
score float,
date_exam date,
primary key (student_code, subject_code),
foreign key(student_code) references student(student_code),
foreign key(subject_code) references `subject`(subject_code)
);





