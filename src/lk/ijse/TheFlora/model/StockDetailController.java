package lk.ijse.TheFlora.model;

import lk.ijse.TheFlora.to.StockDetail;
import lk.ijse.TheFlora.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StockDetailController {
    public static ResultSet getAll() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT *FROM StockDetail");
    }

    public static ResultSet getAllForId(String text) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT *FROM StockDetail WHERE stockID=?", text);
    }

    public static boolean remove(String text) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM StockDetail WHERE stockID=?", text);
    }

    public static boolean update(StockDetail stockDetail) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE  StockDetail SET supplierID=?,quantity=?,totalPrice=? WHERE stockID=?",
                stockDetail.getSupplierID(),
                stockDetail.getQuantity(),
                stockDetail.getTotalPrice(),
                stockDetail.getStockID()
        );
    }
}
