package ra.userBussiness;

import ra.connection.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Scanner;

public class UserBussiness {
    /**
     * Phương thưcs kết nối CSDL hiển thị danh sách phiếu nhập theo trạng thái
     * */
    public static void listOfEntriesByStatusOfUser(Scanner scanner) {
        boolean billType = true;
        Connection connection = null;
        CallableStatement callSt = null;
        System.out.println("Nhập vào trạng thái của phiếu :");
        System.out.println(" 0: Tạo - 1: Huỷ - 2: Duyệt");
        int billStatus = Integer.parseInt(scanner.nextLine());
        try {
            connection = ConnectionDB.openConnection();
            callSt = connection.prepareCall("{call listOfEntriesByStatusOfUser(?,?)}");
            // set tham so dau vao
            callSt.setBoolean(1,billType);
            callSt.setInt(2,billStatus);
            // goi procedure
            ResultSet rs = callSt.executeQuery();
            System.out.printf("%-20s %-20s %-20s %-20s\n",
                    "Mã phiếu nhập ","Mã code","Mã người tạo(user)","Ngày tạo");
            while (rs.next()) {
                int billId = rs.getInt("b.bill_id");
                String billCode = rs.getString("b.bill_code");
                String empId = rs.getString("b.emp_id_created");
                Date createds = rs.getDate("b.createds");
                System.out.printf("%-20s %-20s %-20s %-20s\n",
                        billId,billCode,empId,createds);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection,callSt);
        }
    }
}
