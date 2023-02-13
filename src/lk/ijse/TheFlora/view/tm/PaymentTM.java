package lk.ijse.TheFlora.view.tm;

public class PaymentTM {
    private String paymentID;
    private String date;
    private double cost;
    private String supplierID;
    public PaymentTM(){}

    public PaymentTM(String paymentID, String date,double cost, String supplierID) {
        this.paymentID = paymentID;
        this.date = date;
        this.cost = cost;
        this.supplierID = supplierID;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
        return "PaymentTM{" +
                "paymentID='" + paymentID + '\'' +
                ", date='" + date + '\'' +
                ", cost=" + cost +
                ", supplierID='" + supplierID + '\'' +
                '}';
    }
}
