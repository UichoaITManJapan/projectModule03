package ra.user;

import ra.color.Color;
import ra.userBussiness.UserBussiness;

import java.util.Scanner;

public class User_WareHouse_Management {
    public static void main(String[] args) {
        userMenu();
    }
    public static void userMenu() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("******************WAREHOUSE MANAGEMENT****************");
            System.out.println("1. Danh sách phiếu nhập theo trạng thái");
            System.out.println("2. Tạo phiếu nhập");
            System.out.println("3. Cập nhật phiếu nhập");
            System.out.println("4. Tìm kiếm phiếu nhập");
            System.out.println("5. Danh sách phiếu xuất theo trạng thái");
            System.out.println("6. Tạo phiếu xuất");
            System.out.println("7. Cập nhật phiếu xuất");
            System.out.println("8. Tìm kiếm phiếu xuất");
            System.out.println("9. Thoát");
            System.out.println();
            System.out.printf("Chọn :\t");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                System.out.println();
                switch (choice) {
                    case 1:
                        UserBussiness.listOfEntriesByStatusOfUser(scanner);
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        break;
                    case 9:
                        System.out.println(Color.VANGDAM+"Kết thúc chương trình."+Color.RESET);
                        System.exit(0);
                    default:
                        System.err.println("VUi lòng chọn từ 1 đến 9.");
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (true);
    }
}
