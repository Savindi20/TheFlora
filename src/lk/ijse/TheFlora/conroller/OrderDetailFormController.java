package lk.ijse.TheFlora.conroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.TheFlora.model.OrderDetailController;
import lk.ijse.TheFlora.to.OrderDetail;
import lk.ijse.TheFlora.util.CrudUtil;
import lk.ijse.TheFlora.view.tm.OrderDetailTM;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class OrderDetailFormController implements Initializable {
    public TextField txtOrderID;
    public TextField txtQuantity;
    public TextField txtItemID;
    public TextField txtTotalPrice;

    public TableView table_users;
    public TableColumn col_id;
    public TableColumn col_quantity;
    public TableColumn col_Total;
    public TableColumn col_ItemID;
    public TableColumn col_Date;
    public TableColumn col_CustId;


    ObservableList<OrderDetailTM> tms = FXCollections.observableArrayList();

    private void tableLoad() {
        table_users.getItems().clear();
        try {
            ResultSet set= OrderDetailController.getAll();
            while (set.next()){
                tms.add(new OrderDetailTM(
                        set.getString(1),
                        set.getString(2),
                        set.getInt(3),
                        set.getString(4),
                        set.getString(5),
                        set.getString(6)
                ));
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableLoad();
        col_id.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        col_ItemID.setCellValueFactory(new PropertyValueFactory<>("itemID"));
        col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        col_Total.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        col_Date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_CustId.setCellValueFactory(new PropertyValueFactory<>("id"));

        table_users.setItems(tms);

    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        OrderDetail orderDetail=new OrderDetail(
                txtOrderID.getText(),
                txtItemID.getText(),
                Integer.parseInt(txtQuantity.getText()),
                txtTotalPrice.getText()

        );

        try {

            if (CrudUtil.execute("INSERT INTO OrderDetail VALUES (?,?,?,?)",
                    orderDetail.getOrderID(),
                    orderDetail.getItemID(),
                    orderDetail.getQuantity(),
                    orderDetail.getTotalPrice()

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
            if (OrderDetailController.update(new OrderDetail(txtOrderID.getText(),
                    txtItemID.getText(),
                    Integer.parseInt(txtQuantity.getText()),
                    txtTotalPrice.getText())
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
            ResultSet set= OrderDetailController.getAllForId(txtOrderID.getText());
            if (set.next()){
                //  txtSupID.setText(set.getString(1));
                txtItemID.setText(set.getString(2));
                txtQuantity.setText(set.getString(3));
                txtTotalPrice.setText(set.getString(4));
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
                if (OrderDetailController.remove(txtOrderID.getText())) {
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
        txtOrderID.clear();
        txtItemID.clear();
        txtQuantity.clear();
        txtTotalPrice.clear();
    }
}
