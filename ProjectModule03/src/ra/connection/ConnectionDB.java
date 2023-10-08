package ra.connection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static ra.dataSource.DataSource_Management.*;

public class ConnectionDB {
    /**
     * Các bước để tạo kết nối với database
     * B1: Download mysql jdbc jar và giải nén
     * B2: add gói jar vào project
     * B3: Cung cấp các thông tin driver, url, username, password
     * B4: set Driver cho DriverManager để làm việc với CSDL mysql
     * B5: Tạo đối tượng connection để tạo phiên làm việc với mysql
     * */
    /**
     * tạo phương thức mở connection đến DB và trả về đối tượng connection để làm việc
     * */
    public static Connection openConnection() {
        // Khai báo đối tượng connection
        Connection connection = null;
        // Set driver cho DriverManagement
        try {
            Class.forName(DRIVER);
            // khởi tạo đối tượng connection từ Driver Management
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
//    // test kết nối
//    public static void main(String[] args) {
//        Connection connection = ConnectionDB.openConnection();
//        if (connection != null) {
//            System.out.println("Thanh cong");
//        } else {
//            System.out.println("That Bai");
//        }
//    }
    /**
     * tạo phương thức đóng kết nối connection
     * */
    public static void closeConnection(Connection connection, CallableStatement callSt) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (callSt != null) {
            try {
                callSt.close();
            } catch (SQLException e) {
               e.printStackTrace();
            }
        }
    }
}
