package lk.ijse.TheFlora.model;

import lk.ijse.TheFlora.to.Customer;
import lk.ijse.TheFlora.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerController {
    public static ResultSet getAll() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT *FROM Customer");
    }

    public static ResultSet getAllForId(String text) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT *FROM Customer WHERE customerID=?", text);
    }

    public static boolean remove(String text) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Customer WHERE customerID=?", text);
    }

    public static boolean update(Customer customer) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE  Customer SET full_name=?,address=?,email=?,phoneNo=?,userID=? WHERE customerID=?",
                customer.getFull_name(),
                customer.getAddress(),
                customer.getEmail(),
                customer.getPhoneNo(),
                customer.getUserID(),
                customer.getCustomerID()



        );
    }
}