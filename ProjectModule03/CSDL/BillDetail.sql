SELECT * FROM WAREHOUSE_MANAGEMENT.bill_detail;
-- taoj procedure dựa vào mã phiếu để lấy thông tin chi tiết phiếu nhập
DELIMITER ##
create procedure getBillDetailById(in bill_id_in int, in billType bit)
BEGIN
select d.bill_detail_id, d.bill_id, d.product_id, d.quantity, d.price, b.bill_type
from bill_detail d
join bill b on b.bill_id = d.bill_id
where d.bill_id = bill_id_in;
END ##
DELIMITER ;

-- taoj procedure cập nhật chi tiết phiếu nhập
DELIMITER ##
create procedure updateBillDetail(
bill_detail_id_in int,
  bill_id_in        int,
  product_id_in     char(5),
  quantity_in       int,
  price_in          float
)
BEGIN
update bill_detail set 
  bill_id = bill_id_in,
  product_id = product_id_in,
  quantity = quantity_in,
  price = price_in
where bill_detail_id = bill_detail_id_in;  
END ##
DELIMITER ;


-- test thu
INSERT INTO bill_detail (bill_detail_id, bill_id, product_id,quantity, price)
VALUES (1,18, 'P0001', 25,26000);
INSERT INTO bill_detail (bill_id, product_id,quantity, price)
VALUES (37, 'PS001', 15,26300);
INSERT INTO bill_detail (bill_detail_id,bill_id, product_id,quantity, price)
VALUES (2,37, 'SP001', 35,29300);
INSERT INTO bill_detail (bill_id, product_id,quantity, price)
VALUES (18, 'PS001', 11,24350)

-- tao procedure hiển thị chi tiết phiếu
DELIMITER ##
create procedure getAllBillDetail(in billType bit)
BEGIN
select d.bill_detail_id, d.bill_id, d.product_id, d.quantity, d.price, b.bill_type
from bill_detail d
join bill b on b.bill_id = d.bill_id
where b.bill_type = billType;
END ##
DELIMITER ;

SELECT p.product_id, p.product_name, p.quantity + SUM(bd.quantity) AS total_quantity
FROM product p
JOIN bill_detail bd ON p.product_id = bd.product_id
GROUP BY p.product_id, p.product_name, p.quantity;
-- tạo procedure duyệt phiếu nhập
DELIMITER ##
create procedure updateProductQuantity(in billType bit,in bill_id_in int)
BEGIN
-- tính tổng số sản phẩm
 SELECT p.product_id, p.product_name, p.quantity + SUM(bd.quantity) AS total_quantity
 FROM product p
JOIN bill_detail bd ON p.product_id = bd.product_id
 GROUP BY p.product_id, p.product_name, p.quantity;
-- cập nhật trạng thái của phiếu là duyêt
update bill set bill_status = 2 where bill_status in (0,1) and bill_id = bill_id_in;
END ##
DELIMITER ;
drop procedure if exists updateProductQuantity;