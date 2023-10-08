package ra.adminBussiness;

import ra.connection.ConnectionDB;
import ra.entity.Bill;
import ra.entity.Bill_Detail;
import ra.entity.Product;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BillBussiness {
/**
 * Phương thức kết nối CSDL để hiển thị danh sách phiêú  xuất
 */
    public static List<Bill> getAllBill(boolean billType) {
        Connection connection = null;
        CallableStatement callSt = null;
        List<Bill> billList = null;
        try {
            connection = ConnectionDB.openConnection();
            callSt = connection.prepareCall("{call getAllBill(?)}");
            // set tham số đầu vào
            callSt.setBoolean(1,billType);
            // thực hiện gọi procedure
            ResultSet resultSet = callSt.executeQuery();
            billList = new ArrayList<>();
            // lấy dưc liệu resultSEt trả về
            while (resultSet.next()) {
                Bill bill = new Bill();
                bill.setBill_id(resultSet.getInt("bill_id"));
                bill.setBill_code(resultSet.getString("bill_code"));
                bill.setBill_type(resultSet.getBoolean("bill_type"));
                bill.setEmp_id_created(resultSet.getString("emp_id_created"));
                bill.setCreated(resultSet.getDate("createds"));
                bill.setEmp_id_auth(resultSet.getString("emp_id_auth"));
                bill.setAuth_date(resultSet.getDate("auth_date"));
                bill.setBill_status(resultSet.getInt("bill_status"));
                billList.add(bill);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection,callSt);
        }
        return billList;
    }
    /**
     * Phương thức kết nối CSDL dựa vào mã id lấy thông tin của chi tiết phiếu
     * */
    public static Bill_Detail getBillDetailById(int billId,boolean billType) {
        Connection connection = null;
        CallableStatement callSt = null;
        Bill_Detail billDetail = null;
        try {
            connection = ConnectionDB.openConnection();
            callSt = connection.prepareCall("{call getBillDetailById(?,?)}");
            // set tham số đầu vào
            callSt.setInt(1,billId);
            callSt.setBoolean(2,billType);
            // thực hiện gọi procedure
            ResultSet resultSet = callSt.executeQuery();
            // lấy dữ liệu resultSet đẩy vào đối tượng product trả về
            while (resultSet.next()) {
                billDetail = new Bill_Detail();
               billDetail.setBill_detail_id(resultSet.getInt("bill_detail_id"));
               billDetail.setBill_id(resultSet.getInt("bill_id"));
               billDetail.setProduct_id(resultSet.getString("product_id"));
               billDetail.setQuantity(resultSet.getInt("quantity"));
               billDetail.setPrice(resultSet.getFloat("price"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection,callSt);
        }
        return billDetail;
    }
}
