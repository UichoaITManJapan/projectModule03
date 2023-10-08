package ra.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Product {
    // mã sản phẩm
    private String product_id;
    // tên sản phẩm
    private String product_name;
    // nhà sản xuất
    private String manufacturer;
    // ngày tạo
    private Date created;
    // lô chứa sản phẩm
    private int batch;
    // số lượng sản phẩm
    private int quantity;
    // Trạng thái (1- Hoạt động 0- Không hoạt động)
    private boolean product_status;

    public Product() {
    }

    public Product(String product_id, String product_name, String manufacturer, Date created, int batch,
                   int quantity, boolean product_status) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.manufacturer = manufacturer;
        this.created = created;
        this.batch = batch;
        this.quantity = quantity;
        this.product_status = product_status;
    }
    /**
     * Các phương thức getter & setter cảu product
     * */
    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isProduct_status() {
        return product_status;
    }

    public void setProduct_status(boolean product_status) {
        this.product_status = product_status;
    }

    /**
     * phương thức hiển thị thông tin sản phẩm
     * */
    public void displayProduct() {
        String statusString = (this.product_status) ? "Hoạt động    " : "Không hoạt động";
//        System.out.printf("%-20s%-20s%-20s%-10s%-15s%-15s%-15s\n", "Mã sản phẩm", "Tên sản phẩm", "Nhà sản xuất",
//                "Ngày tạo", "Lô sản xuất", "Số lượng", "Tình trạng");
        System.out.printf("%-20s%-20s%-20s%-15s%-15s%-15s%-15s\n", this.product_id, this.product_name,
                this.manufacturer, this.created, this.batch, this.quantity,statusString);
    }
    /**
     *  phương thức nhập thông tin sản phẩm
     * */
    public void inputDataProduct(Scanner scanner) {
        this.product_id = validateProductId(scanner);
        this.product_name = validateProductName(scanner);
        this.manufacturer = validateManufacturer(scanner);
        try {
            this.created = validateCreated(scanner);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.batch = validateBatch(scanner);
        this.quantity = validateQuantity(scanner);
        this.product_status = validateProductStatus(scanner);
    }
    /**
     * các phương thức validate thông tin sản phẩm
     * @
     * */
    public static String validateProductId(Scanner scanner) {
        boolean running = true;
        System.out.println("Nhập vào mã sản phẩm :");
        String inputProductId;
        do {
            inputProductId = scanner.nextLine();
            if (!inputProductId.isEmpty()) {
                if (inputProductId.startsWith("SP") && inputProductId.length() == 5) {
                    running = false;
                } else {
                    System.err.println("Mã sản phẩm phải bắt đầu là 'SP' và phải có 5 ký tự. Vui lòng kiểm tra lại.");
                }
            } else {
                System.err.println("Mã sản phẩm không được để trống. Vui lòng nhập vào mã sản phẩm.");
            }
        }  while (running);
        return inputProductId;
    }
    public static String validateProductName(Scanner scanner) {
        String productName;
        System.out.println("Nhập vào tên sản phẩm :");
        do {
            productName = scanner.nextLine().trim();
            if (productName.isEmpty()) {
                System.err.println("Tên sản phẩm không dược để trống. Vui lòng nhâọ vào tên sản phẩm.");
            }
        } while (productName.isEmpty());
        return productName;
    }
    public static String validateManufacturer(Scanner scanner) {
        String manufaturer;
        System.out.println("Nhập vào tên nhà sản xuất :");
        do {
           manufaturer = scanner.nextLine().trim();
            if (manufaturer.isEmpty()){
                System.err.println("Nhà sản xuất không được để trống. Vui lòng nhập vào tên nhà sản xuất.");
            }
        } while (manufaturer.isEmpty());
        return manufaturer;
    }
    public static java.sql.Date validateCreated(Scanner scanner) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Nhập vào ngày tạo (dd/MM/yyyy):");
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.err.println("ngày tạo không được để trống. Vui lòng nhập vào ngày tạo.");
                continue;
            }
            try {
                Date createdDate = sdf.parse(input);
                // Chuyển đổi từ java.util.Date sang java.sql.Date
                java.sql.Date createdSqlDate = new java.sql.Date(createdDate.getTime());
                return createdSqlDate;
            } catch (ParseException e) {
                // Xử lý ngoại lệ ParseException nếu định dạng không hợp lệ
                System.err.println("Định dạng ngày không hợp lệ. Vui lòng nhập đúng định dạng dd/MM/yyyy.");
            }
        }
    }
    public static int validateBatch(Scanner scanner) {
        int batch = 0;
        String inputBatch;
        do {
            System.out.println("Nhập vào số lô chứa sản phẩm :");
            inputBatch = scanner.nextLine();
            if (inputBatch.isEmpty()) {
                System.err.println("Số lô chứa sản phẩm không được để trống. Vui lòng nhập vào số lô chứa.");
            } else {
                try {
                    batch = Integer.parseInt(inputBatch);
                } catch (NumberFormatException e) {
                    System.err.println("Số lô chứa sản phẩm phải là kiểu số. Vui lòng nhập lại đúng định dạng.");
                }
            }
        } while (inputBatch.isEmpty());
        return batch;
    }
    public static int validateQuantity(Scanner scanner) {
        int quatity = 0;
        String inputQuantity;
        System.out.println("Nhập vào số lượng sản phẩm :");
        do {
            inputQuantity = scanner.nextLine().trim();
            if (inputQuantity.isEmpty()) {
                System.err.println("Số lượng sản phẩm không được để trống. Vui lòng nhập vào số lương sản phẩm.");
            } else {
                try {
                    quatity = Integer.parseInt(inputQuantity);
                } catch (NumberFormatException e) {
                    System.err.println("Số lượng sản phẩm phải là kiểu số. Vui lòng nhập lại đúng định dạng.");
                }
            }
        } while (inputQuantity.isEmpty());
        return quatity;
    }
    public static boolean validateProductStatus(Scanner scanner) {
        System.out.println("Nhập vào trạng thái sản phẩm :");
        System.out.println("true : Hoạt động - false : Không hoạt động");
        while (true) {
            String inputStatus = scanner.nextLine().toLowerCase().trim();
           if (inputStatus.isEmpty() || (!inputStatus.equals("true") && !inputStatus.equals("false"))) {
               System.err.println("Trạng thái sản phẩm không hợp lệ. Vui lòng nhập true hoặc false.");
           } else {
               return Boolean.parseBoolean(inputStatus);
           }
        }
    }
}
