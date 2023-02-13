package lk.ijse.TheFlora.view.tm;

public class StockDetailTM {
    private  String stockID;
    private  String supplierID ;
    private int quantity ;
    private String totalPrice ;

    public  StockDetailTM(){}

    public StockDetailTM(String stockID, String supplierID, int quantity, String totalPrice) {
        this.stockID = stockID;
        this.supplierID = supplierID;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public String getStockID() {
        return stockID;
    }

    public void setStockID(String stockID) {
        this.stockID = stockID;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "StockDetailTM{" +
                "stockID='" + stockID + '\'' +
                ", supplierID='" + supplierID + '\'' +
                ", quantity=" + quantity +
                ", totalPrice='" + totalPrice + '\'' +
                '}';
    }
}
