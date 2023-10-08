package ra.entity;

import java.util.Scanner;

public class Account {
    // mã tài khaonr
    private int acc_id;
    // tên người dùng
    private String user_name;
    // mật khẩu
    private String password;
    // Quyền tài khoản (0-admin 1-user)
    private boolean permission;
    // mã nhân viên
    private String emp_id;
    // Trạng thái (1- active 0-Block)
    private boolean acc_status;

    public Account() {
    }

    public Account(int acc_id, String user_name, String password, boolean permission,
                   String emp_id, boolean acc_status) {
        this.acc_id = acc_id;
        this.user_name = user_name;
        this.password = password;
        this.permission = permission;
        this.emp_id = emp_id;
        this.acc_status = acc_status;
    }
/**
 * Các phương thức getter & setter của account
 * */
    public int getAcc_id() {
        return acc_id;
    }

    public void setAcc_id(int acc_id) {
        this.acc_id = acc_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isPermission() {
        return permission;
    }

    public void setPermission(boolean permission) {
        this.permission = permission;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public boolean isAcc_status() {
        return acc_status;
    }

    public void setAcc_status(boolean acc_status) {
        this.acc_status = acc_status;
    }
    /**
     * phương thức hiển thị thông tin tài khoản
     */
    public void displayAccount() {
        String permissionString = (this.permission) ? "Admin" : "User";
        String statusString = (this.acc_status) ? "Active" : "Blocked";
        System.out.printf(" %-20s %-20s %-20s %-20s %-20s %-20s\n", this.acc_id,
                this.user_name,this.password,permissionString,this.emp_id,statusString);
    }
    /**
     * phương thức nhập thông tin tài khoản
     */
    public void inputAccount(Scanner scanner) {
        this.user_name = validateUserName(scanner);
        this.password = validatePassword(scanner);
        this.permission = validatePermission(scanner);
        this.emp_id = validateEmpId(scanner);
        this.acc_status = validateAcc_status(scanner);
    }
    /**
     * các phương thức validate cho Account
     */
    public static int validateAcc_id(Scanner scanner) {
        System.out.println("Nhập vào mã tài khoản :");
        int acc_id = 0;
        String inputAccId;
        do {
            inputAccId = scanner.nextLine();
            if (inputAccId.isEmpty()) {
                System.err.println("Mã tài khoản không được để trống. Vui lòng nhập vào.");
            } else {
                try {
                    acc_id = Integer.parseInt(inputAccId);
                } catch (NumberFormatException e) {
                    System.err.println("Mã tài khoản là kiểu số. Vui lòng nhập đúng định dạng.");
                }
            }
        } while (inputAccId.isEmpty());
        return acc_id;
    }
    public static String validateUserName(Scanner scanner) {
        System.out.println("Nhập vào tên tài khoản :");
        String userName;
        do {
            userName = scanner.nextLine();
            if (userName.isEmpty()) {
                System.err.println("Tên tài khoản không được để trống. Vui lòng nhập vào.");
            }
        } while (userName.isEmpty());
        return userName;
    }
    public static String validatePassword(Scanner scanner) {
        System.out.println("Nhập vào mật khẩu :");
        String password;
        do {
            password = scanner.nextLine();
            if (password.isEmpty()) {
                System.err.println("Mật khẩu không được để trống. Vui lòng nhập vào.");
            }
        } while (password.isEmpty());
        return password;
    }
    public static boolean validatePermission(Scanner scanner) {
        System.out.println("Nhập vào quyền tài khoản :");
        System.out.println(" true : admin - false : user");
        while (true) {
            String inputPermission = scanner.nextLine().toLowerCase().trim();
            if (inputPermission.isEmpty() || (!inputPermission.equals("true") && !inputPermission.equals("false"))) {
                System.err.println("Quyền tài khoản không hợp lệ. Vui lòng nhập true hoặc false.");
            } else {
                return Boolean.parseBoolean(inputPermission);
            }
        }
    }
    public static String validateEmpId(Scanner scanner) {
        boolean empAccRunning = true;
        System.out.println("Nhập vào mã nhân viên :");
        System.out.println("Example : EM001 ✓");
        String empId;
        do {
            empId = scanner.nextLine().trim();
            if (!empId.isEmpty()) {
                if (empId.startsWith("EM") && empId.length() == 5) {
                    empAccRunning = false;
                } else {
                    System.err.println("Mã nhân viên phải bắt đầu là 'EM' và phải có 5 ký tự.");
                }
            } else {
                System.err.println("Mã nhân viên không được để trống. Vui lòng nhập vào mã nhân viên.");
            }
        } while (empAccRunning);
        return empId;
    }
    public static boolean validateAcc_status(Scanner scanner) {
        System.out.println("Nhập vào trạng thái tài khoản :");
        System.out.println(" true : Active - false : Block");
        while (true) {
            String inputAccStatus = scanner.nextLine().toLowerCase().trim();
            if (inputAccStatus.isEmpty() || (!inputAccStatus.equals("true") && !inputAccStatus.equals("false"))) {
                System.err.println("Trạng thái tài khoản không hợp lệ. Vui lòng kiểm tra lại.");
            } else {
                return Boolean.parseBoolean(inputAccStatus);
            }
        }
    }
}
