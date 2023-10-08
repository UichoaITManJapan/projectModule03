package ra.adminBussiness;

import ra.connection.ConnectionDB;
import ra.entity.Employee;
import ra.entity.Product;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBussiness {
    /**
     * phương thức kết nối cơ sở dữ liệu để hiện thị thông tin nhân viên
     * */
    public static List<Employee> displayEmployeeList(int pageNumber) {
        Connection connection = null;
        CallableStatement callSt = null;
        List<Employee> employeeList = null;
        try {
            connection = ConnectionDB.openConnection();
            callSt = connection.prepareCall("{call getAllEmployeeByPage(?)}");
            // set gía trị tham số đầu vào
            // hiển thị trang đầu tiên
            callSt.setInt(1,pageNumber);
            // thực hiện gọi procedure
            ResultSet resultSet = callSt.executeQuery();
           employeeList = new ArrayList<>();
            // lấy dưc liệu resultSEt trả về
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmp_id(resultSet.getString("emp_Id"));
                employee.setEmp_name(resultSet.getString("emp_name"));
                employee.setBirth_of_date(resultSet.getDate("birth_of_date"));
                employee.setEmail(resultSet.getString("email"));
                employee.setPhone(resultSet.getString("phone"));
                employee.setAddress(resultSet.getString("address"));
                employee.setEmp_status(resultSet.getInt("emp_status"));
               employeeList.add(employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection,callSt);
        }
        return employeeList;
    }
    /**
     * Phương thức kết nối cơ sở dữ liệu để thêm mới sản
     * */
    public static boolean createEmployee(Employee employee) {
        Connection connection = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            connection = ConnectionDB.openConnection();
            callSt = connection.prepareCall("{call createEmployee(?,?,?,?,?,?,?)}");
            // set giá trị tham số đâù vào
            callSt.setString(1,employee.getEmp_id());
            callSt.setString(2,employee.getEmp_name());
            callSt.setDate(3,new java.sql.Date(employee.getBirth_of_date().getTime()));
            callSt.setString(4,employee.getEmail());
            callSt.setString(5,employee.getPhone());
            callSt.setString(6,employee.getAddress());
            callSt.setInt(7,employee.getEmp_status());
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
     * Phương thức kết nối cơ sở dữ liệu để cập nhập thông tin nhân viên
     * */
    public static boolean updateEmployee(Employee employee) {
        Connection connection = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            connection = ConnectionDB.openConnection();
            callSt = connection.prepareCall("{call updateEmployee(?,?,?,?,?,?,?)}");
            // set dữ liệu cho các tham số đầu vào
            callSt.setString(1,employee.getEmp_id());
            callSt.setString(2,employee.getEmp_name());
            callSt.setDate(3,new java.sql.Date(employee.getBirth_of_date().getTime()));
            callSt.setString(4,employee.getEmail());
            callSt.setString(5,employee.getPhone());
            callSt.setString(6,employee.getAddress());
            callSt.setInt(7,employee.getEmp_status());
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
    /**
     * Phương thức kết nối cơ sở dữ liệu để lấy thông tin nhân viên theo mã nhân viên
     * */
    public static Employee getEmployeeById(String empId) {
        Connection connection = null;
        CallableStatement callSt = null;
        Employee employee = null;
        try {
            connection = ConnectionDB.openConnection();
            callSt = connection.prepareCall("{call getEmployeeById(?)}");
            // set tham số đầu vào
            callSt.setString(1,empId);
            // thực hiện gọi procedure
            ResultSet resultSet = callSt.executeQuery();
            // lấy dữ liệu resultSet đẩy vào đối tượng product trả về
            while (resultSet.next()) {
                employee = new Employee();
                employee.setEmp_id(resultSet.getString("emp_Id"));
                employee.setEmp_name(resultSet.getString("emp_name"));
                employee.setBirth_of_date(resultSet.getDate("birth_of_date"));
                employee.setEmail(resultSet.getString("email"));
                employee.setPhone(resultSet.getString("phone"));
                employee.setAddress(resultSet.getString("address"));
                employee.setEmp_status(resultSet.getInt("emp_status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection,callSt);
        }
        return employee;
    }
    /**
     * Phương thức tìm kiếm nhân viên theo tên nhân viên
     * */
    public static List<Employee> searchEmployeeByName(String empName) {
        List<Employee> employeeList = null;
        Connection connection = null;
        CallableStatement callSt = null;
        try {
            connection = ConnectionDB.openConnection();
            callSt = connection.prepareCall("{call searchEmployeetByName(?)}");
            employeeList = new ArrayList<>();
            // set tham số đầu vào
            callSt.setString(1,empName);
            // thực hiện gọi procedure
            ResultSet resultSet = callSt.executeQuery();
            // duyệt bản ghi trong ResultSet rồi đẩy trả về DB
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmp_id(resultSet.getString("emp_Id"));
                employee.setEmp_name(resultSet.getString("emp_name"));
                employee.setBirth_of_date(resultSet.getDate("birth_of_date"));
                employee.setEmail(resultSet.getString("email"));
                employee.setPhone(resultSet.getString("phone"));
                employee.setAddress(resultSet.getString("address"));
                employee.setEmp_status(resultSet.getInt("emp_status"));
                employeeList.add(employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection,callSt);
        }
        return employeeList;
    }
    /**
     * Phương thức cập nhật trạng thái nhân viên
     * */
    public static boolean updateStatusEmployee(String empId,int newStatus) {
        Connection connection = null;
        CallableStatement callST = null;
        boolean result = false;
        try {
            connection = ConnectionDB.openConnection();
            callST = connection.prepareCall("{call updateEmployeeStatus(?,?)}");
            // set tham sô đàu vào
            callST.setString(1,empId);
            callST.setInt(2,newStatus);
            // thực hiện gọi procedure
            callST.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection,callST);
        }
        return result;
    }
}
