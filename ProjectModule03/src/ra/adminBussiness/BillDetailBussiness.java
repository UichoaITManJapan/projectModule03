package ra.adminBussiness;

import ra.connection.ConnectionDB;
import ra.entity.Bill_Detail;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BillDetailBussiness {
    /**
     * Phương thức kết nối CSDL cập nhật chi tiết phiếu nhập
     * */
    public static boolean updateBillDetail(Bill_Detail billDetail) {
        Connection connection = null;
        CallableStatement callSta = null;
        boolean result = false;
        try {
            connection = ConnectionDB.openConnection();
            callSta = connection.prepareCall("{call updateBillDetail(?,?,?,?,?)}");
            // set dữ liệu cho các tham số đầu vào
            callSta.setInt(1,billDetail.getBill_detail_id());
            callSta.setInt(2,billDetail.getBill_id());
            callSta.setString(3,billDetail.getProduct_id());
            callSta.setInt(4,billDetail.getQuantity());
            callSta.setFloat(5,billDetail.getPrice());
            // gọi procedure
            callSta.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection,callSta);
        }
        return result;
    }

    /**
     * Phương thức kết nối cơ sở dữ liệu lấy thông tin tài khoản
     */
    public static List<Bill_Detail> displayBillDetailList(boolean billType) {
        Connection connection = null;
        CallableStatement callSt = null;
        List<Bill_Detail> billDetailList = null;
        try {
            connection = ConnectionDB.openConnection();
            callSt = connection.prepareCall("{call getAllBillDetail(?)}");
            // set gía trị tham số đầu vào
            callSt.setBoolean(1,billType);
            // thực hiện gọi procedure
            ResultSet resultSet = callSt.executeQuery();
            billDetailList = new ArrayList<>();
            // lấy dưc liệu resultSEt trả về
            while (resultSet.next()) {
                Bill_Detail billDetail = new Bill_Detail();
                billDetail.setBill_detail_id(resultSet.getInt("bill_detail_id"));
                billDetail.setBill_id(resultSet.getInt("bill_id"));
                billDetail.setProduct_id(resultSet.getString("product_id"));
                billDetail.setQuantity(resultSet.getInt("quantity"));
                billDetail.setPrice(resultSet.getFloat("price"));
                billDetailList.add(billDetail);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection,callSt);
        }
        return billDetailList;
    }
}
