SELECT * FROM WAREHOUSE_MANAGEMENT.bill;
insert into bill ( bill_code, bill_type, emp_id_created, createds,
  emp_id_auth, auth_date, bill_status)
  values
  ('B000000002',1,'E0001','2010-12-09','EM001','2020-11-09',1),('B000000010',0,'E0001','2010-12-09','EM001','2020-11-09',0),
  ('B000000003',1,'E0001','2010-12-09','EM001','2020-11-09',1),('B000000011',0,'E0001','2010-12-09','EM001','2020-11-09',0),
  ('B000000004',1,'E0001','2010-12-09','EM001','2020-11-09',1),('B000000021',0,'E0001','2010-12-09','EM001','2020-11-09',0),
  ('B000000005',1,'E0001','2010-12-09','EM001','2020-11-09',1),('B000000031',0,'E0001','2010-12-09','EM001','2020-11-09',0),
  ('B000000006',1,'E0001','2010-12-09','EM001','2020-11-09',1),('B000000041',0,'E0001','2010-12-09','EM001','2020-11-09',0),
  ('B000000007',1,'E0001','2010-12-09','EM001','2020-11-09',1),('B000000051',0,'E0001','2010-12-09','EM001','2020-11-09',0),
  ('B000000008',1,'E0001','2010-12-09','EM001','2020-11-09',1),('B000000061',0,'E0001','2010-12-09','EM001','2020-11-09',0),
  ('B000000009',1,'E0001','2010-12-09','EM001','2020-11-09',1),('B000000071',0,'E0001','2010-12-09','EM001','2020-11-09',0);
  
-- tạo procedure hiển thi danh sách phiếu nhập/xuất
DELIMITER ##
create procedure getAllBill(in billType bit)
BEGIN
select bill_id,bill_code, bill_type, emp_id_created, createds,
  emp_id_auth, auth_date, bill_status
from bill where bill_type = billType;
END ##
DELIMITER ; 
drop procedure if exists  getAllBill;

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

-- cập nhật phiếu nhập --
-- cập nhật phiếu nhập dựa theo mã ID hoặc mã code. khi cập nhật phiếu nhập thì câpf nhật luôn chi tiết phiếu nhập.
-- chi cập nhâtj khi trạng thái đang ở tạo hoặc huỷ
select bill_id, bill_status from bill where bill_status in (0,1);
-- tạo procedure cập nhâtj phiếu nhập theo mã hoặc mã code phiếu nhập
DELIMITER ##
create procedure updateReceiptByIdOrCode( in bill_id_in int, in bill_code_in varchar(10), in billType bit)
BEGIN
select bill_id,bill_code, bill_type, emp_id_created, createds,
  emp_id_auth, auth_date, bill_status
from bill where bill_id = bill_id_in and bill_code = bill_code_in and bill_type = billType;  
END ##
DELIMITER ;
drop procedure if exists updateReceiptByIdOrCode;

-- taoj procedure cập nhâtj phiếu xuất khi trạng thái đang ở tạo 
DELIMITER ##
create procedure updateReceiptByStatus(
  bill_id_in int,
  bill_code_in varchar(10),
  billType      bit,
  emp_id_created_in char(5),
  createds_in       date,
  emp_id_auth_in    char(5),
  auth_date_in      date,
  bill_status_in smallint
)
BEGIN
update bill set
  bill_type = billType,
  emp_id_created = emp_id_created_in,
  createds = createds_in,
  emp_id_auth = emp_id_auth_in,
  auth_date = auth_date_in,
  bill_status = bill_status_in
  where bill_id = bill_id_in and bill_code = bill_code_in
    and bill_status = 0;
END ##
DELIMITER ;
drop procedure if exists updateReceiptByStatus;

-- tao procedure duyệt phiếu nhập (Tính tổng số sản phẩm trong kho và nhập)
DELIMITER ##
create procedure updateProductQuantity(in billType bit)
BEGIN

END ##
DELIMITER ;
drop procedure if exists updateProductQuantity;
-- taoj procedure dưạ vào mã id để lấyt hông tin phiếu nhập
DELIMITER ##
create procedure getBilltById( in bill_id_in int)
BEGIN
SELECT bill_id,bill_code, bill_type, emp_id_created, createds,
  emp_id_auth, auth_date, bill_status
    FROM bill where bill_id = bill_id_in;
END ##
DELIMITER ;
UPDATE bill
SET bill_status = 0
WHERE bill_id = 18;

-- tạo procedure tìm kiếm phiếu nhập theo mã hoặc mã code phiếu nhập
DELIMITER ##
create procedure searchBillByIdAndCode( in bill_id_in int, in bill_code_in varchar(12), in billType bit)
BEGIN
select bill_id,bill_code, bill_type, emp_id_created, createds,
  emp_id_auth, auth_date, bill_status
from bill
where bill_id = bill_id_in and bill_code like concat('%',bill_code_in,'%') and bill_type = billType
order by bill_id;
END ##
DELIMITER ;
insert into bill ( bill_code, bill_type, emp_id_created, createds,
  emp_id_auth, auth_date, bill_status)
  values
  ('B999999996',1,'EMP01','2010-12-09','EM001','2020-11-09',0)