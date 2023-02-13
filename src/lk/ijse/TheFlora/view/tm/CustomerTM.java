package lk.ijse.TheFlora.view.tm;

public class CustomerTM {
    private  String customerID;
    private String full_name;
    private  String address;
    private  String email;
    private int phoneNo;
    private String userID;

    public  CustomerTM(){}

    public CustomerTM(String customerID, String full_name, String address, String email, int phoneNo, String userID) {
        this.customerID = customerID;
        this.full_name = full_name;
        this.address = address;
        this.email = email;
        this.phoneNo = phoneNo;
        this.userID = userID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(int phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "CustomerTM{" +
                "customerID='" + customerID + '\'' +
                ", full_name='" + full_name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phoneNo=" + phoneNo +
                ", userID='" + userID + '\'' +
                '}';
    }
}