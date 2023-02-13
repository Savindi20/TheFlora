package lk.ijse.TheFlora.model;

import lk.ijse.TheFlora.to.Payment;
import lk.ijse.TheFlora.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentController {
    public static ResultSet getAll() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT *FROM payment");
    }

    public static ResultSet getAllForId(String text) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT *FROM payment  WHERE paymentID=?", text);
    }


    public static boolean update(Payment payment) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE  payment SET cost=?,supplierID=? WHERE paymentID=?",
                payment.getCost(),
                payment.getSupplierID(),
                payment.getPaymentID()
        );
    }
}
