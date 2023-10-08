package ra.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Employee {
    // mã nhân viên
    private String emp_id;
    // tên nhân viên
    private String emp_name;
    // ngày sinh
    private Date birth_of_date;
    // email
    private String email;
    // số điện thoại
    private String phone;
    // địa chỉ
    private String address;
    // Trạng thái (0- Hoạt động | 1- Nghỉ chế độ | 2- Nghỉ việc)
    private int emp_status;

    public Employee() {
    }

    public Employee(String emp_id, String emp_name, Date birth_of_date, String email, String phone,
                    String address, int emp_status) {
        this.emp_id = emp_id;
        this.emp_name = emp_name;
        this.birth_of_date = birth_of_date;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.emp_status = emp_status;
    }
/**
 *  các phương thức getter & setter của employee
 * */
    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public Date getBirth_of_date() {
        return birth_of_date;
    }

    public void setBirth_of_date(Date birth_of_date) {
        this.birth_of_date = birth_of_date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getEmp_status() {
        return emp_status;
    }

    public void setEmp_status(int emp_status) {
        this.emp_status = emp_status;
    }
    /**
     * phương thức hiển thị thông tin nhân viên
     * */
    public void displayEmployee() {
        String statusString = (this.emp_status == 0) ? "Hoạt động" : (this.emp_status == 1) ? "Nghỉ chế độ" : "Nghỉ việc";
        System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %-20s\n",
                this.emp_id,this.emp_name,this.birth_of_date,this.email,this.phone,
                this.address,statusString);
    }
    /**
     * phương thức nhập thông tin nhân viên
     * */
    public void inputEmployee(Scanner scanner) {
        this.emp_id = validateEmp_Id(scanner);
        this.emp_name = validateEmp_Name(scanner);
        try {
            this.birth_of_date = validateBrith_of_date(scanner);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.email = validateEmail(scanner);
        this.phone = validatePhone(scanner);
        this.address = validateAddress(scanner);
        this.emp_status = validateEmp_Status(scanner);
    }
    /**
     * các phương thức validate cho Employee
     * */
    /**
     * @
     * */
    public static String validateEmp_Id(Scanner scanner) {
        boolean empRunning = true;
        System.out.println("Nhập vào mã nhân viên :");
        String empId;
        do {
            empId = scanner.nextLine().trim();
            if (!empId.isEmpty()) {
                if (empId.startsWith("EM") && empId.length() == 5) {
                    empRunning = false;
                } else {
                    System.err.println("Mã nhân viên phải bắt đầu là 'EM' và phải có 5 ký tự.");
                }
            } else {
                System.err.println("Mã nhân viên không được để trống. Vui lòng nhập vào mã nhân viên.");
            }
        } while (empRunning);
        return empId;
    }
    public static String validateEmp_Name(Scanner scanner) {
        System.out.println("Nhập vào tên nhân viên '");
        String empName;
        do {
            empName = scanner.nextLine();
            if (empName.isEmpty()) {
                System.err.println("Tên nhân viên không được để trống. Vui lòng nhập vào tên nhân viên.");
            }
        } while (empName.isEmpty());
        return empName;
    }
    public static java.sql.Date validateBrith_of_date(Scanner scanner) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Nhập vào ngày sinh nhân viên (dd/MM/yyyy):");
        while (true) {
            String empBirth = scanner.nextLine().trim();
            if (empBirth.isEmpty()) {
                System.err.println("Ngày sinh nhân viên không được để trống. Vui lòng nhập vào.");
                continue;
            }
            try {
                Date birthDate = sdf.parse(empBirth);
                // Chuyển đổi từ java.util.Date sang java.sql.Date
                java.sql.Date birthSqlDate = new java.sql.Date(birthDate.getTime());
                return birthSqlDate;
            } catch (ParseException e) {
                // Xử lý ngoại lệ ParseException nếu định dạng không hợp lệ
                System.err.println("Định dạng ngày không hợp lệ. Vui lòng nhập đúng định dạng dd/MM/yyyy.");
            }
        }
    }
    public static String validateEmail(Scanner scanner) {
        System.out.println("Nhập vào email :");
        System.out.printf("Example :");
        System.out.printf("abc@gmail.com ✓\n");
        String email;
        do {
            email = scanner.nextLine();
            if (!isValidRegex(email)) {
                System.err.println("Email không hợp lệ. Vui lòng nhập đúng theo ví dụ trên..");
            }
        } while (!isValidRegex(email));
        return email;
    }
    public static boolean isValidRegex(String email) {
        String regex = "^[A-Za-z0-9]+@[A-Za-z0-9]+\\.[A-Za-z]{2,}$";
        return email.matches(regex);
    }
    public static String validatePhone(Scanner scanner) {
        System.out.println("Nhập vào số điện thoại :");
        System.out.printf("Example :");
        System.out.printf("070-2373-1996 ✓\n");
        String phone ;
        do {
            phone = scanner.nextLine();
            if (!isValidRegexPhone(phone)) {
                System.err.println("Số điện thoại không hợp lệ. Vui lòng nhập đúng định dạng theo ví dụ trên.");
            }
        } while (!isValidRegexPhone(phone));
        return phone;
    }
    public static boolean isValidRegexPhone(String phone) {
        String regexPhone = "^(\\+?\\d{1,4}[-.\\s]?)?(\\(?\\d{1,3}\\)?[-.\\s]?)?\\d{1,4}[-.\\s]?\\d{1,9}$";
        return phone.matches(regexPhone);
    }
    public static String validateAddress(Scanner scanner) {
        System.out.println("Nhập vào địa chỉ nhân viên :");
        String address;
        do {
            address = scanner.nextLine();
            if (address.isEmpty()) {
                System.err.println("Địa chỉ nhân viên không được để trống. Vui lòng nhập vào địa chỉ.");
            }
        } while (address.isEmpty());
        return address;
    }
    public static int validateEmp_Status(Scanner scanner) {
        System.out.println("Nhập vào trạng thái nhân viên :");
        System.out.println("0: Hoạt động - 1: Nghỉ chế độ - 2: Nghỉ việc");
        int emp_status = 0;
        String inputStatus;
        do {
            inputStatus = scanner.nextLine();
            if (inputStatus.isEmpty()) {
                System.err.println("Trạng thái nhân viên không được để trống. Vui lòng nhập vào trạng thái.");
            } else {
                try {
                    emp_status = Integer.parseInt(inputStatus);
                } catch (NumberFormatException e) {
                    System.err.println("Trạng thái nhân viên là kiểu số. Vui lòng nhập lại đúng định dạng.");
                }
            }
        } while (inputStatus.isEmpty());
       return emp_status;
    }
}
