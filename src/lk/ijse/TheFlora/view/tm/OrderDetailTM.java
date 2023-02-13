package lk.ijse.TheFlora.view.tm;

public class OrderDetailTM {
    private  String orderID;
    private String itemID;
    private  int quantity;
    private  String totalPrice;
    private  String id;
    private  String date;

    public  OrderDetailTM(){}

    public OrderDetailTM(String orderID, String itemID, int quantity, String totalPrice, String id, String date) {
        this.orderID = orderID;
        this.itemID = itemID;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.id = id;
        this.date = date;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "OrderDetailTM{" +
                "orderID='" + orderID + '\'' +
                ", itemID='" + itemID + '\'' +
                ", quantity=" + quantity +
                ", totalPrice='" + totalPrice + '\'' +
                ", id='" + id + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
