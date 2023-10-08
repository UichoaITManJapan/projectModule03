package ra.adminHandle;

import ra.adminBussiness.BillBussiness;
import ra.adminBussiness.BillDetailBussiness;
import ra.adminBussiness.ReceiptBussiness;
import ra.adminRun.Admin_WareHouse_Management;
import ra.color.Color;
import ra.entity.Bill;
import ra.entity.Bill_Detail;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class Bill_Management {
    public static void main(String[] args) {
        billMenu();
    }
    public static void billMenu() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("******************BILL MANAGEMENT****************");
            System.out.println("1. Danh sách phiếu xuất");
            System.out.println("2. Tạo phiếu xuất");
            System.out.println("3. Cập nhật thông tin phiếu xuất");
            System.out.println("4. Chi tiết phiếu xuất");
            System.out.println("5. Duyệt phiếu xuất");
            System.out.println("6. Tìm kiếm phiếu xuất");
            System.out.println("7. Thoát");
            System.out.printf(" Chọn :\t");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        displayBill(scanner);
                        break;
                    case 2:
                        createBill(scanner);
                        break;
                    case 3:
                        updateBillByStatus(scanner);
                        break;
                    case 4:
                        displayBillDetail();
                        break;
                    case 5:
                        updateProductQuantityAndStatus(scanner);
                        break;
                    case 6:
                        searchBillByIdAndCode(scanner);
                        break;
                    case 7:
                        System.out.println(Color.YELLOW+"Quay lại trang Admin."+Color.RESET);
                        Admin_WareHouse_Management.adminMenu();
                    default:
                        System.err.println("vui lòng chọn từ 1 đến 7.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (true);
    }
    /**
     * Phương thức hiển thị danh sách phiếu xuất
     * */
    public static void displayBill(Scanner scanner) {
        // biến cố định đây là phiếu xuất
        boolean billtype = false;
        List<Bill> billList = BillBussiness.getAllBill(billtype);
        System.out.printf(" %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s\n",
                "Mã phiếu","Mã code","Loại phiếu","Mã nhân viên xuất","Ngày tạo","Mã nhân viên duyệt","Ngày duyệt","Trạng thái");
        billList.stream().forEach(bill -> bill.displayBill());
    }
    /**
     * Phương thức tạo mới phiếu xuất
     * */
    public static void createBill(Scanner scanner) {
        // biến cố định đây là phiếu xuất
        boolean billTypes = false;
        // nhập dữ liệu cho 1 phiếu xuất thêm mới
        Bill billNew = new Bill();
        billNew.inputBill(scanner);
        // gọi phương thức createReceipt ở ReceiptBussiness để thực hiên thêm dữ liệu vào DB
        boolean result = ReceiptBussiness.createBill(billNew,billTypes);
        if (result) {
            System.out.println("Thêm mới thành công.");
        } else {
            System.err.println("Có lỗi xảy ra trong quá trình thực hiện. Vui lòng kiểm tra lại.");
        }
    }
    /**
     * Phương thức cập nhật phiếu xuất
     * */
    public static void updateBillByStatus(Scanner scanner) {
        boolean billType = false;
        System.out.println("Nhập vào mã phiếu xuất cần cập nhật :");
        int updateBillId = Integer.parseInt(scanner.nextLine());
        System.out.println("Nhập vào mã code phiếu xuất cần cập nhật :");
        String updateBillCode = scanner.nextLine();
        Bill bill =  ReceiptBussiness.getBillByIdOrCode(updateBillId,updateBillCode,billType);
        Bill_Detail billDetail = BillBussiness.getBillDetailById(updateBillId,billType);
        if (bill != null && billDetail != null) {
            // mã phiếu nhập và mã code tồn tại trong CSDL
            System.out.println("Nhập vào mã nhân viên cần cập nhật :");
            bill.setEmp_id_created(Bill.validateEmp_id_created(scanner));
            System.out.println("Nhập vào ngày tạo cần cập nhật (dd/MM/yyyy):");
            try {
                bill.setCreated(Bill.validateCreatedBill(scanner));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println("Nhập vào mã nhân viên duyệt cần cập nhật:");
            bill.setEmp_id_auth(Bill.validateEmp_id_auth(scanner));
            System.out.println("Nhập vào ngày duyệt cần cập nhật (dd/MM/yyyy):");
            try {
                bill.setAuth_date(Bill.validateAuth_date(scanner));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println("Nhập vào trạng thái của phiếu cần cập nhật :");
            bill.setBill_status(Bill.validateBill_status(scanner));
            System.out.println("Nhập vào mã sản phẩm cần cập nhật :");
            billDetail.setProduct_id(Bill_Detail.validateProduct_id(scanner));
            System.out.println("Nhập vào số lượng sản phẩm xuất cần cập nhật :");
            billDetail.setQuantity(Bill_Detail.validateQuantity(scanner));
            System.out.println("Nhập vào số giá xuất cần cập nhật :");
            billDetail.setPrice(Bill_Detail.validatePrice(scanner));
            // tiến hành cập nhật
            boolean resultBill = ReceiptBussiness.updateBillByStatus(bill,billType);
            boolean resultBillDetail = BillDetailBussiness.updateBillDetail(billDetail);
            if (resultBill && resultBillDetail) {
                System.out.println("Cập nhật thành công");

            } else {
                System.err.println("Có lỗi xảy ra trong quá trình thực hiện. Vui lòng kiểm tra lại.");
            }
        } else {
            System.err.println("Phiếu xuất không tồn tại. Vui lòng kiểm tra lại.");
        }
    }
    /**
     *  Phương thức hiển thị thông tin chi tiết phiếu xuất
     * */
    public static void displayBillDetail() {
        boolean billType = false;
        List<Bill_Detail> billDetailList = BillDetailBussiness.displayBillDetailList(billType);
        System.out.printf("%-20s %-20s %-20s %-20s %-20s\n","Mã CTPX",
                "Mã phiếu xuất","Mã sản phẩm","Số lượng xuất","Giá thành");
        billDetailList.stream().forEach(billDetail -> billDetail.displayBillDetail());
    }
    /**
     * Phương thức duyệt phiếu xuất
     * */
    public static void updateProductQuantityAndStatus(Scanner scanner) {
        boolean billType = false;
        System.out.println("Nhập vào mã phiếu cần duyệt :");
        int browserId = Integer.parseInt(scanner.nextLine());
        System.out.println("nhập vào mã code cần duyệt :");
        String browserCode = scanner.nextLine();
        Bill bill =  ReceiptBussiness.getBillByIdOrCode(browserId,browserCode,billType);
        if (bill != null) {
            boolean result = ReceiptBussiness.updateProductQuantityAndStatus(billType,browserId);
            if (result) {
                System.out.println("Duyệt thành công");
            } else {
                System.err.println("Có lỗi");
            }
        }
    }
    /**
     * Phương thức tìm kiếm phiếu xuất
     * */
    public static void searchBillByIdAndCode(Scanner scanner) {
        boolean billType = false;
        System.out.println("Nhập vào mã phiếu cần tìm :");
        int searchId = Integer.parseInt(scanner.nextLine());
        System.out.println("nhập vào mã code cần tìm :");
        String searchCode = scanner.nextLine();
        List<Bill> billList =  ReceiptBussiness.searchReceiptByIdAndCode(searchId,searchCode,billType);
        if (billList != null) {
            System.out.println("Kết quả tìm kiếm ");
            System.out.printf(" %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s\n",
                    "Mã phiếu","Mã code","Loại phiếu","Mã nhân viên xuất","Ngày tạo","Mã nhân viên duyệt","Ngày duyệt",
                    "Trạng thái");
            billList.stream().forEach(bill -> bill.displayBill());
        } else {
            System.err.println("Không tìm thấy phiếu xuất phù hợp với mã và mã code vừa nhập.");
        }
    }
}
