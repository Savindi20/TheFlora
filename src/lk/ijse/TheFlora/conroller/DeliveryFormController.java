package lk.ijse.TheFlora.conroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.TheFlora.model.DeliveryController;
import lk.ijse.TheFlora.to.Delivery;
import lk.ijse.TheFlora.util.CrudUtil;
import lk.ijse.TheFlora.util.DateTimeUtil;
import lk.ijse.TheFlora.view.tm.DeliveryTM;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DeliveryFormController implements Initializable {
    public TextField txtDeliveryID;
    public TextField txtStatus;
    public TextField txtPrice;
    public TextField txtOrderID;
    public TextField txtDate;

    @FXML
    private TableView<DeliveryTM> table_users;

    @FXML
    private TableColumn<? , ?> col_DeliveryID;

    @FXML
    private TableColumn<? , ?> col_Date;

    @FXML
    private TableColumn<? , ?> col_Price;

    @FXML
    private TableColumn<? , ?> col_status;

    @FXML
    private TableColumn<? , ?> col_OrderID;

    ObservableList<DeliveryTM> tms = FXCollections.observableArrayList();

    private void tableLoad() {
        table_users.getItems().clear();
        try {
            ResultSet set= DeliveryController.getAll();
            while (set.next()){
                tms.add(new DeliveryTM(
                        set.getString(1),
                        set.getString(2),
                        set.getString(3),
                        set.getString(4),
                        set.getString(5)
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
        col_DeliveryID.setCellValueFactory(new PropertyValueFactory<>("deliveryID"));
        col_Date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_Price.setCellValueFactory(new PropertyValueFactory<>("Price"));
        col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        col_OrderID.setCellValueFactory(new PropertyValueFactory<>("orderID"));

    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        Delivery delivery=new Delivery(
                txtDeliveryID.getText(),
                txtPrice.getText(),
                txtStatus.getText(),
                txtOrderID.getText()
        );

        try {

            if (CrudUtil.execute("INSERT INTO Delivery VALUES (?,?,?,?,?)",
                    delivery.getDeliveryID(),
                    DateTimeUtil.dateNow(),
                    delivery.getPrice(),
                    delivery.getStatus(),
                    delivery.getOrderID()

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
            if (DeliveryController.update(new Delivery(txtDeliveryID.getText(),
                    txtPrice.getText(),
                    txtStatus.getText(),
                    txtOrderID.getText())
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
            ResultSet set=DeliveryController.getAllForId(txtDeliveryID.getText());
            if (set.next()){
                //  txtSupID.setText(set.getString(1));
                txtPrice.setText(set.getString(3));
                txtStatus.setText(set.getString(4));
                txtOrderID.setText(set.getString(5));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are Your Sure ! ", ButtonType.NO, ButtonType.YES);
        alert.showAndWait();
        if (alert.getResult().equals(ButtonType.YES)) {
            try {
                if (DeliveryController.remove(txtDeliveryID.getText())) {
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
        txtDeliveryID.clear();
        txtPrice.clear();
        txtStatus.clear();
        txtOrderID.clear();

    }
}
