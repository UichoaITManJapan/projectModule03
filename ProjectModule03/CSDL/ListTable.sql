create database WAREHOUSE_MANAGEMENT;
use WAREHOUSE_MANAGEMENT;
-- tạo bảng product với các trường và kiểu dữ liệu tương ứng
create table product (
  product_Id char(5) primary key,
  product_name   varchar(100) not null unique,
  manufacturer   varchar(200) not null,
  created        date,
  batch          smallint not null,
  quantity       int not null default 0,
  product_status bit default 1
);
-- tạo bảng employee với các trường và kiểu dữ liệu tương ứng
create table employee (
  emp_Id        char(5) primary key,
  emp_name      varchar(100) not null unique,
  birth_of_date date,
  email         varchar(100) not null,
  phone         varchar(100) not null,
  address       text not null,
  emp_status    smallint not null
);
-- tạo bảng account với các trường và kiểu dữ liệu tương ứng
create table accounts (
  acc_id      int primary key auto_increment,
  user_name   varchar(30) not null unique,
  passwords   varchar(30) not null,
  permission  bit default 1,
  emp_id      char(5) not null unique,
  foreign key (emp_id) references employee (emp_Id),
  acc_status  bit default 1
);
-- tạo bảng bill với các trường và kiểu dữ liệu tương ứng
create table bill (
  bill_id        int primary key auto_increment,
  bill_code      varchar(10),
  bill_type      bit not null,
  emp_id_created char(5) not null,
  foreign key (emp_id_created) references employee (emp_id),
  createds       date,
  emp_id_auth    char(5) not null,
  foreign key (emp_id_auth) references employee (emp_id),
  auth_date      date,
  bill_status    smallint not null default 0
);
-- tạo bảng bill_detail với các trường và kiểu dữ liệu tương ứng
  create table bill_detail (
  bill_detail_id int primary key auto_increment,
  bill_id        int not null,
  foreign key (bill_id) references bill (bill_id),
  product_id     char(5) not null,
  foreign key (product_id) references product (product_id),
  quantity       int not null check (quantity > 0),
  price          float not null check (price > 0)
  );