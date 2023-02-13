package lk.ijse.TheFlora.model;

import javafx.collections.ObservableList;
import lk.ijse.TheFlora.to.Stock;
import lk.ijse.TheFlora.util.CrudUtil;
import lk.ijse.TheFlora.view.tm.CustomerOderTm;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StockController {
    public static ResultSet getAll() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT *FROM Stock");
    }

    public static ResultSet getAllForId(String text) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT *FROM Stock WHERE stockID=?", text);
    }

    public static boolean remove(String text) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Stock WHERE stockID=?", text);
    }

    public static boolean update(Stock stock) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE  Stock SET St_name=?,quantity=?,description=? WHERE stockID=?",

                stock.getSt_name(),
                stock.getQuantity(),
                stock.getDescription(),
                stock.getStockID()

        );
    }

    public static boolean updateStock(ObservableList<CustomerOderTm> oderTms) throws SQLException, ClassNotFoundException {
        for (int i = 0; i < oderTms.size(); i++) {
            if (CrudUtil.execute("UPDATE Stock INNER JOIN Item SET Stock.quantity=quantity-? WHERE itemID=?",
                    oderTms.get(i).getQty(),
                    oderTms.get(i).getId()
            )) {
            } else {
                return false;
            }
        }
        return true;
    }
}