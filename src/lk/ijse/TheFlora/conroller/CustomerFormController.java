package lk.ijse.TheFlora.conroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.TheFlora.db.DBConnection;
import lk.ijse.TheFlora.model.CustomerController;
import lk.ijse.TheFlora.to.Customer;
import lk.ijse.TheFlora.util.CrudUtil;
import lk.ijse.TheFlora.view.tm.CustomerTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {
    public TextField txtID;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtPhoneNo;
    public TextField txtEmail;
    public TextField txtUserID;


    @FXML
    private TableView<CustomerTM> table_Customer;

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
    private TableColumn<? , ?> col_userID;

    ObservableList<CustomerTM> tms = FXCollections.observableArrayList();

    private void tableLoad() {
        table_Customer.getItems().clear();
        try {
            ResultSet set= CustomerController.getAll();
            while (set.next()){
                tms.add(new CustomerTM(
                        set.getString(1),
                        set.getString(2),
                        set.getString(3),
                        set.getString(4),
                        set.getInt(5),
                        set.getString(6)
                ));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        table_Customer.setItems(tms);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableLoad();
        col_id.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("full_name"));
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_number.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        col_userID.setCellValueFactory(new PropertyValueFactory<>("userID"));


    }


    public void btnCustomerAddOnAction(ActionEvent actionEvent) {
        Customer customer =new Customer(
                txtID.getText(),
                txtName.getText(),
                txtAddress.getText(),
                txtEmail.getText(),
                Integer.parseInt(txtPhoneNo.getText()),
                txtUserID.getText()

        );

        try {

            if (CrudUtil.execute("INSERT INTO Customer VALUES (?,?,?,?,?,?)",
                    customer.getCustomerID(),
                    customer.getFull_name(),
                    customer.getAddress(),
                    customer.getEmail(),
                    customer.getPhoneNo(),
                    customer.getUserID()

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

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try {
            if (CustomerController.update(new Customer(txtID.getText(),
                    txtName.getText(),
                    txtAddress.getText(),
                    txtEmail.getText(),
                    Integer.parseInt(txtPhoneNo.getText()),
                    txtUserID.getText())
            )){
                new Alert(Alert.AlertType.CONFIRMATION,"Ok").show();
                tableLoad();
                Clear();
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        try {
            ResultSet set=CustomerController.getAllForId(txtID.getText());
            if (set.next()){
                //  txtSupID.setText(set.getString(1));
                txtName.setText(set.getString(2));
                txtAddress.setText(set.getString(3));
                txtEmail.setText(set.getString(4));
                txtPhoneNo.setText(set.getString(5));
                txtUserID.setText(set.getString(6));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent)  {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are Your Sure ! ", ButtonType.NO, ButtonType.YES);
        alert.showAndWait();
        if (alert.getResult().equals(ButtonType.YES)) {
            try {
                if (CustomerController.remove(txtID.getText())) {
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
    private void Clear(){
        txtID.clear();
        txtName.clear();
        txtAddress.clear();
        txtEmail.clear();
        txtPhoneNo.clear();
        txtUserID.clear();

    }

    public void viewReportOnAction(ActionEvent actionEvent) throws JRException, SQLException, ClassNotFoundException {
        InputStream resource = this.getClass().getResourceAsStream("/lk/ijse/TheFlora/report/Customer.jrxml");

        HashMap<String, Object> hm = new HashMap<>();

        JasperReport jasperReport = JasperCompileManager.compileReport(resource);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hm, DBConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);
    }
}
