package lk.ijse.TheFlora.view.tm;

public class OrderTM {
    private String orderID;
    private String date;
    private String description;
    private String customerId;

    public OrderTM() {
    }

    public OrderTM(String orderID,String date, String description, String customerId) {
        this.orderID = orderID;
        this.date = date;
        this.description = description;
        this.customerId = customerId;

    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
        return "OrderTM{" +
                "orderID='" + orderID + '\'' +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                ", customerId='" + customerId + '\'' +
                '}';
    }
}

