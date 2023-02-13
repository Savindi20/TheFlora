package lk.ijse.TheFlora.view.tm;

public class UsetTM {
    private String userID;
    private String userName;
    private String password;
    private String role;
    private String employeeID;

    public UsetTM(String userID, String userName, String password, String role, String employeeID) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.employeeID = employeeID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    @Override
    public String toString() {
        return "UsetTM{" +
                "userID='" + userID + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", employeeID='" + employeeID + '\'' +
                '}';
    }
}
