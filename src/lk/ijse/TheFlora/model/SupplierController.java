package lk.ijse.TheFlora.model;

import lk.ijse.TheFlora.to.Supplier;
import lk.ijse.TheFlora.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierController {
    public static ResultSet getAll() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT *FROM supplier");
    }

    public static ResultSet getAllForId(String text) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT *FROM supplier WHERE supplierID=?", text);
    }

    public static boolean remove(String text) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM supplier WHERE supplierID=?", text);
    }

    public static boolean update(Supplier supplier) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE  supplier SET full_name=?,address=?,email=?,phoneNo=?,userID=? WHERE supplierID=?",
                supplier.getFull_name(),
                supplier.getAddress(),
                supplier.getEmail(),
                supplier.getPhoneNo(),
                supplier.getUserID(),
                supplier.getSupplierID()



        );
    }
    public static ResultSet getIds() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT supplierID from supplier");
    }
}
