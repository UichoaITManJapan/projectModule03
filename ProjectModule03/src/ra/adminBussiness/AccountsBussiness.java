package ra.adminBussiness;

import ra.connection.ConnectionDB;
import ra.entity.Account;
import ra.entity.Employee;
import ra.entity.Product;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AccountsBussiness {
    /**
     * Phương thức kết nối cơ sở dữ liệu dựa vào mã tài khoản lấy thông tin tài khoản
     */
    public static Account getAccountsById(int accId) {
        Connection connection = null;
        CallableStatement callSt = null;
        Account account = null;
        try {
            connection = ConnectionDB.openConnection();
            callSt = connection.prepareCall("{call getAccoutsById(?)}");
            // set tham số đầu vào
            callSt.setInt(1, accId);
            // thực hiện gọi procedure
            ResultSet resultSet = callSt.executeQuery();
            // lấy dữ liệu resultSet đẩy vào đối tượng product trả về
            while (resultSet.next()) {
                account = new Account();
                account.setAcc_id(resultSet.getInt("acc_id"));
                account.setUser_name(resultSet.getString("user_name"));
                account.setPassword(resultSet.getString("passwords"));
                account.setPermission(resultSet.getBoolean("permission"));
                account.setEmp_id(resultSet.getString("emp_id"));
                account.setAcc_status(resultSet.getBoolean("acc_status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callSt);
        }
        return account;
    }
    /**
     * Phương thức kết nối cơ sở dữ liệu lấy thông tin tài khoản
     */
    public static List<Account> displayAccountList() {
        Connection connection = null;
        CallableStatement callSt = null;
        List<Account> accountList = null;
        try {
            connection = ConnectionDB.openConnection();
            callSt = connection.prepareCall("{call getAllAccount()}");
            // set gía trị tham số đầu vào
            // thực hiện gọi procedure
            ResultSet resultSet = callSt.executeQuery();
            accountList = new ArrayList<>();
            // lấy dưc liệu resultSEt trả về
            while (resultSet.next()) {
                Account account = new Account();
                account.setAcc_id(resultSet.getInt("acc_id"));
                account.setUser_name(resultSet.getString("user_name"));
                account.setPassword(resultSet.getString("passwords"));
                account.setPermission(resultSet.getBoolean("permission"));
                account.setEmp_id(resultSet.getString("emp_id"));
                account.setAcc_status(resultSet.getBoolean("acc_status"));
                accountList.add(account);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection,callSt);
        }
        return accountList;
    }
    /**
     * Phương thức kết nối cơ sở dữ liệu để thêm mới tài khoản
     * */
    public static boolean createAccounts(Account account) {
        Connection connection = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            connection = ConnectionDB.openConnection();
            callSt = connection.prepareCall("{call createAccounts(?,?,?,?,?)}");
            // set giá trị tham số đâù vào
            callSt.setString(1,account.getUser_name());
            callSt.setString(2,account.getPassword());
            callSt.setBoolean(3,account.isPermission());
            callSt.setString(4,account.getEmp_id());
            callSt.setBoolean(5,account.isAcc_status());
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
     * Phương thức kết nối CSDL cập nhật trạng thái tài khoản
     * */
    public static boolean updateStatusAccounts(int accId,boolean newStatus) {
        Connection connection = null;
        CallableStatement callST = null;
        boolean result = false;
        try {
            connection = ConnectionDB.openConnection();
            callST = connection.prepareCall("{call updateAccountStatus(?,?)}");
            // set tham sô đàu vào
            callST.setInt(1,accId);
            callST.setBoolean(2,newStatus);
            // thực hiện gọi procedure
            callST.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection,callST);
        }
        return result;
    }
    /**
     * Phương thức kết nối CSDL tìm kiếm tài khoản theo userName hoặc tên nhân viên
     * */
    public static List<Account> searchAccountsByKeyWord(String keyword) {
        Connection connection = null;
        CallableStatement callSt = null;
        List<Account> accountList = null;
        try {
            connection = ConnectionDB.openConnection();
            callSt = connection.prepareCall("{call searchAccountsByKeyWord(?)}");
            // set gía trị tham số đầu vào
            callSt.setString(1,keyword);
            // thực hiện gọi procedure
            ResultSet resultSet = callSt.executeQuery();
            accountList = new ArrayList<>();
            // lấy dưc liệu resultSEt trả về
            while (resultSet.next()) {
                Account account = new Account();
                account.setAcc_id(resultSet.getInt("acc_id"));
                account.setUser_name(resultSet.getString("user_name"));
                account.setPassword(resultSet.getString("passwords"));
                account.setPermission(resultSet.getBoolean("permission"));
                account.setEmp_id(resultSet.getString("emp_id"));
                account.setAcc_status(resultSet.getBoolean("acc_status"));
                accountList.add(account);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection,callSt);
        }
        return accountList;
    }
}