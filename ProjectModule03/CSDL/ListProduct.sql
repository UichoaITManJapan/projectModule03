SELECT * FROM WAREHOUSE_MANAGEMENT.product;
-- tạo procedure hiển thị danh sách sản phẩm. Mỗi lần hiển thị tối đa 10 sản phẩm
DELIMITER //

CREATE PROCEDURE getAllProductByPage(IN pageNumber INT)
BEGIN
declare startRow int;
-- tính toán dong bắt đầu và dòng kết thúc
    SET startRow = (pageNumber - 1) * 10;
    -- lấy danh sách sản phẩm
    SELECT product_Id, product_name, manufacturer, created, batch, quantity, product_status
    FROM product
    ORDER BY product_id
    LIMIT startRow,10;
END //

DELIMITER ;

drop procedure if exists getAllProductByPage;

-- gọi procedure trên
call getAllProductByPage(1);
call getAllProductByPage(2);
-- test thử
INSERT INTO product (product_Id,product_name,manufacturer,created,
        batch,quantity,product_status )
VALUES ('P0002','May giặt2','NSX1','2010-11-09',2,20,1),
       ('P0003','May giặt3','NSX2','2010-12-09',1,20,0),('P0004','May giặt4','NSX1','2010-11-09',2,20,1),
       ('P0005','May giặt5','NSX3','2010-01-09',1,20,0),('P0006','May giặt6','NSX1','2010-11-09',2,20,1),
       ('P0007','May giặt7','NSX4','2010-11-09',1,20,0),('P0008','May giặt8','NSX1','2010-11-09',2,20,1),
       ('P0009','May giặt9','NSX5','2010-11-09',1,20,0),('P0010','May giặt10','NSX1','2010-11-09',2,20,1),
       ('P0011','May giặtad','NSX6','2010-11-09',1,20,0),('P0012','May giặt12','NSX1','2010-11-09',2,20,1),
       ('P0013','May giặtasc','NSX121','2010-11-09',1,20,0),('P0014','May giặt14','NSX1','2010-11-09',2,20,1),
       ('P0015','May giặtq','NSX1sf','2010-11-09',1,20,0),('P0016','May giặt16','NSX1','2010-11-09',2,20,1),
       ('P0017','May giặtgj','NSX1sf','2010-11-09',1,20,0),('P0018','May giặt18','NSX1','2010-11-09',2,20,1),
       ('P0019','May giặtsxv','NSX1qg','2010-11-09',1,20,0),('P0020','May giặt20','NSX1','2010-11-09',2,20,1),
       ('P0021','May giặtqf','NSX1fgj','2010-11-09',1,20,0),('P0022','May giặt22','NSX1','2010-11-09',2,20,1),
       ('P0023','May giặtzc','NSX1qfgf','2010-11-09',1,20,0),('P0024','May giặt24','NSX1','2010-11-09',2,20,1),
       ('P0025','May giặtthrt','NSX1qghq','2010-11-09',1,20,0),('P0026','May giặt26','NSX1','2010-11-09',2,20,1),
       ('P0027','May giặtc','NSX1dsv','2010-11-09',2,20,0),('P0028','May giặt28','NSX1','2010-11-09',2,20,1),
       ('P0029','May giặtr','NSX1jyt','2010-11-09',2,20,0),('P0030','May giặt30','NSX1','2010-11-09',2,20,1),
       ('P0031','May giặtmht','NSX1rehe','2010-11-09',2,20,0),('P0032','May giặt32','NSX1','2010-11-09',2,20,1),
       ('P0033','May giặtaz','NSX1q','2010-11-09',2,20,0),('P0034','May giặt34','NSX1','2010-11-09',2,20,1),
       ('P0035','May giặtty3','NSX1jl','2010-11-09',2,20,0),('P0036','May giặt36','NSX1','2010-11-09',2,20,1),
       ('P0037','May giặtasvc','NSX1za','2010-11-09',2,20,0),('P0038','May giặt38','NSX1','2010-11-09',2,20,1),
       ('P0039','May giặtcxv','NSX1qdw','2010-11-09',2,20,0),('P0040','May giặt40','NSX1','2010-11-09',2,20,1),
       ('P0041','May giặtvcas','NSX1wqr','2010-11-09',2,20,0),('P0042','May giặt42','NSX1','2010-11-09',2,20,1);
       
-- tạo procedure thêm mới sản phẩm mà không thêm mới số lượng sản phẩm quantity
DELIMITER //
create procedure createProduct(  
  product_Id_in char(5),
  product_name_in   varchar(100),
  manufacturer_in   varchar(200),
  created_in        date,
  batch_in          smallint,
  quantity_in       int,
  product_status_in bit
  )
BEGIN
insert into product (product_Id, product_name, manufacturer, created, batch, quantity, product_status)
values (product_Id_in,product_name_in,manufacturer_in,created_in,batch_in,quantity_in,product_status_in);
END //
DELIMITER ;       

-- Tạo procedure cập nhật sản phẩm mà không có chứa số lượng sản phẩm quantity
DELIMITER ##
create procedure updateProductWithoutQuantity(
  product_Id_in char(5),
  product_name_in   varchar(100),
  manufacturer_in   varchar(200),
  created_in        date,
  batch_in          smallint,
  product_status_in bit
)
BEGIN
update product
set product_name = product_name_in,
    manufacturer = manufacturer_in,
    created = created_in,
    batch = batch_in,
    product_status = product_status_in
where product_Id = product_Id_in;    
END ##
DELIMITER ;
drop procedure if exists updateProductWithoutQuantity;

-- Tạo procedure dựa vào mã sản phẩm để lấy thông tin sản phẩm
DELIMITER ##
create procedure getProductById( in product_Id_in char(5))
BEGIN
SELECT product_Id, product_name, manufacturer, created, batch, quantity, product_status
    FROM product where product_Id = product_Id_in;
END ##
DELIMITER ;

-- tạo procedure tìm kiếm sản phẩm theo tên sản phẩm
DELIMITER ##
create procedure searchProductByName( in input_product_name varchar(102))
BEGIN
select product_Id, product_name, manufacturer, created, batch, quantity, product_status
from product
where product_name LIKE concat('%',input_product_name,'%')
order by product_id;
END ##
DELIMITER ;

-- tạo procedure cập nhật trạng thái sản phẩm
DELIMITER $$
create procedure updateProductStatus( in product_Id_in char(5), in product_status_in bit)
BEGIN
update product set product_status = product_status_in
where product_id = product_id_in;
END $$
DELIMITER ;
drop procedure if exists updateProductStatus;