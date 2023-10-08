package ra.loginBussiness;

import ra.connection.ConnectionDB;

import java.sql.*;

public class LoginBussiness {
    /**
     * kiểm tra username và password có tồn rại trong bảng account hay không
     * */
    public static boolean checkLogin(String userName, String password) throws SQLException {
        Connection connection = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            connection = ConnectionDB.openConnection();
            callSt = connection.prepareCall("{call get_user_password(?,?,?)}");
            // set dữ liệu cho các tham số trả ra
            callSt.setString(1,userName);
            callSt.setString(2,password);
            // đăng ký tham số đầu ra
            callSt.registerOutParameter(3,Types.BIT);
            // thực hiện gọi procedure
            callSt.execute();
            // lấy kết quả tham số đầu ra
            result = callSt.getBoolean(3);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection,callSt);
        }
        return result;
    }
    /**
     * kiểm tả trạng thái của permission
     * */
    public static int getUserAndPasswordPermission(String username, String password) throws SQLException {
        Connection connection = null;
        CallableStatement callSt = null;
        // giá trị mặc định nếu không tìm thấy
        int permission = 1;
        try {
            connection = ConnectionDB.openConnection();
            callSt = connection.prepareCall("{call check_permission(?,?)}");
            // thiết lập tham số cho dữ liệu đầu vào
            callSt.setString(1,username);
            // đăng ký tham số đầu ra
            callSt.registerOutParameter(2,Types.BIT);
            // thực hiện gọi procedure
            callSt.execute();
            // lấy kết quả từ tham số đầu ra
            permission = callSt.getInt(2);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection,callSt);
        }
        return permission;
    }
}
