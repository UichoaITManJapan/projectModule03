package ra.adminHandle;

import ra.adminBussiness.ProductBussiness;
import ra.adminRun.Admin_WareHouse_Management;
import ra.color.Color;
import ra.entity.Product;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class Product_Management {
    public static void main(String[] args) {
        productMenu();
    }
    public static void productMenu() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("******************PRODUCT MANAGEMENT****************");
            System.out.println("1. Danh sách sản phẩm");
            System.out.println("2. Thêm mới sản phẩm");
            System.out.println("3. Cập nhật sản phẩm");
            System.out.println("4. Tìm kiếm sản phẩm");
            System.out.println("5. Cập nhật trạng thái sản phẩm");
            System.out.println("6. Thoát");
            System.out.printf(" Chọn :\t");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                System.out.println();
                switch (choice) {
                    case 1:
                        displayProductByPage(scanner);
                        break;
                    case 2:
                        createProduct(scanner);
                        break;
                    case 3:
                        updateProductWithoutQuantity(scanner);
                        break;
                    case 4:
                        searchProductByName(scanner);
                        break;
                    case 5:
                        updateProductStatus(scanner);
                        break;
                    case 6:
                        System.out.println(Color.YELLOW+"Quay lại trang Admin."+Color.RESET);
                        Admin_WareHouse_Management.adminMenu();
                    default:
                        System.err.println("Vui lòng chọn từ 1 đến 6.");
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (true);
    }
    /**
     * Phương thức hiển thị thông tin sản phẩm theo trang
     * */
    public static void displayProductByPage(Scanner scanner) {
        int pageNumber = 1;
        boolean continueDisplay = true;
        do {
            try {
                // tạo 1 biến để kiểm tra trang cuối cùng
                boolean isLastPage = false;
                List<Product> productList = ProductBussiness.displayProductlist(pageNumber);
                System.out.println("THÔNG TIN DANH SÁCH SẢN PHẨM TRANG "+ pageNumber);
                System.out.println();
                System.out.printf("%-20s%-20s%-20s%-15s%-15s%-15s%-15s\n", "Mã sản phẩm", "Tên sản phẩm", "Nhà sản xuất",
                "Ngày tạo", "Lô sản xuất", "Số lượng", "Trạng thái");
                productList.stream().forEach(product -> product.displayProduct());
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
                if (productList.size() < 10 ) {
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
     * Phương thức thêm mới sản phẩm
     * */
    public static void createProduct(Scanner scanner) {
        // nhập dữ liệu cho 1 sản phẩm thêm mới
        Product productNew = new Product();
        productNew.inputDataProduct(scanner);
        // gọi phương thức createProduct ở ProductBussiness để thực hiên thêm dữ liệu vào DB
        boolean result = ProductBussiness.createProduct(productNew);
        if (result) {
            System.out.println("Thêm mới thành công.");
        } else {
            System.err.println("Có lỗi xảy ra trong quá trình thực hiện. Vui lòng kiểm tra lại.");
        }
    }
    /**
     * Phương thức cập nhật sản phẩm không chứa số lượng sản phẩm
     * */
    public static void updateProductWithoutQuantity(Scanner scanner) {
        System.out.println("Nhập vào mã sách cần cập nhật :");
        String updateProductId = scanner.nextLine();
        // Kiểm tra mã sản phẩm có tồn tại hay không
        Product product = ProductBussiness.getProductById(updateProductId);
        if (product != null) {
            // mã sản phẩm tồn tại trong CSDL
            System.out.println("Nhập vào tên sản phẩm cần cập nhật :");
            product.setProduct_name(Product.validateProductName(scanner));
            System.out.println("Nhập vào nhà sản xuất cần cập nhật :");
            product.setManufacturer(Product.validateManufacturer(scanner));
            System.out.println("Nhập vào ngày tạo cần cập nhật (dd/MM/yyyy):");
            try {
                product.setCreated(Product.validateCreated(scanner));
            } catch (ParseException e) {
               e.printStackTrace();
            }
            System.out.println("Nhập vào số lô chứa sản phẩm cần cập nhật :");
            product.setBatch(Product.validateBatch(scanner));
            System.out.println("Nhập vào trạng thái sản phẩm cần cập nhật :");
            product.setProduct_status(Product.validateProductStatus(scanner));
            // thực hiện cập nhật
            boolean result = ProductBussiness.updateProductWithoutQuantity(product);
            if (result) {
                System.out.println("Đã cập nhật thành công sản phẩm có mã sản phẩm là " + updateProductId + ".");
            } else {
                System.err.println("Có lỗi xảy ra trong quá trình thực hiện. Vui lòng kiểm tra lại.");
            }
        } else {
            System.err.println("Mã sách không tồn tại. Vui lòng kiểm tra lại.");
        }
    }
    /**
     * Phương thức tìm kiếm sản phẩm thep tên sản phẩm
     * */
    public static void searchProductByName(Scanner scanner) {
        System.out.println("Nhập vào tên sản phẩm cần tìm kiếm :");
        String searchName = scanner.nextLine().toLowerCase();
        List<Product> productList = ProductBussiness.searchProductByName(searchName);
        if (!productList.isEmpty()) {
            System.out.printf("%-20s%-20s%-20s%-15s%-15s%-15s%-15s\n", "Mã sản phẩm", "Tên sản phẩm", "Nhà sản xuất",
                    "Ngày tạo", "Lô sản xuất", "Số lượng", "Trạng thái");
            productList.stream().forEach(product -> product.displayProduct());
        } else {
            System.err.println("Không tìm thấy sản phẩm cần tìm.");
        }
    }
    /**
     *  Phương thức cập nhật trạng thái sản phẩm
     * */
    public static void updateProductStatus(Scanner scanner) {
        System.out.println("Nhập vào mã sách cần cập nhật :");
        String productIdUpdate = scanner.nextLine();
        Product product = ProductBussiness.getProductById(productIdUpdate);
        if (product != null) {
            // mã sản phẩm tồn tại trong CSDL
            System.out.println("Nhập vào trạng thái cần cập nhật :");
            boolean newStatus = Product.validateProductStatus(scanner);
            // tiến hành cập nhật
            boolean result = ProductBussiness.updateProductStatus(productIdUpdate,newStatus);
            if (result) {
                System.out.println("Cập nhật trạng thái sản phẩm thành công");
            } else {
                System.err.println("Lỗi khi cập nhật trạng thái sản phẩm.");
            }
        } else {
            System.err.println("Mã sách cần cập nhật không tồn tại. Vui lòng kiểm tra lại.");
        }
    }
}
