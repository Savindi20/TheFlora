package lk.ijse.TheFlora.conroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.TheFlora.model.SupplierController;
import lk.ijse.TheFlora.to.Supplier;
import lk.ijse.TheFlora.util.CrudUtil;
import lk.ijse.TheFlora.view.tm.SupplierTM;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SupplierFormController implements Initializable {
    public TextField txtSupID;
    public TextField txtSupName;
    public TextField txtSupAddress;
    public TextField txtSupEmail;
    public TextField txtSupPhoneNo;
    public TextField txtUserID;


    @FXML
    private TableView table_users;

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
    private TableColumn<? , ?> col_userid;

    ObservableList<SupplierTM> tms = FXCollections.observableArrayList();

    private void tableLoad() {
        table_users.getItems().clear();
        try {
            ResultSet set= SupplierController.getAll();
            while (set.next()){
                tms.add(new SupplierTM(
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
        table_users.setItems(tms);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableLoad();
        col_id.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("full_name"));
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_number.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        col_userid.setCellValueFactory(new PropertyValueFactory<>("userID"));


    }


    public void btnAddOnAction(ActionEvent actionEvent) {
        Supplier supplier=new Supplier(
                txtSupID.getText(),
                txtSupName.getText(),
                txtSupAddress.getText(),
                txtSupEmail.getText(),
                Integer.parseInt(txtSupPhoneNo.getText()),
                txtUserID.getText()

        );

        try {

            if (CrudUtil.execute("INSERT INTO supplier VALUES (?,?,?,?,?,?)",
                    supplier.getSupplierID(),
                    supplier.getFull_name(),
                    supplier.getAddress(),
                    supplier.getEmail(),
                    supplier.getPhoneNo(),
                    supplier.getUserID()

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
            if (SupplierController.update(new Supplier(txtSupID.getText(),
                    txtSupName.getText(),
                    txtSupAddress.getText(),
                    txtSupEmail.getText(),
                    Integer.parseInt(txtSupPhoneNo.getText()),
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

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are Your Sure ! ", ButtonType.NO, ButtonType.YES);
        alert.showAndWait();
        if (alert.getResult().equals(ButtonType.YES)) {
            try {
                if (SupplierController.remove(txtSupID.getText())) {
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
            ResultSet set=SupplierController.getAllForId(txtSupID.getText());
            if (set.next()){
              //  txtSupID.setText(set.getString(1));
                txtSupName.setText(set.getString(2));
                txtSupAddress.setText(set.getString(3));
                txtSupEmail.setText(set.getString(4));
                txtSupPhoneNo.setText(set.getString(5));
                txtUserID.setText(set.getString(6));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void Clear(){
        txtSupID.clear();
        txtSupName.clear();
        txtSupAddress.clear();
        txtSupEmail.clear();
        txtSupPhoneNo.clear();
        txtUserID.clear();

    }
}
