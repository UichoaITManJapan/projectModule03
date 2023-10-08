use WAREHOUSE_MANAGEMENT;
-- nghiệp vụ làm cho bảng user
-- 1. danh sách phiếu nhập theo trạng thái phiếu nhập
/*select b.bill_id, b.bill_code, b.emp_id_created, b.createds
from bill b
join employee e on b.emp_id_created = e.emp_id
join accounts a on e.emp_id = a.emp_id
where b.bill_type = 1 and a.permission = 1 and b.bill_status = 0
*/
-- tạo procedure
DELIMITER ##
create procedure listOfEntriesByStatusOfUser(in billType bit, in billStatus smallint)
BEGIN
 select b.bill_id, b.bill_code, b.emp_id_created, b.createds
from bill b
join employee e on b.emp_id_created = e.emp_id
join accounts a on e.emp_id = a.emp_id
where b.bill_type = billType and a.permission = 1 and b.bill_status = billStatus;
END ##
DELIMITER ;

-- tạo mới phiếu nhập xuất
-- tạo procedure thêm mới phiếu nhâp/xuất
DELIMITER ##
create procedure createBill(
  bill_code_in      varchar(10),
  billType      bit,
  emp_id_created_in char(5),
  createds_in       date,
  emp_id_auth_in    char(5),
  auth_date_in      date,
  bill_status_in    smallint
  )
BEGIN
insert into bill ( bill_code, bill_type, emp_id_created, createds,
  emp_id_auth, auth_date, bill_status)
  values (bill_code_in, billType, emp_id_created_in, createds_in, emp_id_auth_in, auth_date_in, bill_status_in);
END ##
DELIMITER ;
