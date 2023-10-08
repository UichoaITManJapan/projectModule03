package ra.adminHandle;

import ra.adminBussiness.ReceiptBussiness;
import ra.adminBussiness.ReportBussiness;
import ra.adminRun.Admin_WareHouse_Management;
import ra.color.Color;
import ra.connection.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.Scanner;

public class Report_Management {
    public static void main(String[] args) {
        reportMenu();
    }
    public static void reportMenu() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("******************REPORT MANAGEMENT****************");
            System.out.println("1. Thống kê chi phí theo ngày, tháng, năm");
            System.out.println("2. Thống kê chi phí theo khoảng thời gian");
            System.out.println("3. Thống kê doanh thu theo ngày, tháng, năm");
            System.out.println("4. Thống kê doanh thu theo khoảng thời gian");
            System.out.println("5. Thống kê số nhân viên theo từng trạng thái");
            System.out.println("6. Thống kê sản phẩm nhập nhiều nhất trong khoảng thời gian");
            System.out.println("7. Thống kê sản phẩm nhập ít nhất trong khoảng thời gian");
            System.out.println("8. Thống kê sản phẩm xuất nhiều nhất trong khoảng thời gian");
            System.out.println("9. Thống kê sản phẩm xuất ít nhất trong khoảng thời gian");
            System.out.println("10.Thoát");
            System.out.printf(" Chọn :\t");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                System.out.println();
                switch (choice) {
                    case 1:
                        ReportBussiness.staticCostToDayMonthYear();
                        break;
                    case 2:
                        ReportBussiness.staticCostDateToDate(scanner);
                        break;
                    case 3:
                        ReportBussiness.staticRevenueToDayMonthYear();
                        break;
                    case 4:
                        ReportBussiness.staticRevenueDateToDate(scanner);
                        break;
                    case 5:
                        getCntEmpByStatus();
                        break;
                    case 6:
                        staticQuantityReceiptMostDateToDate(scanner);
                        break;
                    case 7:
                        staticQuantityReceiptSmallestDateToDate(scanner);
                        break;
                    case 8:
                        staticQuantityBillMostDateToDate(scanner);
                        break;
                    case 9:
                        staticQuantityBillSmallestDateToDate(scanner);
                        break;
                    case 10:
                        System.out.println(Color.YELLOW+"Quay lại trang Admin."+Color.RESET);
                        Admin_WareHouse_Management.adminMenu();
                    default:
                        System.err.println("Vui lòng chọn từ 1 đến 10.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (true);
    }
    /**
     *  Phương thức thống kê số nhân viên theo trạng thái
     * */
    public static void getCntEmpByStatus() {
        System.out.println("Thống kê số lượng nhân viên theo trạng thái");
        System.out.printf("%-20s%-20s\n", "Trạng thái", "Số lượng nhân viên");
        ReportBussiness.employeeStatisticsByStatus();
    }
    /**
     * Phương thức kết nối CSDL thống k số phiếu nhâp nhiều nhất trong khoang thời gian
     * */
    public static void staticQuantityReceiptMostDateToDate(Scanner scanner) {
        boolean billType = true;
        Connection connection = null;
        CallableStatement callSt = null;
        System.out.println("Nhập ngày bắt đầu (yyyy-MM-dd) :");
        String startDateStr = scanner.nextLine();
        System.out.println("Nhập ngày kết thúc (yyyy-MM-dd) :");
        String endDateStr = scanner.nextLine();
        try {
            connection = ConnectionDB.openConnection();
            callSt = connection.prepareCall("{call staticQuantityMOstDateToDate(?,?,?)}");
            // set tham so dau vao
            callSt.setDate(1,java.sql.Date.valueOf(startDateStr));
            callSt.setDate(2,java.sql.Date.valueOf(endDateStr));
            callSt.setBoolean(3,billType);
            ResultSet rs = callSt.executeQuery();
            System.out.printf("%-20s %-20s %-20s %-20s %-20s\n",
                    "Ngày bắt đầu","Ngày kết thúc","Mã sản phẩm","Tên sản phẩm","Số lượng");
            while (rs.next()) {
                String productId = rs.getString("bd.product_id");
                String productName = rs.getString("p.product_name");
                int sumQuantity = rs.getInt("total_quantity");
                System.out.printf("%-20s %-20s %-20s %-20s %-20s\n",
                        startDateStr,endDateStr,productId,productName,sumQuantity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection,callSt);
        }
    }
    /**
     * Phương thức kết nối CSDL thống k số phiếu nhâp nhiều nhất trong khoang thời gian
     * */
    public static void staticQuantityReceiptSmallestDateToDate(Scanner scanner) {
        boolean billTypeCheck = true;
        Connection connection = null;
        CallableStatement callSt = null;
        System.out.println("Nhập ngày bắt đầu (yyyy-MM-dd) :");
        String startDateStr = scanner.nextLine();
        System.out.println("Nhập ngày kết thúc (yyyy-MM-dd) :");
        String endDateStr = scanner.nextLine();
        try {
            connection = ConnectionDB.openConnection();
            callSt = connection.prepareCall("{call staticQuantitySmallestDateToDate(?,?,?)}");
            // set tham so dau vao
            callSt.setDate(1,java.sql.Date.valueOf(startDateStr));
            callSt.setDate(2,java.sql.Date.valueOf(endDateStr));
            callSt.setBoolean(3,billTypeCheck);
            ResultSet rs = callSt.executeQuery();
            System.out.printf("%-20s %-20s %-20s %-20s %-20s\n",
                    "Ngày bắt đầu","Ngày kết thúc","Mã sản phẩm","Tên sản phẩm","Số lượng");
            while (rs.next()) {
                String productId = rs.getString("bd.product_id");
                String productName = rs.getString("p.product_name");
                int sumQuantity = rs.getInt("total_quantity");
                System.out.printf("%-20s %-20s %-20s %-20s %-20s\n",
                        startDateStr,endDateStr,productId,productName,sumQuantity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection,callSt);
        }
    }
    /**
     * Phương thức kết nối CSDL thống k số phiếu nhâp nhiều nhất trong khoang thời gian
     * */
    public static void staticQuantityBillMostDateToDate(Scanner scanner) {
        boolean billType = false;
        Connection connection = null;
        CallableStatement callSt = null;
        System.out.println("Nhập ngày bắt đầu (yyyy-MM-dd) :");
        String startDateStr = scanner.nextLine();
        System.out.println("Nhập ngày kết thúc (yyyy-MM-dd) :");
        String endDateStr = scanner.nextLine();
        try {
            connection = ConnectionDB.openConnection();
            callSt = connection.prepareCall("{call staticQuantityMOstDateToDate(?,?,?)}");
            // set tham so dau vao
            callSt.setDate(1,java.sql.Date.valueOf(startDateStr));
            callSt.setDate(2,java.sql.Date.valueOf(endDateStr));
            callSt.setBoolean(3,billType);
            ResultSet rs = callSt.executeQuery();
            System.out.printf("%-20s %-20s %-20s %-20s %-20s\n",
                    "Ngày bắt đầu","Ngày kết thúc","Mã sản phẩm","Tên sản phẩm","Số lượng");
            while (rs.next()) {
                String productId = rs.getString("bd.product_id");
                String productName = rs.getString("p.product_name");
                int sumQuantity = rs.getInt("total_quantity");
                System.out.printf("%-20s %-20s %-20s %-20s %-20s\n",
                        startDateStr,endDateStr,productId,productName,sumQuantity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection,callSt);
        }
    }
    /**
     * Phương thức kết nối CSDL thống k số phiếu nhâp nhiều nhất trong khoang thời gian
     * */
    public static void staticQuantityBillSmallestDateToDate(Scanner scanner) {
        boolean billTypeCheck = false;
        Connection connection = null;
        CallableStatement callSt = null;
        System.out.println("Nhập ngày bắt đầu (yyyy-MM-dd) :");
        String startDateStr = scanner.nextLine();
        System.out.println("Nhập ngày kết thúc (yyyy-MM-dd) :");
        String endDateStr = scanner.nextLine();
        try {
            connection = ConnectionDB.openConnection();
            callSt = connection.prepareCall("{call staticQuantitySmallestDateToDate(?,?,?)}");
            // set tham so dau vao
            callSt.setDate(1,java.sql.Date.valueOf(startDateStr));
            callSt.setDate(2,java.sql.Date.valueOf(endDateStr));
            callSt.setBoolean(3,billTypeCheck);
            ResultSet rs = callSt.executeQuery();
            System.out.printf("%-20s %-20s %-20s %-20s %-20s\n",
                    "Ngày bắt đầu","Ngày kết thúc","Mã sản phẩm","Tên sản phẩm","Số lượng");
            while (rs.next()) {
                String productId = rs.getString("bd.product_id");
                String productName = rs.getString("p.product_name");
                int sumQuantity = rs.getInt("total_quantity");
                System.out.printf("%-20s %-20s %-20s %-20s %-20s\n",
                        startDateStr,endDateStr,productId,productName,sumQuantity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection,callSt);
        }
    }
}
