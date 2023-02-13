package lk.ijse.TheFlora.to;

public class StockDetail {
    private  String stockID;
    private  String supplierID;
    private int quantity;
    private String totalPrice;

    public StockDetail(){}
    public StockDetail(String stockID, String supplierID, int quantity, String totalPrice) {
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
        return "StockDetail{" +
                "stockID='" + stockID + '\'' +
                ", supplierID='" + supplierID + '\'' +
                ", quantity=" + quantity +
                ", totalPrice='" + totalPrice + '\'' +
                '}';
    }
}
