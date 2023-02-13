package lk.ijse.TheFlora.model;

import lk.ijse.TheFlora.to.Item;
import lk.ijse.TheFlora.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemController {
    public static ResultSet getAll() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT *FROM Item");
    }

    public static ResultSet getAllForId(String text) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT *FROM Item WHERE itemID=?", text);
    }

    public static boolean remove(String text) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Item WHERE itemID=?", text);
    }

    public static boolean update(Item item) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE  Item SET It_name=?,Price=?,description=?,stockID=? ,qty=? WHERE itemID=?",
                item.getIt_name(),
                item.getPrice(),
                item.getDescription(),
                item.getStockID(),
                item.getQty(),
                item.getItemID()

        );
    }

    public static ResultSet getAllForid(String valueOf) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT Stock.quantity,Item.It_name,Item.Price FROM item INNER JOIN Stock ON Item.stockID = Stock.stockID WHERE itemID=?",valueOf);
    }
}
