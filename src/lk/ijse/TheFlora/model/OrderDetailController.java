package lk.ijse.TheFlora.model;

import javafx.collections.ObservableList;
import lk.ijse.TheFlora.to.Order;
import lk.ijse.TheFlora.to.OrderDetail;
import lk.ijse.TheFlora.util.CrudUtil;
import lk.ijse.TheFlora.view.tm.CustomerOderTm;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailController {
    public static ResultSet getAll() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT o.orderID,od.itemID,od.quantity,od.totalPrice,o.customerId,o.date FROM orderdetail od INNER JOIN Orders O on od.orderID = O.orderID");
    }

    public static ResultSet getAllForId(String text) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT *FROM OrderDetail WHERE orderID=?", text);
    }

    public static boolean remove(String text) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM OrderDetail WHERE orderID=?", text);
    }

    public static boolean update(OrderDetail orderDetail) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE  OrderDetail SET itemID=?,quantity=?,totalPrice=? WHERE orderID=?",
                orderDetail.getItemID(),
                orderDetail.getQuantity(),
                orderDetail.getTotalPrice(),
                orderDetail.getOrderID()

        );
    }

    public static boolean setOder(Order order, ObservableList<CustomerOderTm> oderTms) throws SQLException, ClassNotFoundException {
        for (int i = 0; i < oderTms.size(); i++) {
            if (CrudUtil.execute("INSERT INTO OrderDetail VALUES (?,?,?,?)",
                    order.getOrderID(),
                    oderTms.get(i).getId(),
                    oderTms.get(i).getQty(),
                    oderTms.get(i).getTotal()
            )) {
            } else {
                return false;
            }
        }
        return true;
    }
}
