package ra.adminBussiness;

import jdk.nashorn.internal.codegen.CompilerConstants;
import ra.adminHandle.Product_Management;
import ra.connection.ConnectionDB;
import ra.entity.Product;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductBussiness {
    /**
     * phương thức kết nối cơ sở dữ liệu để hiện thị thông tin sản phẩm
     * */
    public static List<Product> displayProductlist(int pageNumber) {
        Connection connection = null;
        CallableStatement callSt = null;
        List<Product> productList = null;
        try {
            connection = ConnectionDB.openConnection();
            callSt = connection.prepareCall("{call getAllProductByPage(?)}");
            // set gía trị tham số đầu vào
            // hiển thị trang đầu tiên
            callSt.setInt(1,pageNumber);
            // thực hiện gọi procedure
            ResultSet resultSet = callSt.executeQuery();
            productList = new ArrayList<>();
            // lấy dưc liệu resultSEt trả về
            while (resultSet.next()) {
                Product product = new Product();
                product.setProduct_id(resultSet.getString("product_Id"));
                product.setProduct_name(resultSet.getString("product_name"));
                product.setManufacturer(resultSet.getString("manufacturer"));
                product.setCreated(resultSet.getDate("created"));
                product.setBatch(resultSet.getInt("batch"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setProduct_status(resultSet.getBoolean("product_status"));
                productList.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection,callSt);
        }
        return productList;
    }
    /**
     * Phương thức kết nối cơ sở dữ liệu để thêm mới sản
     * */
    public static boolean createProduct(Product product) {
        Connection connection = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            connection = ConnectionDB.openConnection();
            callSt = connection.prepareCall("{call createProduct(?,?,?,?,?,?,?)}");
            // set giá trị tham số đâù vào
            callSt.setString(1,product.getProduct_id());
            callSt.setString(2,product.getProduct_name());
            callSt.setString(3,product.getManufacturer());
            callSt.setDate(4,new java.sql.Date(product.getCreated().getTime()));
            callSt.setInt(5,product.getBatch());
            callSt.setInt(6,product.getQuantity());
            callSt.setBoolean(7,product.isProduct_status());
            // gọi procedure
            callSt.executeUpdate();
            result = true;
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection,callSt);
        }
        return result;
    }
    /**
     * Phương thức kết nối cơ sở dữ liệu để cập nhập sản phẩm không chứa quantity
     * */
    public static boolean updateProductWithoutQuantity(Product product) {
        Connection connection = null;
        CallableStatement callSta = null;
        boolean result = false;
        try {
            connection = ConnectionDB.openConnection();
            callSta = connection.prepareCall("{call updateProductWithoutQuantity(?,?,?,?,?,?)}");
            // set dữ liệu cho các tham số đầu vào
            callSta.setString(1,product.getProduct_id());
            callSta.setString(2,product.getProduct_name());
            callSta.setString(3,product.getManufacturer());
            callSta.setDate(4,new java.sql.Date(product.getCreated().getTime()));
            callSta.setInt(5,product.getBatch());
            callSta.setBoolean(6,product.isProduct_status());
            // gọi procedure
            callSta.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection,callSta);
        }
        return result;
    }
    /**
     * Phương thức kết nối cơ sở dữ liệu để lấy thông tin sản phẩm theo mã sản phẩm
     * */
    public static Product getProductById(String productId) {
        Connection connection = null;
        CallableStatement callSt = null;
        Product product = null;
        try {
            connection = ConnectionDB.openConnection();
            callSt = connection.prepareCall("{call getProductById(?)}");
            // set tham số đầu vào
           callSt.setString(1,productId);
           // thực hiện gọi procedure
            ResultSet resultSet = callSt.executeQuery();
            // lấy dữ liệu resultSet đẩy vào đối tượng product trả về
            while (resultSet.next()) {
                product = new Product();
                product.setProduct_id(resultSet.getString("product_Id"));
                product.setProduct_name(resultSet.getString("product_name"));
                product.setManufacturer(resultSet.getString("manufacturer"));
                product.setCreated(resultSet.getDate("created"));
                product.setBatch(resultSet.getInt("batch"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setProduct_status(resultSet.getBoolean("product_status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection,callSt);
        }
        return product;
    }
    public static List<Product> searchProductByName(String productName) {
        List<Product> productList = null;
        Connection connection = null;
        CallableStatement callSt = null;
        try {
            connection = ConnectionDB.openConnection();
            callSt = connection.prepareCall("{call searchProductByName(?)}");
            productList = new ArrayList<>();
            // set tham số đầu vào
            callSt.setString(1,productName);
            // thực hiện gọi procedure
            ResultSet resultSet = callSt.executeQuery();
            // duyệt bản ghi trong ResultSet rồi đẩy trả về DB
            while (resultSet.next()) {
                Product product = new Product();
                product.setProduct_id(resultSet.getString("product_Id"));
                product.setProduct_name(resultSet.getString("product_name"));
                product.setManufacturer(resultSet.getString("manufacturer"));
                product.setCreated(resultSet.getDate("created"));
                product.setBatch(resultSet.getInt("batch"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setProduct_status(resultSet.getBoolean("product_status"));
                productList.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection,callSt);
        }
        return productList;
    }
    /**
     * Phương thức kết nối cơ sở dữ liệu cập nhật trạng thái sản phẩm
     * */
    public static boolean updateProductStatus(String productId,boolean newStatus) {
        Connection connection = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            connection = ConnectionDB.openConnection();
            callSt = connection.prepareCall("{call  updateProductStatus(?,?)}");
            // set tham số đầu vào
            callSt.setString(1,productId);
            callSt.setBoolean(2,newStatus);
            // gọi procedure
            callSt.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection,callSt);
        }
        return result;
    }
}
