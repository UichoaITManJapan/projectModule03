SELECT * FROM WAREHOUSE_MANAGEMENT.accounts;
-- tạo procedure kiểm tra userName và password có tồn tại trong bảng account hay không
DELIMITER //

CREATE PROCEDURE get_user_password(IN p_user_name VARCHAR(30), IN p_password VARCHAR(30), OUT p_exists BIT)
BEGIN
    DECLARE user_count INT;
    
    -- Đếm số lần xuất hiện của user_name và password trong bảng accounts
    SELECT COUNT(*) INTO user_count
    FROM accounts
    WHERE user_name = p_user_name AND passwords = p_password;
    
    -- Nếu user_count > 0, tồn tại tên người dùng và mật khẩu
    IF user_count > 0 THEN
    -- tên người dùng và mật khẩu tồn tại
        SET p_exists = 1;
    ELSE
    -- ngược lại ~_~
        SET p_exists = 0;
    END IF;
END //

DELIMITER ;
 drop procedure if exists get_user_password;

-- tạo procedure kiểm tra permission là 0 (admin) hay 1(user)
DELIMITER //

CREATE PROCEDURE check_permission(IN p_user_name VARCHAR(30), OUT p_permission BIT)
BEGIN
    -- Lấy giá trị permission từ bảng accounts dựa trên user_name
    SELECT permission INTO p_permission
    FROM accounts
    WHERE user_name = p_user_name;
END //

DELIMITER ;
drop procedure if exists check_permission;
-- test thử login
INSERT INTO accounts (acc_id,user_name, passwords, permission,emp_id,acc_status)
VALUES (1,'admin', 'admin_password', 0,'E0001',1);
INSERT INTO accounts (acc_id,user_name, passwords, permission,emp_id,acc_status)
VALUES (2,'cuong', 'cuong1996', 1,'E0002',0);

-- taoj procedure dựa vào mã Id lấy thông tin tài khoản
DELIMITER ##
create procedure getAccoutsById( in acc_id_in int)
BEGIN
select acc_id, user_name, passwords, permission, emp_id, acc_status
from accounts where acc_id = acc_id_in;
END ##
DELIMITER ;

-- tạo procedure lấy thông tin tài khoản
DELIMITER ##
create procedure getAllAccount()
BEGIN
select   acc_id, user_name, passwords, permission, emp_id, acc_status
from accounts ;
END ##
DELIMITER ;

-- tạo procedure tạo mới tài khoản
DELIMITER ##
create procedure createAccounts(
  user_name_in   varchar(30),
  passwords_in   varchar(30),
  permission_in  bit,
  emp_id_in      char(5),
  acc_status_in  bit
)
BEGIN
insert into accounts (user_name, passwords, permission, emp_id, acc_status)
values (user_name_in, passwords_in, permission_in, emp_id_in, acc_status_in);
END ##
DELIMITER ;
drop procedure if exists createAccounts;

-- tạo procedure cập nhật trạng thái tài khoản
DELIMITER ##
create procedure updateAccountStatus (in acc_id_in int, in newStatus bit)
BEGIN
update accounts set acc_status = newStatus where acc_id = acc_id_in;
END ##
DELIMITER ;

-- tạo procedure tìm kiếm tài khoản theo username hoặc tên nhân viên
DELIMITER ##
create procedure searchAccountsByKeyWord(in searchKeyword varchar(250))
BEGIN
select acc_id, user_name, passwords, permission, emp_id, acc_status
from accounts
where user_name = searchKeyWord or emp_id = searchKeyWord;
END ##
DELIMITER ;

drop procedure if exists searchAccountsByKeyWord;

select a.acc_id, a.user_name, a.passwords,a.permission, a.emp_id,e.emp_name , a.acc_status
from accounts a
join employee e on e.emp_id = a.emp_id;
INSERT INTO accounts (acc_id,user_name, passwords, permission,emp_id,acc_status)
VALUES (4,'cuongSmiles', 'cuong0910', 1,'EMP01',0);