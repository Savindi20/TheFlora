package lk.ijse.TheFlora.to;

public class Delivery {
    private  String deliveryID;
    private String Price;
    private  String status;
    private  String orderID;

    public Delivery(){}
    public Delivery(String deliveryID, String Price, String status, String orderID) {
        this.deliveryID = deliveryID;
        this.Price = Price;
        this.status = status;
        this.orderID = orderID;
    }

    public String getDeliveryID() {
        return deliveryID;
    }

    public void setDeliveryID(String deliveryID) {
        this.deliveryID = deliveryID;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "deliveryID='" + deliveryID + '\'' +
                ", Price='" + Price + '\'' +
                ", status='" + status + '\'' +
                ", orderID='" + orderID + '\'' +
                '}';
    }
}
