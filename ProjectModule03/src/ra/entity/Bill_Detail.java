package ra.entity;

import java.util.Scanner;

public class Bill_Detail {
    // Mã phiếu chi tiết
    private int bill_detail_id;
    // Mã phiếu nhập/xuất
    private int bill_id;
    // mã sản phẩm
    private String product_id;
    // Số lượng nhập/xuất
    private int quantity;
    // Giá Nhập/xuất
    private float price;

    public Bill_Detail() {
    }

    public Bill_Detail(int bill_detail_id, int bill_id, String product_id, int quantity, float price) {
        this.bill_detail_id = bill_detail_id;
        this.bill_id = bill_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.price = price;
    }
    /**
     *  các phương thức getter & setter của bill_detail
     * */
    public int getBill_detail_id() {
        return bill_detail_id;
    }

    public void setBill_detail_id(int bill_detail_id) {
        this.bill_detail_id = bill_detail_id;
    }

    public int getBill_id() {
        return bill_id;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    /**
     * Phương thức hiển thị thông tin chi tiết phiếu
     * */
    public void displayBillDetail() {
        System.out.printf("%-20s %-20s %-20s %-20s %-20s\n", this.bill_detail_id,
                this.bill_id,this.product_id,this.quantity,this.price);
    }
    /**
     *  Phương thức cho nhập thông tin chi tiết phiếu
     * */
    public void inputBillDetail(Scanner scanner) {
        this.bill_detail_id = validateBill_detail_id(scanner);
        this.bill_id = validateBill_id(scanner);
        this.product_id = validateProduct_id(scanner);
        this.quantity = validateQuantity(scanner);
        this.price = validatePrice(scanner);
    }
    /**
     * Các phương thức validate thông tin đầu vào của chi tiết phiếu
     * */
    public static int validateBill_detail_id(Scanner scanner) {
        System.out.println("Nhập vào mã phiếu chi tiết :");
        int bill_detail_id = 0;
        String inputBillDetailId;
        do {
            inputBillDetailId = scanner.nextLine();
            if (inputBillDetailId.isEmpty()) {
                System.err.println("Mã phiếu chi tiết không được để trống. Vui lòng nhập vào.");
            } else {
                bill_detail_id = Integer.parseInt(inputBillDetailId);
            }
        } while (inputBillDetailId.isEmpty());
        return bill_detail_id;
    }
    public static int validateBill_id(Scanner scanner) {
        System.out.println("Nhập vào mã phiếu nhập/xuất :");
        int bill_id = 0;
        String inputBillId;
        do {
            inputBillId = scanner.nextLine();
            if (inputBillId.isEmpty()) {
                System.err.println("Mã phiếu nhập / xuất không được để trống. Vui lòng nhập vào.");
            } else {
                bill_id = Integer.parseInt(inputBillId);
            }
        } while (inputBillId.isEmpty());
        return bill_id;
    }
    public static String validateProduct_id(Scanner scanner) {
        System.out.println("Nhập vào mã sản phẩm :");
        System.out.println("Example : SP123 ✓");
        String product_Id;
        boolean runningId = true;
        do {
            product_Id = scanner.nextLine();
            if (!product_Id.isEmpty()) {
               if (product_Id.startsWith("SP") && product_Id.length() == 5) {
                   runningId = false;
               } else {
                   System.err.println("Mã sản phẩm phải bắt đầu là 'SP' và phải có 5 ký tự.");
               }
            } else {
                System.err.println("Mã sản phẩm không được để trống. Vui lòng nhập vào.");
            }
        } while (runningId);
        return product_Id;
    }
    public static int validateQuantity(Scanner scanner) {
        System.out.println("Nhập vào số lượng nhập/xuất :");
        int quantity = 0;
        String inputQuantity;
        do {
            inputQuantity = scanner.nextLine();
            if (inputQuantity.isEmpty()) {
                System.err.println("Số lượng nhập/xuất không được để trống. Vui lòng nhập vào.");
            } else {
                quantity = Integer.parseInt(inputQuantity);
            }
        } while (inputQuantity.isEmpty());
        return quantity;
    }
    public static float validatePrice(Scanner scanner) {
        System.out.println("Nhập vào giá nhập/xuất :");
        float price = 0;
        String inputPrice;
        do {
            inputPrice = scanner.nextLine();
            if (inputPrice.isEmpty()) {
                System.err.println("Giá nhập/xuất không được để trống. Vui lòng nhập vào.");
            } else {
                price = Float.parseFloat(inputPrice);
            }
        } while (inputPrice.isEmpty());
        return price;
    }
}
