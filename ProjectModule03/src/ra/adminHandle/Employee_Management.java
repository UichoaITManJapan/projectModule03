package ra.adminHandle;

import ra.adminBussiness.EmployeeBussiness;
import ra.adminBussiness.ProductBussiness;
import ra.adminRun.Admin_WareHouse_Management;
import ra.color.Color;
import ra.entity.Employee;
import ra.entity.Product;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class Employee_Management {
    public static void main(String[] args) {
        employeeMenu();
    }
    public static void employeeMenu() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("******************EMPLOYEE MANAGEMENT****************");
            System.out.println("1. Danh sách nhân viên");
            System.out.println("2. Thêm mới nhân viên");
            System.out.println("3. Cập nhật thông tin nhân viên");
            System.out.println("4. Cập nhật trạng thái nhân viên");
            System.out.println("5. Tìm kiếm nhân viên");
            System.out.println("6. Thoát");
            System.out.printf(" Chọn :\t");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        displayEmployeeByPage(scanner);
                        break;
                    case 2:
                        createEmployee(scanner);
                        break;
                    case 3:
                        updateEmployee(scanner);
                        break;
                    case 4:
                        updateEmployeeStatus(scanner);
                        break;
                    case 5:
                        searchEmployeeByName(scanner);
                        break;
                    case 6:
                        System.out.println(Color.YELLOW+"Quay lại trang Admin."+Color.RESET);
                        Admin_WareHouse_Management.adminMenu();
                    default:
                        System.err.println("Vui lòng chọn từ 1 đến 6.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (true);
    }
    /**
     * Phương thức hiển thị thông tin sản phẩm theo trang
     * */
    public static void displayEmployeeByPage(Scanner scanner) {
        int pageNumber = 1;
        boolean continueDisplay = true;
        do {
            try {
                // tạo 1 biến để kiểm tra trang cuối cùng
                boolean isLastPage = false;
                List<Employee> employeeList = EmployeeBussiness.displayEmployeeList(pageNumber);
                System.out.println("THÔNG TIN DANH SÁCH SẢN PHẨM TRANG "+ pageNumber);
                System.out.println();
                System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %-20s\n", "Mã nhân viên", "Tên nhân viên", "Ngày sinh",
                        "Email", "Số điện thoại", "Địa chỉ", "Trạng thái");
                employeeList.stream().forEach(employee -> employee.displayEmployee());
                System.out.println();
                if (pageNumber == 1) {
                    System.out.println("Bạn muốn xem trang tiếp theo");
                    System.out.println("\t1.Có \t\t 2.Thoát");
                    System.out.printf(" Chọn :  ");
                    int choice = Integer.parseInt(scanner.nextLine());
                    System.out.println();
                    if (choice == 1) {
                        pageNumber++;
                    } else if (choice == 2){
                        continueDisplay = false;
                    } else {
                        System.err.println("Vui lòng chỉ chọn 1 và 2.");
                    }
                } else {
                    System.out.println("Bạn có muốn quay lại trang trước hoặc xem trang tiếp theo hoặc thoát không?");
                    System.out.println("\t1.Quay lại \t\t2. Trang tiếp theo \t\t3. Thoát");
                    System.out.printf("Chọn :");
                    int choose = Integer.parseInt(scanner.nextLine());
                    System.out.println();
                    if (choose == 1) {
                        if (pageNumber > 1) {
                            pageNumber--;
                        } else {
                            System.out.println("Đã đến trang đầu tiên.");
                        }
                    } else if (choose == 2) {
                        pageNumber++;
                    } else if (choose == 3) {
                        continueDisplay = false;
                    } else {
                        System.err.println("Vui lòng chọn 1 hoặc 2 hoặc 3.");
                    }
                }
                // điều kiện của trang cuối cùng
                if (employeeList.size() < 10 ) {
                    isLastPage = true;
                }
                if (isLastPage) {
                    System.out.println("Bạn có muốn quay lại trang trước hoặc thoát không?");
                    System.out.println("\t1. Quay lại \t\t2. Thoát");
                    System.out.printf("Chọn : ");
                    int choiceLastPage = Integer.parseInt(scanner.nextLine());
                    System.out.println();
                    if (choiceLastPage == 1) {
                        if (pageNumber > 1) {
                            pageNumber--;
                        } else {
                            System.out.println("Đã đến trang đầu tiên.");
                        }
                    } else if (choiceLastPage == 2) {
                        continueDisplay = false;
                    } else {
                        System.err.println("Vui lòng chỉ chọn 1 hoặc 2.");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (continueDisplay);
    }
    /**
     * Phương thức thêm mới nhân viên
     * */
    public static void createEmployee(Scanner scanner) {
        // nhập dữ liệu cho 1 nhân viên thêm mới
        Employee employeeNew = new Employee();
        employeeNew.inputEmployee(scanner);
        // gọi phương thức createEmployee ở EmployeeBussiness để thực hiên thêm dữ liệu vào DB
        boolean result = EmployeeBussiness.createEmployee(employeeNew);
        if (result) {
            System.out.println("Thêm mới thành công.");
        } else {
            System.err.println("Có lỗi xảy ra trong quá trình thực hiện. Vui lòng kiểm tra lại.");
        }
    }
    /**
     * Phương thức cập nhật nhân viên
     * */
    public static void updateEmployee(Scanner scanner) {
        System.out.println("Nhập vào mã nhân viên cần cập nhật :");
        String updateEmpId = scanner.nextLine();
        // Kiểm tra mã nhân viên có tồn tại hay không
        Employee employee = EmployeeBussiness.getEmployeeById(updateEmpId);
        if (employee != null) {
            // mã nhân viên tồn tại trong CSDL
            System.out.println("Nhập vào tên nhân viên cần cập nhật :");
            employee.setEmp_name(Employee.validateEmp_Name(scanner));
            System.out.println("Nhập vào ngày sinh cần cập nhật (dd/MM/yyyy):");
            try {
                employee.setBirth_of_date(Employee.validateBrith_of_date(scanner));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println("Nhập vào email cần cập nhật :");
            employee.setEmail(Employee.validateEmail(scanner));
            System.out.println("Nhập vào số điện thoại cần cập nhật :");
            employee.setPhone(Employee.validatePhone(scanner));
            System.out.println("Nhập vào địa chỉ cần cập nhật :");
            employee.setAddress(Employee.validateAddress(scanner));
            System.out.println("Nhập vào trạng thái cần cập nhật :");
            employee.setEmp_status(Employee.validateEmp_Status(scanner));
            // thực hiện cập nhật
            boolean result = EmployeeBussiness.updateEmployee(employee);
            if (result) {
                System.out.println("Đã cập nhật thành công nhân viên có mã nhân viên là " + updateEmpId + ".");
            } else {
                System.err.println("Có lỗi xảy ra trong quá trình thực hiện. Vui lòng kiểm tra lại.");
            }
        } else {
            System.err.println("Mã nhân viên không tồn tại. Vui lòng kiểm tra lại.");
        }
    }
    /**
     * Phương thức tìm kiếm nhân viên theo tên nhân viên
     * */
    public static void searchEmployeeByName(Scanner scanner) {
        System.out.println("Nhập vào tên nhân viên cần tìm kiếm :");
        String searchName = scanner.nextLine().toLowerCase();
        List<Employee> employeeList = EmployeeBussiness.searchEmployeeByName(searchName);
        if (!employeeList.isEmpty()) {
            System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %-20s\n", "Mã nhân viên", "Tên nhân viên", "Ngày sinh",
                    "Email", "Số điện thoại", "Địa chỉ", "Trạng thái");
            employeeList.stream().forEach(employee -> employee.displayEmployee());
        } else {
            System.err.println("Không tìm thấy nhân viên cần tìm.");
        }
    }
    /**
     * Phương thức cập nhật trạng thái nhân viên
     * */
    public static void updateEmployeeStatus(Scanner scanner) {
        System.out.println("Nhập vào mã nhân viên cần cập nhật :");
        String updateEmpId = scanner.nextLine();
        Employee employee = EmployeeBussiness.getEmployeeById(updateEmpId);
        if (employee != null) {
            // mã nhân viên tồn tại trong CSDL
            System.out.println("Nhập vào trạng thái cần cập nhật :");
            System.out.println("0: Hoạt động - 1: Nghỉ chế độ - 2: Nghỉ việc");
            int newStatus = Employee.validateEmp_Status(scanner);
            //  tiến hành cập nhật
            boolean result = EmployeeBussiness.updateStatusEmployee(updateEmpId,newStatus);
            if (result) {
                System.out.println("Cập nhật trạng thái nhân viên có mã là "+updateEmpId + " thành công.");
            } else {
                System.err.println("Có lỗi xảy ra trong quá trình thực hiện. Vui lòng kiểm tra lại.");
            }
        } else {
            System.err.println("Mã nhân viên không tồn tại. Vui lòng kiểm tra lại.");
        }
    }
}
