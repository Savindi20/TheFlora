package lk.ijse.TheFlora.conroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.TheFlora.model.EmployeeController;
import lk.ijse.TheFlora.to.Employee;
import lk.ijse.TheFlora.util.CrudUtil;
import lk.ijse.TheFlora.view.tm.EmployeeTM;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeeFormController implements Initializable{
    public TextField txtEmployeeId;
    public TextField txtEmail;
    public TextField txtSalary;
    public TextField txtAddress;
    public TextField txtPhone_number;
    public TextField txtFullName;

    @FXML
    private TableView<EmployeeTM> table_users;

    @FXML
    private TableColumn<? , ?> col_id;

    @FXML
    private TableColumn<? , ?> col_name;

    @FXML
    private TableColumn<? , ?> col_address;

    @FXML
    private TableColumn<? , ?> col_email;

    @FXML
    private TableColumn<?, ?> col_number;

    @FXML
    private TableColumn<? , ?> col_salary;

    ObservableList<EmployeeTM> tms = FXCollections.observableArrayList();

    private void tableLoad() {
        table_users.getItems().clear();
        try {
            ResultSet set= EmployeeController.getAll();
            while (set.next()){
                tms.add(new EmployeeTM(
                        set.getString(1),
                        set.getString(2),
                        set.getString(3),
                        set.getString(4),
                        set.getInt(5),
                        set.getInt(6)
                ));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        table_users.setItems(tms);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableLoad();
        col_id.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("full_name"));
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_number.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        col_salary.setCellValueFactory(new PropertyValueFactory<>("salary"));


    }
    public void btnAddOnAction(ActionEvent actionEvent) {
        Employee employee =new Employee(
                txtEmployeeId.getText(),
                txtFullName.getText(),
                txtAddress.getText(),
                txtEmail.getText(),
                Integer.parseInt(txtPhone_number.getText()),
                Integer.parseInt(txtSalary.getText())

        );

        try {

            if (CrudUtil.execute("INSERT INTO Employee VALUES (?,?,?,?,?,?)",
                    employee.getEmployeeID(),
                    employee.getFull_name(),
                    employee.getAddress(),
                    employee.getEmail(),
                    employee.getPhoneNo(),
                    employee.getSalary()

            )) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved..!").show();
                tableLoad();
                Clear();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again..!").show();
            }

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent)  {
        try {
            if (EmployeeController.update(new Employee(txtEmployeeId.getText(),
                    txtFullName.getText(),
                    txtAddress.getText(),
                    txtEmail.getText(),
                    Integer.parseInt(txtPhone_number.getText()),
                    Integer.parseInt(txtSalary.getText()))
            )){
                new Alert(Alert.AlertType.CONFIRMATION,"Ok").show();
                tableLoad();
                Clear();
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are Your Sure ! ", ButtonType.NO, ButtonType.YES);
        alert.showAndWait();
        if (alert.getResult().equals(ButtonType.YES)) {
            try {
                if (EmployeeController.remove(txtEmployeeId.getText())) {
                    tableLoad();
                    Clear();
                    new Alert(Alert.AlertType.CONFIRMATION, "Ok").show();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        try {
            ResultSet set= EmployeeController.getAllForId(txtEmployeeId.getText());
            if (set.next()){
                //  txtSupID.setText(set.getString(1));
                txtFullName.setText(set.getString(2));
                txtAddress.setText(set.getString(3));
                txtEmail.setText(set.getString(4));
                txtPhone_number.setText(set.getString(5));
                txtSalary.setText(set.getString(6));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void Clear(){
        txtEmployeeId.clear();
        txtFullName.clear();
        txtAddress.clear();
        txtEmail.clear();
        txtPhone_number.clear();
        txtSalary.clear();

    }
}
