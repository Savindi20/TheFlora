package lk.ijse.TheFlora.model;

import javafx.collections.ObservableList;
import lk.ijse.TheFlora.db.DBConnection;
import lk.ijse.TheFlora.to.Order;
import lk.ijse.TheFlora.util.CrudUtil;
import lk.ijse.TheFlora.util.DateTimeUtil;
import lk.ijse.TheFlora.view.tm.CustomerOderTm;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderController {
    public static ResultSet getAll() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT *FROM Orders");
    }

    public static ResultSet getAllForId(String text) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT *FROM Orders WHERE orderID=?", text);
    }

    public static boolean remove(String text) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Orders WHERE orderID=?", text);
    }

    public static boolean update(Order order) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE  Orders SET description=? WHERE orderID=?",
                order.getDescription(),
                order.getOrderID()

        );
    }

    public static boolean setOrder(Order order, ObservableList<CustomerOderTm> oderTms) throws SQLException {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            if (setOrder(order)) {
                if (OrderDetailController.setOder(order, oderTms)) {
                    if (StockController.updateStock(oderTms)) {
                        connection.commit();
                        return true;
                    }
                } else {
                    connection.rollback();
                }
            } else {
                connection.rollback();
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        } finally {
            connection.setAutoCommit(true);
        }
        return false;
    }

    private static boolean setOrder(Order order) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO orders VALUES (?,?,?,?)",
                order.getOrderID(),
                DateTimeUtil.dateNow(),
                order.getDescription(),
                order.getCustomerId()

        );
    }

    public static ResultSet getAllId() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT orderID FROM orders ORDER BY LENGTH(orderID),orderID");
    }
}
