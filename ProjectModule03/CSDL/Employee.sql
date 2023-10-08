SELECT * FROM WAREHOUSE_MANAGEMENT.employee;
INSERT INTO employee (emp_id,emp_name, birth_of_date, email,phone,address,emp_status)
VALUES ('E0001','Nguyen Van B', '2006/12/12','b@gmail.com','07023738111','ha Noi',0);
INSERT INTO employee (emp_id,emp_name, birth_of_date, email,phone,address,emp_status)
VALUES ('E0002','Nguyen Van c', '2008-10-02','c@gmail.com','07023238111','Hai Duong',1);
INSERT INTO employee (emp_id,emp_name, birth_of_date, email,phone,address,emp_status)
VALUES ('EM001','Nguyen Van d', '2011-10-04','d@gmail.com','070-9123-81701','Quang Nam',1),
       ('EM002','Nguyen Van e', '2011-10-04','d@gmail.com','070-9123-81701','Quang Nam',1),
       ('EM003','Nguyen Van f', '2011-10-04','d@gmail.com','070-9123-81701','Quang Nam',1),
       ('EM004','Nguyen Van g', '2011-10-04','d@gmail.com','070-9123-81701','Quang Nam',0),
       ('EM005','Nguyen Van s', '2011-10-04','d@gmail.com','070-9123-81701','Quang Nam',0),
       ('EM006','Nguyen Van h', '2011-10-04','d@gmail.com','070-9123-81701','Quang Nam',0),
       ('EM007','Nguyen Van n', '2011-10-04','d@gmail.com','070-9123-81701','Quang Nam',0),
       ('EM008','Nguyen Van v', '2011-10-04','d@gmail.com','070-9123-81701','Quang Nam',0),
       ('EM009','Nguyen Van k', '2011-10-04','d@gmail.com','070-9123-81701','Quang Nam',0),
       ('EM010','Nguyen Van l', '2011-10-04','d@gmail.com','070-9123-81701','Quang Nam',1),
       ('EM011','Nguyen Van q', '2011-10-04','d@gmail.com','070-9123-81701','Quang Nam',0),
       ('EM012','Nguyen Van jk', '2011-10-04','d@gmail.com','070-9123-81701','Quang Nam',1),
       ('EM013','Nguyen Van y', '2011-10-04','d@gmail.com','070-9123-81701','Quang Nam',1),
       ('EM014','Nguyen Van u', '2011-10-04','d@gmail.com','070-9123-81701','Quang Nam',0),
       ('EM015','Nguyen Van i', '2011-10-04','d@gmail.com','070-9123-81701','Quang Nam',1),
       ('EM016','Nguyen Van o', '2011-10-04','d@gmail.com','070-9123-81701','Quang Nam',0),
       ('EM017','Nguyen Van p', '2011-10-04','d@gmail.com','070-9123-81701','Quang Nam',0),
       ('EM018','Nguyen Van @', '2011-10-04','d@gmail.com','070-9123-81701','Quang Nam',1),
       ('EM019','Nguyen Van j', '2011-10-04','d@gmail.com','070-9123-81701','Quang Nam',0),
       ('EM020','Nguyen Van á', '2011-10-04','d@gmail.com','070-9123-81701','Quang Nam',1),
       ('EM021','Nguyen Van dsd', '2011-10-04','d@gmail.com','070-9123-81701','Quang Nam',1),
       ('EM022','Nguyen Van cx', '2011-10-04','d@gmail.com','070-9123-81701','Quang Nam',0),
       ('EM023','Nguyen Van za', '2011-10-04','d@gmail.com','070-9123-81701','Quang Nam',0),
       ('EM024','Nguyen Van qq2', '2011-10-04','d@gmail.com','070-9123-81701','Quang Nam',1),
       ('EM025','Nguyen Van dcv', '2011-10-04','d@gmail.com','070-9123-81701','Quang Nam',0),
       ('EM026','Nguyen Van dmk', '2011-10-04','d@gmail.com','070-9123-81701','Quang Nam',1);
-- taoj procedure hiiển thị thông tin nhân viên mỗi trang hiển thị tối đa 10 nhân viên
DELIMITER ##
create procedure getAllEmployeeByPage(in pageNumber int)
BEGIN
declare startRow int;
-- tính toán dong bắt đầu và dòng kết thúc
    SET startRow = (pageNumber - 1) * 10;
    -- lấy danh sách sản phẩm
    SELECT emp_Id,emp_name,birth_of_date ,email,phone,address,emp_status
    FROM employee
    ORDER BY emp_name
    LIMIT startRow,10;
END ##
DELIMITER ;

-- tạo procedure thêm mới nhân viên
DELIMITER ##
create procedure createEmployee(
  emp_Id_in        char(5),
  emp_name_in      varchar(100),
  birth_of_date_in date,
  email_in         varchar(100),
  phone_in         varchar(100),
  address_in       text,
  emp_status_in    smallint
)
BEGIN
insert into employee (emp_Id,emp_name,birth_of_date ,email,phone,address,emp_status)
values (emp_Id_in,emp_name_in,birth_of_date_in,email_in,phone_in,address_in,emp_status_in);
END ##
DELIMITER ;

-- tạo procedure cập nhật nhân viên theo mã nhân viên hoặc tên nhân viên
DELIMITER ##
create procedure updateEmployee(
   emp_Id_in        char(5),
  emp_name_in      varchar(100),
  birth_of_date_in date,
  email_in         varchar(100),
  phone_in         varchar(100),
  address_in       text,
  emp_status_in    smallint
)
BEGIN
update employee
set emp_name = emp_name_in,
    birth_of_date = birth_of_date_in,
    email = emaild_in,
    phone = phone_in,
    address = address_in,
    emp_status = emp_status_in
where emp_Id = emp_Id_in;    
END ##
DELIMITER ;

-- tạo procedure lấy thông tin nhân viên theo mã nhân viên
DELIMITER ##
create procedure getEmployeeById( in emp_Id_in char(5))
BEGIN
SELECT emp_Id,emp_name,birth_of_date ,email,phone,address,emp_status
    FROM employee where emp_Id = emp_Id_in;
END ##
DELIMITER ;

-- tạo procedure tìm kiếm nhân viên theo tên nhân viên
DELIMITER ##
create procedure searchEmployeetByName( in input_emp_name varchar(102))
BEGIN
select emp_Id,emp_name,birth_of_date ,email,phone,address,emp_status
from employee
where emp_name LIKE concat('%',input_emp_name,'%')
order by emp_id;
END ##
DELIMITER ;

-- tạo procedure cập nhật trạng thái nhân viên nhưng khi trạng thái là nghỉ chế độ haowcj nghir việc thì trạng thái tài khaonr là Block
DELIMITER ##
create procedure updateEmployeeStatus(in emp_id_in char(5) , in newStatus int)
BEGIN
-- cập nhật trạng thái nhân viên
  update employee set emp_status = case 
	when newStatus = 0 then 0
    when newStatus = 1 then 1
    when newStatus = 2 then 2
    else emp_status
    end
    where emp_id = emp_id_in;
-- cập nhật trạng thái tài khoản khi newStatus = 1 và 2
  update accounts set acc_status = case when newStatus = 0 then 1 else 0
  end
  where emp_id = emp_id_in;
END ##
DELIMITER ;
drop procedure if exists updateEmployeeStatus;

INSERT INTO employee (emp_id,emp_name, birth_of_date, email,phone,address,emp_status)
VALUES ('EMP01','Tran tien cuong', '1996-10-09','c@gmail.com','07023238111','Hai Duong',1);