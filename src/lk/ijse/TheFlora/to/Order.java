package lk.ijse.TheFlora.to;

public class Order {
    private  String orderID;
    private String description;
    private String customerId;

    public Order(String orderID, String description, String customerId) {
        this.orderID = orderID;
        this.description = description;
        this.customerId = customerId;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID='" + orderID + '\'' +
                ", description='" + description + '\'' +
                ", customerId='" + customerId + '\'' +
                '}';
    }
}
