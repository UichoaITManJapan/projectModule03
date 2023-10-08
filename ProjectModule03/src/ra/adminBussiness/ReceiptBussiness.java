package ra.adminBussiness;

import ra.connection.ConnectionDB;
import ra.entity.Account;
import ra.entity.Bill;
import ra.entity.Bill_Detail;
import ra.entity.Product;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReceiptBussiness {
    /**
     * Phương thức kết nối CSDL để hiển thị danh sách phiêú
     * nhập
     */
    public static List<Bill> getAllREceipt(boolean billType) {
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
     * Phương thức kết nối CSDL tạo mới phiếu nhập
     */
    public static boolean createBill(Bill bill,boolean billType) {
        Connection connection = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            connection = ConnectionDB.openConnection();
            callSt = connection.prepareCall("{call createBill(?,?,?,?,?,?,?)}");
            // set giá trị tham số đâù vào
            callSt.setString(1,bill.getBill_code());
            callSt.setBoolean(2,billType);
            callSt.setString(3,bill.getEmp_id_created());
            callSt.setDate(4,new java.sql.Date(bill.getCreated().getTime()));
            callSt.setString(5,bill.getEmp_id_auth());
            callSt.setDate(6,new java.sql.Date(bill.getAuth_date().getTime()));
            callSt.setInt(7,bill.getBill_status());
            // gọi procedure
            callSt.executeUpdate();
            result = true;
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection,callSt);
        }
        return result;
    }
    /**
     * Phương thức kết nối CSDL cập nhật phiếu nhập
     * */
    public static boolean updateBillByStatus(Bill bill,boolean billType) {
        Connection connection = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            connection = ConnectionDB.openConnection();
            callSt = connection.prepareCall("{call updateReceiptByStatus(?,?,?,?,?,?,?,?)}");
            // set tham số đầu vào
            callSt.setInt(1,bill.getBill_id());
            callSt.setString(2,bill.getBill_code());
            callSt.setBoolean(3,billType);
            callSt.setString(4,bill.getEmp_id_created());
            callSt.setDate(5,new java.sql.Date(bill.getCreated().getTime()));
            callSt.setString(6,bill.getEmp_id_auth());
            callSt.setDate(7,new java.sql.Date(bill.getAuth_date().getTime()));
            callSt.setInt(8,bill.getBill_status());
            // goi procedure
            callSt.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection,callSt);
        }
       return result;
    }
    /**
     * Phương thức kết nối CSDL cập nhật phiếu nhập theo mã hoặc mã code
     * */
    public static Bill getBillByIdOrCode(int billId,String billCode,boolean billType) {
        Connection connection = null;
        CallableStatement callSt = null;
        Bill bill = null;
        try {
            connection = ConnectionDB.openConnection();
            callSt = connection.prepareCall("{call updateReceiptByIdOrCode(?,?,?)}");
            // set tham số đầu vào
            callSt.setInt(1,billId);
            callSt.setString(2,billCode);
            callSt.setBoolean(3,billType);
            // thực hiện gọi procedure
            ResultSet resultSet = callSt.executeQuery();
            // lấy dữ liệu resultSet đẩy vào đối tượng product trả về
            while (resultSet.next()) {
                bill = new Bill();
                bill.setBill_id(resultSet.getInt("bill_id"));
                bill.setBill_code(resultSet.getString("bill_code"));
                bill.setBill_type(resultSet.getBoolean("bill_type"));
                bill.setEmp_id_created(resultSet.getString("emp_id_created"));
                bill.setCreated(resultSet.getDate("createds"));
                bill.setEmp_id_auth(resultSet.getString("emp_id_auth"));
                bill.setAuth_date(resultSet.getDate("auth_date"));
                bill.setBill_status(resultSet.getInt("bill_status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection,callSt);
        }
        return bill;
    }

    /**
     * Phương thức kết nối CSDL để duyệt phiếu
     * */
    public static boolean updateProductQuantityAndStatus(boolean billType, int billId) {
        Connection connection = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            connection = ConnectionDB.openConnection();
            callSt = connection.prepareCall("{call updateProductQuantity(?,?)}");
            // set tham so dau vao
            callSt.setBoolean(1,billType);
            callSt.setInt(2,billId);
            // goij procedure
           result = callSt.execute();
//            // kiểm tra trạng thái phiếu sau khi cập nhật
//            int billStatus = callSt.getInt("bill_status");
//            if (billStatus == 2) {
//                result = true;
//            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection,callSt);
        }
        return result;
    }
    public static Bill getBillById(int billId) {
        Connection connection = null;
        CallableStatement callSt = null;
        Bill bill = null;
        try {
            connection = ConnectionDB.openConnection();
            callSt = connection.prepareCall("{call getBilltById(?)}");
            // set tham số đầu vào
            callSt.setInt(1,billId);
            // thực hiện gọi procedure
            ResultSet resultSet = callSt.executeQuery();
            // lấy dữ liệu resultSet đẩy vào đối tượng product trả về
            while (resultSet.next()) {
                bill = new Bill();
                bill.setBill_id(resultSet.getInt("bill_id"));
                bill.setBill_code(resultSet.getString("bill_code"));
                bill.setBill_type(resultSet.getBoolean("bill_type"));
                bill.setEmp_id_created(resultSet.getString("emp_id_created"));
                bill.setCreated(resultSet.getDate("createds"));
                bill.setEmp_id_auth(resultSet.getString("emp_id_auth"));
                bill.setAuth_date(resultSet.getDate("auth_date"));
                bill.setBill_status(resultSet.getInt("bill_status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection,callSt);
        }
        return bill;
    }
    /**
     * phương thức kết nối CSDL tìm kiếm phiếu nhập bằng mã và mã code
     * */
    public static List<Bill> searchReceiptByIdAndCode(int billId,String billCode,boolean billType) {
        Connection connection = null;
        CallableStatement callSt = null;
        List<Bill> billList = null;
        try {
            connection = ConnectionDB.openConnection();
            callSt = connection.prepareCall("{call searchBillByIdAndCode(?,?,?)}");
            // set tham số đầu vào
            callSt.setInt(1,billId);
            callSt.setString(2,billCode);
            callSt.setBoolean(3,billType);
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
}
