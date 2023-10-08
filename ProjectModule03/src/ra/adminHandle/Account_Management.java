package ra.adminHandle;

import ra.adminBussiness.AccountsBussiness;
import ra.adminBussiness.EmployeeBussiness;
import ra.adminRun.Admin_WareHouse_Management;
import ra.color.Color;
import ra.entity.Account;
import ra.entity.Employee;

import java.util.List;
import java.util.Scanner;

public class Account_Management {
    public static void main(String[] args) {
        accountMenu();
    }
    public static void accountMenu() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("******************ACCOUNT MANAGEMENT****************");
            System.out.println("1. Danh sách tài khoản");
            System.out.println("2. Tạo tài khoản mới");
            System.out.println("3. Cập nhật trạng thái tài khoản");
            System.out.println("4. Tìm kiếm tài khoản");
            System.out.println("5. Thoát");
            System.out.printf(" Chọn :\t");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                System.out.println();
                switch (choice) {
                    case 1:
                        displayAccount();
                        break;
                    case 2:
                        createAccounts(scanner);
                        break;
                    case 3:
                        updateEmployeeStatus(scanner);
                        break;
                    case 4:
                        searchAccountByKeyWord(scanner);
                        break;
                    case 5:
                        System.out.println(Color.YELLOW+"Quay lại trang Admin."+Color.RESET);
                        Admin_WareHouse_Management.adminMenu();
                    default:
                        System.err.println("Vui lòng chọn từ 1 đến 5.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (true);
    }
    /**
     * Phương thức hiển thị thông tin tài khoản
     * */
    public static void displayAccount() {
        List<Account> accountList = AccountsBussiness.displayAccountList();
        System.out.printf(" %-20s %-20s %-20s %-20s %-20s %-20s\n","Mã tài khoản","Tên tài khoản","Mật khẩu",
                "Quyền tài khoản","Mã nhân viên","Trạng thái");
        accountList.stream().forEach(account -> account.displayAccount());
    }
    /**
     * Phương thức thêm mới tài khoản
     * */
    public static void createAccounts(Scanner scanner) {
        // nhập dữ liệu cho 1 tài khoản thêm mới
        Account accountNew = new Account();
        accountNew.inputAccount(scanner);
        // gọi phương thức createEmployee ở EmployeeBussiness để thực hiên thêm dữ liệu vào DB
        boolean result = AccountsBussiness.createAccounts(accountNew);
        if (result) {
            System.out.println("Thêm mới thành công.");
        } else {
            System.err.println("Có lỗi xảy ra trong quá trình thực hiện. Vui lòng kiểm tra lại.");
        }
    }
    /**
     * Phương thức cập nhật trạng thái tài khoản
     * */
    public static void updateEmployeeStatus(Scanner scanner) {
        System.out.println("Nhập vào mã tài khoản cần cập nhật :");
        int updateAccId = Integer.parseInt(scanner.nextLine());
        Account account = AccountsBussiness.getAccountsById(updateAccId);
        if (account != null) {
            // mã tài khoản tồn tại trong CSDL
            System.out.println("Nhập vào trạng thái cần cập nhật :");
            System.out.println(" true : Active - false : Block");
            boolean newStatus = Account.validateAcc_status(scanner);
            //  tiến hành cập nhật
            boolean result = AccountsBussiness.updateStatusAccounts(updateAccId,newStatus);
            if (result) {
                System.out.println("Cập nhật trạng thái tài khoản có mã là "+ updateAccId + " thành công.");
            } else {
                System.err.println("Có lỗi xảy ra trong quá trình thực hiện. Vui lòng kiểm tra lại.");
            }
        } else {
            System.err.println("Mã tài khoản không tồn tại. Vui lòng kiểm tra lại.");
        }
    }
    /**
     * Phương thức tìm kiếm tài khoản theo username hoặc mã nhân viên
     * */
    public static void searchAccountByKeyWord(Scanner scanner) {
        System.out.println("Nhập vào username hoặc mã nhân viên cần tìm kiếm :");
        String keyword  = scanner.nextLine().toLowerCase();
        List<Account> accountList = AccountsBussiness.searchAccountsByKeyWord(keyword);
        if (!accountList.isEmpty()) {
            System.out.printf(" %-20s %-20s %-20s %-20s %-20s %-20s\n","Mã tài khoản","Tên tài khoản","Mật khẩu",
                    "Quyền tài khoản","Mã nhân viên","Trạng thái");
            accountList.stream().forEach(account -> account.displayAccount());
        } else {
            System.err.println("Không tìm thấy tài khoản cần tìm.");
        }
    }
}
