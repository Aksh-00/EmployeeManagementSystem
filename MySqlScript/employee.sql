create database db1;

use db1;

create table employee(Id INT NOT NULL, Name VARCHAR(255) NOT NULL,DateOfJoining VARCHAR(20),BasicPay int,DA int,HRA int,Gross int,Tax int,Net int,PRIMARY KEY(Id));

insert into employee values(100,'Akshaya','01-AUG-2022',36000,15000,10000,61000,9900,51100);

insert into employee values(101,'Geetakshi','01-AUG-2022',37000,16000,11000,64000,10000,54000);

insert into employee values(102,'Gopi','01-AUG-2022',37000,16000,11000,64000,10000,54000);

select * from employee;

