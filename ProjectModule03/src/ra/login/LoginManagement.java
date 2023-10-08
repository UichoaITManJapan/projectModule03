package ra.login;

import ra.adminRun.Admin_WareHouse_Management;
import ra.loginBussiness.LoginBussiness;
import ra.user.User_WareHouse_Management;

import java.util.Scanner;

public class LoginManagement {
    public static void main(String[] args) {
        loginMenu();
    }

    public static void loginMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean loginRun = false;
        do {
            try {
                System.out.println("Nhập vào username :");
                String userName = scanner.nextLine().toLowerCase();
                System.out.println("Nhập vào password :");
                String password = scanner.nextLine().toLowerCase();
                if (LoginBussiness.checkLogin(userName, password)) {
                    int permission = LoginBussiness.getUserAndPasswordPermission(userName, password);
                    if (permission == 0) {
                        System.out.println("Đăng nhập thành công.");
                        // đăng nhập là admin
                        System.out.println();
                        System.out.println("\t\tChào mừng bạn đến với trang Admin");
                        System.out.println();
                        Admin_WareHouse_Management.adminMenu();
                    } else {
                        System.out.println("Đăng nhập thành công.");
                        System.out.println();
                        // đăng nhập là userName
                        System.out.println("\t\tChào mừng bạn đến với trang User");
                        System.out.println();
                        User_WareHouse_Management.userMenu();
                    }
                    // đánh dấu là đã đăng nhập thành công
                    loginRun = true;
                } else {
                    System.out.println("Đăng nhập thất bại. Vui lòng thử lại.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (!loginRun);
    }
}
