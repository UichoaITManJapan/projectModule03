package ra.adminBussiness;

import ra.connection.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.Scanner;

public class ReportBussiness {
    /**
     *  phương thức kết nối cơ sở dữ liệu thống kế chi phí theo ngày tháng năm
     * */
    public static void staticCostToDayMonthYear() {
        Connection connection = null;
        CallableStatement callSt = null;
        try {
            connection = ConnectionDB.openConnection();
            // gọi procedure thống kê theo ngày
            callSt = connection.prepareCall("{call calculateCostByDate()}");
            boolean hasResult = callSt.execute();
            System.out.println("Thống kê chi phí theo ngày");
            System.out.printf("%-10s %-10s\n","Ngày","Tổng chi phí(USD)");
            while (hasResult) {
                ResultSet resultSet = callSt.getResultSet();
                while (resultSet.next()) {
                    // Lấy giá trị ngày tháng năm từ cơ sở dữ liệu
                    java.sql.Date date = resultSet.getDate("day");

                    // Lấy ngày từ đối tượng java.sql.Date
                    int day = date.toLocalDate().getDayOfMonth();

                    // Lấy tổng chi phí
                    double sumCost = resultSet.getDouble("sumCost");

                    // In kết quả
                    System.out.printf("%-10s %-10s\n", day, sumCost);
                }
                hasResult = callSt.getMoreResults();
            }

            // gọi procedure thống kê theo tháng
            callSt = connection.prepareCall("{call calculateCostByMonth()}");
            hasResult = callSt.execute();
            System.out.println("Thống kê chi phí theo tháng");
            System.out.printf("%-10s %-10s\n","Tháng","Tổng chi phí(USD)");
            while (hasResult) {
                ResultSet resultSet = callSt.getResultSet();
                while (resultSet.next()) {
                    // Lấy giá trị tháng từ cơ sở dữ liệu
                    int month = resultSet.getInt("month");

                    // Lấy tổng chi phí
                    double sumCost = resultSet.getDouble("sumCost");

                    // In kết quả
                    System.out.printf("%-10s %-10s\n", month, sumCost);
                }
                hasResult = callSt.getMoreResults();
            }

            // gọi procedure thống kê theo năm
            callSt = connection.prepareCall("{call calculateCostByYear()}");
            hasResult = callSt.execute();
            System.out.println("Thống kê chi phí theo năm");
            System.out.printf("%-10s %-10s\n","Năm","Tổng chi phí(USD)");
            while (hasResult) {
                ResultSet resultSet = callSt.getResultSet();
                while (resultSet.next()) {
                    // Lấy giá trị năm từ cơ sở dữ liệu
                    int year = resultSet.getInt("year");

                    // Lấy tổng chi phí
                    double sumCost = resultSet.getDouble("sumCost");

                    // In kết quả
                    System.out.printf("%-10s %-10s\n", year, sumCost);
                }
                hasResult = callSt.getMoreResults();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callSt);
        }
    }
/**
 * Phương thức kết nối CSDL hiển thị kinh phí theo khoản thời gian
 * */
    public static void staticCostDateToDate(Scanner scanner) {
        Connection connection = null;
        CallableStatement callSt = null;
        System.out.println("Nhập ngày bắt đầu (yyyy-MM-dd) :");
        String startDateStr = scanner.nextLine();
        System.out.println("Nhập ngày kết thúc (yyyy-MM-dd) :");
        String endDateStr = scanner.nextLine();
        try {
            connection = ConnectionDB.openConnection();
            callSt = connection.prepareCall("{call staticCostByDatetoDate(?,?,?)}");
            // set tham so dau vao
            callSt.setDate(1,java.sql.Date.valueOf(startDateStr));
            callSt.setDate(2,java.sql.Date.valueOf(endDateStr));
            // dang ki tham so dau ra
            callSt.registerOutParameter(3, Types.DOUBLE);
            // goij procedure
            callSt.execute();
            // lay gia trij tham so dau ra
            double totalCost = callSt.getDouble(3);
            // in ket qua
            System.out.printf("%-20s %-20s %-20s\n","Ngày bắt đầu","Ngày kết thúc","Tổng chi phí");
            System.out.printf("%-20s %-20s %-20s\n",startDateStr,endDateStr,totalCost);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection,callSt);
        }
    }
    /***
     * Phương thức kết nối cơ sở dữ liệu thống kê doanh thu theo ngày tháng năm
     */
    public static void staticRevenueToDayMonthYear() {
        Connection connection = null;
        CallableStatement callSt = null;
        try {
            connection = ConnectionDB.openConnection();
            // gọi procedure thống kê theo ngày
            callSt = connection.prepareCall("{call calculateRevenueByDate()}");
            boolean hasResult = callSt.execute();
            System.out.println("Thống kê doanh thu theo ngày");
            System.out.printf("%-10s %-10s\n","Ngày","Tổng doanh thu(USD)");
            while (hasResult) {
                ResultSet resultSet = callSt.getResultSet();
                while (resultSet.next()) {
                    // Lấy giá trị ngày tháng năm từ cơ sở dữ liệu
                    java.sql.Date date = resultSet.getDate("day");

                    // Lấy ngày từ đối tượng java.sql.Date
                    int day = date.toLocalDate().getDayOfMonth();

                    // Lấy tổng chi phí
                    double sumCost = resultSet.getDouble("sumRevenue");

                    // In kết quả
                    System.out.printf("%-10s %-10s\n", day, sumCost);
                }
                hasResult = callSt.getMoreResults();
            }

            // gọi procedure thống kê theo tháng
            callSt = connection.prepareCall("{call calculateRevenueByMonth()}");
            hasResult = callSt.execute();
            System.out.println("Thống kê doanh thu theo tháng");
            System.out.printf("%-10s %-10s\n","Tháng","Tổng doanh thu(USD)");
            while (hasResult) {
                ResultSet resultSet = callSt.getResultSet();
                while (resultSet.next()) {
                    // Lấy giá trị tháng từ cơ sở dữ liệu
                    int month = resultSet.getInt("month");

                    // Lấy tổng chi phí
                    double sumCost = resultSet.getDouble("sumRevenue");

                    // In kết quả
                    System.out.printf("%-10s %-10s\n", month, sumCost);
                }
                hasResult = callSt.getMoreResults();
            }

            // gọi procedure thống kê theo năm
            callSt = connection.prepareCall("{call calculateRevenueByYear()}");
            hasResult = callSt.execute();
            System.out.println("Thống kê doanh thu theo năm");
            System.out.printf("%-10s %-10s\n","Năm","Tổng doanh thu(USD)");
            while (hasResult) {
                ResultSet resultSet = callSt.getResultSet();
                while (resultSet.next()) {
                    // Lấy giá trị năm từ cơ sở dữ liệu
                    int year = resultSet.getInt("year");

                    // Lấy tổng chi phí
                    double sumCost = resultSet.getDouble("sumRevenue");

                    // In kết quả
                    System.out.printf("%-10s %-10s\n", year, sumCost);
                }
                hasResult = callSt.getMoreResults();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callSt);
        }
    }
    /**
     * Phương thức kết nối CSDL hiển thị kinh phí theo khoản thời gian
     * */
    public static void staticRevenueDateToDate(Scanner scanner) {
        Connection connection = null;
        CallableStatement callSt = null;
        System.out.println("Nhập ngày bắt đầu (yyyy-MM-dd) :");
        String startDateStr = scanner.nextLine();
        System.out.println("Nhập ngày kết thúc (yyyy-MM-dd) :");
        String endDateStr = scanner.nextLine();
        try {
            connection = ConnectionDB.openConnection();
            callSt = connection.prepareCall("{call staticRevenueByDatetoDate(?,?,?)}");
            // set tham so dau vao
            callSt.setDate(1,java.sql.Date.valueOf(startDateStr));
            callSt.setDate(2,java.sql.Date.valueOf(endDateStr));
            // dang ki tham so dau ra
            callSt.registerOutParameter(3, Types.DOUBLE);
            // goij procedure
            callSt.execute();
            // lay gia trij tham so dau ra
            double totalRevenue = callSt.getDouble(3);
            // in ket qua
            System.out.printf("%-20s %-20s %-20s\n","Ngày bắt đầu","Ngày kết thúc","Tổng doanh thu(USD)");
            System.out.printf("%-20s %-20s %-20s\n",startDateStr,endDateStr,totalRevenue);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection,callSt);
        }
    }
    /**
     * Phương thức kết nối CSDL thống kê số nhân viên theo trạng thái
     * */
    public static int employeeStatisticsByStatus(){
        //1. Tạo đối tượng Connection
        //2. Tạo đối tượng CallableStatement
        //3. Gọi procedure
        //4. Xử lý dữ liệu và trả về listProduct
        int cntEmp = 0;
        int empStatus = 0;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call staticEmployeeByStatus()}");
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                empStatus = rs.getInt("emp_status");
                cntEmp = rs.getInt("employee_count");
                System.out.printf("%-20d%-20d\n",empStatus,cntEmp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return cntEmp;
    }
}
