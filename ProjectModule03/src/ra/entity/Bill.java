package ra.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Bill {
    // mã phiếu
    private int bill_id;
    // mã code
    private String bill_code;
    // Loại phiếu nhập (1) / xuất (0)
    private boolean bill_type;
    // Mã nhân viên nhập/xuất
    private String emp_id_created;
    // Ngày tạo
    private Date created;
    //  Mã nhân viên duyệt
    private String emp_id_auth;
    // Ngày duyệt
    private Date auth_date;
    // Trạng thái (0-Tạo 1- Hủy 2-Duyệt)
    private int bill_status;

    public Bill() {
    }

    public Bill(int bill_id, String bill_code, boolean bill_type, String emp_id_created, Date created,
                String emp_id_auth, Date auth_date, int bill_status) {
        this.bill_id = bill_id;
        this.bill_code = bill_code;
        this.bill_type = bill_type;
        this.emp_id_created = emp_id_created;
        this.created = created;
        this.emp_id_auth = emp_id_auth;
        this.auth_date = auth_date;
        this.bill_status = bill_status;
    }
/**
 *  các phương thúc getter và setter của bill
 * */
    public int getBill_id() {
        return bill_id;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    public String getBill_code() {
        return bill_code;
    }

    public void setBill_code(String bill_code) {
        this.bill_code = bill_code;
    }

    public boolean isBill_type() {
        return bill_type;
    }

    public void setBill_type(boolean bill_type) {
        this.bill_type = bill_type;
    }

    public String getEmp_id_created() {
        return emp_id_created;
    }

    public void setEmp_id_created(String emp_id_created) {
        this.emp_id_created = emp_id_created;
    }

    public Date getCreated() {
        return  created;
    }
//    public void setCreated(Date created) {
//        this.created = created;
//    }
//public Date getCreated() {
//    return created;
//}

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getEmp_id_auth() {
        return emp_id_auth;
    }

    public void setEmp_id_auth(String emp_id_auth) {
        this.emp_id_auth = emp_id_auth;
    }

    public Date getAuth_date() {
        return auth_date;
    }

    public void setAuth_date(Date auth_date) {
        this.auth_date = auth_date;
    }

    public int getBill_status() {
        return bill_status;
    }

    public void setBill_status(int bill_status) {
        this.bill_status = bill_status;
    }
    /**
     * Phương thức hiển thị thông tin của phiếu
     * */
    public void displayBill() {
        String bill_type_st = (this.bill_type) ? "Nhập" : "Xuất";
        String statusSt = (this.bill_status == 0) ? "Tạo" : (this.bill_status == 1) ? "Huỷ" : "Duyệt";
        System.out.printf(" %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s\n",
                this.bill_id,this.bill_code,bill_type_st,this.emp_id_created,this.created,
                this.emp_id_auth,this.auth_date,statusSt);
    }
    /**
     * Phương thức nhập thông tin của phiếu
     * */
    public void inputBill(Scanner scanner) {
        this.bill_code = validateBill_code(scanner);
        this.emp_id_created = validateEmp_id_created(scanner);
        try {
            this.created = validateCreatedBill(scanner);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.emp_id_auth = validateEmp_id_auth(scanner);
        try {
            this.auth_date = validateAuth_date(scanner);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.bill_status = validateBill_status(scanner);
    }
    /**
     * Các phương thức validate cho Bill
     * */
    public static int validateBill_id(Scanner scanner) {
        System.out.println("Nhập vào mã phiếu :");
        int bill_id = 0;
        String inputBilId;
        do {
            inputBilId = scanner.nextLine();
            if (inputBilId.isEmpty()) {
                System.err.println("Mã phiếu không được để trống. Vui lòng nhập vào.");
            } else {
                bill_id = Integer.parseInt(inputBilId);
            }
        } while (inputBilId.isEmpty());
        return bill_id;
    }
    public static String validateBill_code(Scanner scanner) {
        System.out.println("Nhập vào mã code :");
        String bill_code;
        do {
            bill_code = scanner.nextLine();
            if (bill_code.isEmpty() && (bill_code.length() > 10) || bill_code.length() < 10) {
                System.err.println("Mã code không được để trống và phải có 10 ký tự. Vui lòng nhập vào");
            }
        }
        while (bill_code.isEmpty());
        return bill_code;
    }
    public static boolean validateBill_type(Scanner scanner) {
        System.out.println("Nhập vào loại phiếu :");
        System.out.println(" true : Nhập - false : Xuất");
        while (true) {
            String inputBillType = scanner.nextLine().toLowerCase().trim();
            if (inputBillType.isEmpty() || (!inputBillType.equals("true") && !inputBillType.equals("false"))) {
                System.err.println("Loại phiếu không hợp lệ. Vui lòng nhập true hoặc false.");
            }
        }
    }
    public static String validateEmp_id_created(Scanner scanner) {
        System.out.println("Nhập vào mã nhân viên nhập/xuất :");
        String emp_id_created;
        boolean empIdRunning = true;
        do {
            emp_id_created = scanner.nextLine();
            if (emp_id_created.isEmpty()) {
                System.err.println("Mã nhân viên nhập/xuất không được bỏ trống. Vui lòng nhập vào.");
            } else {
                if (emp_id_created.startsWith("EM") && emp_id_created.length() == 5) {
                    empIdRunning = false;
                } else {
                    System.err.println("Mã nhân viên phải bắt đầu là 'EM' và phải có có 5 ký tự");
                }
            }
        } while (empIdRunning);
        return  emp_id_created;
    }
    public static java.sql.Date validateCreatedBill(Scanner scanner) throws ParseException {
        System.out.println("Nhập vào ngày tạo (dd/MM/yyyy):");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        do {
            String createdDate = scanner.nextLine().trim();
            if (createdDate.isEmpty()) {
                System.err.println("Ngày tạo không được để trống. Vui lòng nhập vào.");
                continue;
            }
            try {
                Date createdBillDate = sdf.parse(createdDate);
                // Chuyển đổi từ java.util.Date sang java.sql.Date
                java.sql.Date createdBillSqlDate = new java.sql.Date(createdBillDate.getTime());
                return createdBillSqlDate;
            } catch (ParseException e) {
                // Xử lý ngoại lệ ParseException nếu định dạng không hợp lệ
                System.err.println("Định dạng ngày tạo không hợp lệ. Vui lòng nhập đúng định dạng dd/MM/yyyy");
            }
        } while (true);
    }
    public static String validateEmp_id_auth(Scanner scanner) {
        System.out.println("Nhập vào mã nhân viên duyệt :");
        String emp_id_auth;
        boolean empIdAuthRunning = true;
        do {
            emp_id_auth = scanner.nextLine();
            if (emp_id_auth.isEmpty()) {
                System.err.println("Mã nhân viên duyệt không được để trống. Vui lòng nhập vào.");
            } else {
                if (emp_id_auth.startsWith("EM") && emp_id_auth.length() == 5) {
                    empIdAuthRunning = false;
                } else {
                    System.err.println("Mã nhân viên duyệt phải bắt đầu là 'EM và phải có 5 ký tự.'");
                }
            }
        } while (empIdAuthRunning);
        return emp_id_auth;
    }
    public static java.sql.Date validateAuth_date(Scanner scanner) throws ParseException {
        System.out.println("Nhập vào ngày duyệt (dd/MM/yyyy):");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        while (true) {
            String inputAuthDate = scanner.nextLine().toLowerCase().trim();
            if (inputAuthDate.isEmpty()) {
                System.err.println("Ngày duyệt không được để trống. Vui lòng nhập vào.");
                continue;
            }
            try {
                Date authDate = sdf.parse(inputAuthDate);
                // Chuyển đổi từ java.util.Date sang java.sql.Date
                java.sql.Date authSqlDate = new java.sql.Date(authDate.getTime());
                return authSqlDate;
            } catch (ParseException e) {
                // Xử lý ngoại lệ ParseException nếu định dạng không hợp lệ
                System.err.println("Ngày duyệt không hợp lệ. Vui lòng nhập đúng định dạng dd/MM/yyyy.");
            }
        }
    }
    public static int validateBill_status(Scanner scanner) {
        System.out.println("Nhập vào trạng thái của phiếu :");
        System.out.println(" 0: Tạo - 1: Huỷ - 2: Duyệt");
        int bill_status =0;
        String inputBillStatus;
        do {
            inputBillStatus = scanner.nextLine();
            if (inputBillStatus.isEmpty()) {
                System.err.println("Trạng thái của phiếu không được để trống. Vui lòng nhập vào.");
            } else {
                bill_status = Integer.parseInt(inputBillStatus);
            }
            if (bill_status >= 3) {
                System.err.println("Vui lòng chỉ chọn 0: Tạo - 1: Huỷ - 2: Duyệt ");
            }
        } while (inputBillStatus.isEmpty());
        return bill_status;
    }
 }
