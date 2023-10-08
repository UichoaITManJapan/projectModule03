package ra.adminRun;

import ra.adminHandle.*;
import ra.color.Color;

import java.util.Scanner;

public class Admin_WareHouse_Management {
    public static void main(String[] args) {
        adminMenu();
    }
    public static void adminMenu() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println();
            System.out.println("******************WAREHOUSE MANAGEMENT****************");
            System.out.println("1. Quản lý sản phẩm");
            System.out.println("2. Quản lý nhân viên");
            System.out.println("3. Quản lý tài khoản");
            System.out.println("4. Quản lý phiếu nhập");
            System.out.println("5. Quản lý phiếu xuất");
            System.out.println("6. Quản lý báo cáo");
            System.out.println("7. Thoát");
            System.out.println();
            System.out.printf(" Chọn :\t");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                System.out.println();
                switch (choice) {
                    case 1:
                        Product_Management.productMenu();
                        break;
                    case 2:
                        Employee_Management.employeeMenu();
                        break;
                    case 3:
                        Account_Management.accountMenu();
                        break;
                    case 4:
                        Receipt_Management.receiptMenu();
                        break;
                    case 5:
                        Bill_Management.billMenu();
                        break;
                    case 6:
                        Report_Management.reportMenu();
                        break;
                    case 7:
                        System.out.println(Color.VANGDAM+"Kết thúc chương trình."+Color.RESET);
                        System.out.println();
                        System.exit(0);
                    default:
                        System.err.println("Vui lòng chọn từ 1 đến 7.");
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (true);
    }
}
