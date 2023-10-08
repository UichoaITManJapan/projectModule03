use WAREHOUSE_MANAGEMENT;
-- tạo procedure thống kê chi phí theo ngày tháng năm
DELIMITER ##
create procedure calculateCostByDate()
BEGIN
-- thống kê tổng chi phí theo ngày
 select date(b.createds) as day,
        Sum(bd.price * bd.quantity) as sumCost
 from bill b
 join bill_detail bd on b.bill_id = bd.bill_id
 where b.bill_type = 1
 group by date(b.createds);
END ##
DELIMITER ;
drop procedure if exists calculateCostByDate;
-- 
DELIMITER ##
create procedure calculateCostByMonth()
BEGIN
 -- thống kê tổng chi phí theo thangs
 select month(b.createds) as month,
        Sum(bd.price * bd.quantity) as sumCost
 from bill b
 join bill_detail bd on bd.bill_id = b.bill_id
 where b.bill_type = 1
 group by month(b.createds);
END ##
DELIMITER ;
drop procedure if exists calculateCostByMonth;
--
DELIMITER ##
create procedure calculateCostByYear()
BEGIN
 -- thống kê tổng chi phí theo Nam
 select year(b.createds) as year,
        Sum(bd.price * bd.quantity) as sumCost
 from bill b
 join bill_detail bd on bd.bill_id = b.bill_id
 where b.bill_type = 1
 group by year(b.createds);
END ##
DELIMITER ;
drop procedure if exists calculateCostByYear;

/*SELECT emp_status, COUNT(*) AS employee_count
FROM employee
GROUP BY emp_status;
*/

-- procedure hiển thị kinh phí theo khaongr thời gian
DELIMITER ##
create procedure staticCostByDatetoDate(in input_startDate date, in input_endDate date, out totalCost double )
BEGIN
select Sum(bd.price * bd.quantity) into totalCost
from bill_detail bd
join bill b on bd.bill_id = b.bill_id
where (createds between input_startDate and input_endDate)
and b.bill_type = 1;
END ##
DELIMITER ;
drop procedure if exists staticCostByDatetoDate;

/*select Sum(bd.price) 
from bill_detail bd
join bill b on bd.bill_id = b.bill_id
where (createds between '2020-11-10' and '2023-12-31')
and b.bill_type = 1;
*/
-- tạo procedure thống kê doanh thu theo ngày
DELIMITER ##
create procedure calculateRevenueByDate()
BEGIN
-- thống kê tổng doanh thu theo ngày
 select date(b.createds) as day,
        Sum(bd.price * bd.quantity) as sumRevenue
 from bill b
 join bill_detail bd on b.bill_id = bd.bill_id
 where b.bill_type = 0
 group by date(b.createds);
END ##
DELIMITER ;
-- tạo procedure thống kê doanh thu theo thang
DELIMITER ##
create procedure calculateRevenueByMonth()
BEGIN
 -- thống kê tổng doanh thu theo thangs
 select month(b.createds) as month,
        Sum(bd.price * bd.quantity) as sumRevenue
 from bill b
 join bill_detail bd on bd.bill_id = b.bill_id
 where b.bill_type = 0
 group by month(b.createds);
 
END ##
DELIMITER ;
-- tạo procedure thống kê doanh thu theo nawm
DELIMITER ##
create procedure calculateRevenueByYear()
BEGIN
 -- thống kê tổng doanh thu theo Nam
 select year(b.createds) as year,
        Sum(bd.price * bd.quantity) as sumRevenue
 from bill b
 join bill_detail bd on bd.bill_id = b.bill_id
 where b.bill_type = 0
 group by year(b.createds);
END ##
DELIMITER ;

-- procedure hiển thị odanh thu theo khaongr thời gian
DELIMITER ##
create procedure staticRevenueByDatetoDate(in input_startDate date, in input_endDate date, out totalRevenue double )
BEGIN
select Sum(bd.price * bd.quantity) into totalRevenue
from bill_detail bd
join bill b on bd.bill_id = b.bill_id
where (createds between input_startDate and input_endDate)
and b.bill_type = 0;
END ##
DELIMITER ;
drop procedure if exists staticRevenueByDatetoDate;

-- thống kê số nhân viên theo trạng thái
DELIMITER ##
create procedure staticEmployeeByStatus()
BEGIN
select emp_status, count(*) as employee_count
from employee
group by emp_status;
END ##
DELIMITER ;
-- thống kê số lượng sản phẩm nhập/xuất nhiều nhất trong khoảng thoừi gian
/*SELECT
    bd.product_id,
    p.product_name,
    SUM(bd.quantity) AS total_quantity
FROM
    bill_detail bd
JOIN
    bill b ON bd.bill_id = b.bill_id
JOIN
    product p ON bd.product_id = p.product_id
WHERE
    b.createds BETWEEN '2023-01-01' AND '2023-12-31'
GROUP BY
    bd.product_id, p.product_name
ORDER BY
    total_quantity DESC
LIMIT 1;
*/
DELIMITER ##
create procedure staticQuantityMOstDateToDate(in input_startdate date,
    in input_endDate date, in billType bit)
BEGIN
 select bd.product_id,
        p.product_name,
        Sum(bd.quantity) as total_quantity
 from bill_detail bd
 join bill b on bd.bill_id = b.bill_id
 join product p on bd.product_id = p.product_id
 where (createds between input_startdate and input_endDate) and b.bill_type = billType
 group by bd.product_id,
        p.product_name
order by total_quantity DESC
LIMIT 1;
END ##
DELIMITER ;
drop procedure if exists staticQuantityMOstDateToDate;
-- Thống kê sản phẩm nhập/xuất ít nhất trong khoảng thời gian
DELIMITER ##
create procedure staticQuantitySmallestDateToDate(in input_startdate date,
    in input_endDate date,in billType bit)
BEGIN
 select bd.product_id,
        p.product_name,
        Sum(bd.quantity) as total_quantity
 from bill_detail bd
 join bill b on bd.bill_id = b.bill_id
 join product p on bd.product_id = p.product_id
 where (createds between input_startdate and input_endDate) and b.bill_type = billType
 group by bd.product_id,
        p.product_name
order by total_quantity ASC
LIMIT 1;
END ##
DELIMITER ;
drop procedure if exists staticProductQuantityDateToDate;