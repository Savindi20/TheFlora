package lk.ijse.TheFlora.view.tm;

public class StockTM {
    private String stockID;
    private String st_name;
    private int quantity;
    private String description;

    public StockTM(){}

    public StockTM(String stockID, String st_name, int quantity, String description) {
        this.stockID = stockID;
        this.st_name = st_name;
        this.quantity = quantity;
        this.description = description;
    }

    public String getStockID() {
        return stockID;
    }

    public void setStockID(String stockID) {
        this.stockID = stockID;
    }

    public String getSt_name() {
        return st_name;
    }

    public void setSt_name(String st_name) {
        this.st_name = st_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "StockTM{" +
                "stockID='" + stockID + '\'' +
                ", st_name='" + st_name + '\'' +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                '}';
    }
}
