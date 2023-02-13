package lk.ijse.TheFlora.to;

public class OrderDetail {
    private  String orderID;
    private String itemID;
    private int quantity;
    private String totalPrice;

    public OrderDetail(){}
    public OrderDetail(String orderID, String itemID,int quantity, String totalPrice) {
        this.orderID = orderID;
        this.itemID = itemID;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
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
        return "OrderDetail{" +
                "orderID='" + orderID + '\'' +
                ", itemID='" + itemID + '\'' +
                ", quantity=" + quantity +
                ", totalPrice='" + totalPrice + '\'' +
                '}';
    }
}
