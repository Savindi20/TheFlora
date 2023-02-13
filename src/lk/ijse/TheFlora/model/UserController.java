package lk.ijse.TheFlora.model;

import lk.ijse.TheFlora.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserController {
    public static ResultSet getAll() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT *FROM user");
    }

    public static ResultSet getAllForId(String text) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT *FROM user WHERE userID=?", text);
    }

    public static boolean remove(String text) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM supplier WHERE supplierID=?", text);
    }
    public static ResultSet getIds() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT userID from user");
    }
}
