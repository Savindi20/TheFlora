package lk.ijse.TheFlora.model;

import lk.ijse.TheFlora.to.Employee;
import lk.ijse.TheFlora.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeController {
    public static ResultSet getAll() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT *FROM Employee");
    }

    public static ResultSet getAllForId(String text) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT *FROM Employee WHERE employeeID=?", text);
    }

    public static boolean remove(String text) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Employee WHERE employeeID=?", text);
    }

    public static boolean update(Employee employee) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE  Employee SET full_name=?,address=?,email=?,phoneNo=?,salary=? WHERE employeeID=?",
                employee.getFull_name(),
                employee.getAddress(),
                employee.getEmail(),
                employee.getPhoneNo(),
                employee.getSalary(),
                employee.getEmployeeID()



        );
    }
}