package lk.ijse.TheFlora.to;

public class Employee {
    private  String employeeID;
    private String full_name;
    private  String address;
    private  String email;
    private int phoneNo;
    private int salary;

    public Employee(){}
    public Employee(String supplierID, String full_name, String address, String email, int phoneNo, int salary) {
        this.employeeID = supplierID;
        this.full_name = full_name;
        this.address = address;
        this.email = email;
        this.phoneNo = phoneNo;
        this.salary = salary;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
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

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeID='" + employeeID + '\'' +
                ", full_name='" + full_name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phoneNo=" + phoneNo +
                ", salary=" + salary +
                '}';
    }
}
