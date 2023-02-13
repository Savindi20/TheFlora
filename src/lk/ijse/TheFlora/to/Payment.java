package lk.ijse.TheFlora.to;

public class Payment {
    private String paymentID;
    private double cost;
    private String supplierID;

    public Payment(){}

    public Payment(String paymentID, double cost, String supplierID) {
        this.paymentID = paymentID;
        this.cost = cost;
        this.supplierID = supplierID;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentID='" + paymentID + '\'' +
                ", cost=" + cost +
                ", supplierID='" + supplierID + '\'' +
                '}';
    }
}
