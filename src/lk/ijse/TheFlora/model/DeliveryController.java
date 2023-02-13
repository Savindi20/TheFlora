package lk.ijse.TheFlora.model;

import lk.ijse.TheFlora.to.Delivery;
import lk.ijse.TheFlora.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeliveryController {
    public static ResultSet getAll() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT *FROM Delivery");
    }

    public static ResultSet getAllForId(String text) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT *FROM Delivery WHERE deliveryID =?", text);
    }

    public static boolean remove(String text) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Delivery WHERE deliveryID =?", text);
    }

    public static boolean update(Delivery delivery) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE  Delivery SET Price=?,status=?,orderID=? WHERE deliveryID =?",
                delivery.getPrice(),
                delivery.getStatus(),
                delivery.getOrderID(),
                delivery.getDeliveryID()
        );
    }
}
