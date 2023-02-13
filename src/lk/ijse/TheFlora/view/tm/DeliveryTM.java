package lk.ijse.TheFlora.view.tm;

public class DeliveryTM {
    private  String deliveryID;
    private String date;
    private String Price;
    private  String status;
    private  String orderID;

    public  DeliveryTM(){}

    public DeliveryTM(String deliveryID, String date,String Price, String status, String orderID) {
        this.deliveryID = deliveryID;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
        return "DeliveryTM{" +
                "deliveryID='" + deliveryID + '\'' +
                ", date='" + date + '\'' +
                ", Price='" + Price + '\'' +
                ", status='" + status + '\'' +
                ", orderID='" + orderID + '\'' +
                '}';
    }
}
