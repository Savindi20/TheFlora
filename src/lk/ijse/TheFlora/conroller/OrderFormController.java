package lk.ijse.TheFlora.conroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.TheFlora.model.OrderController;
import lk.ijse.TheFlora.to.Order;
import lk.ijse.TheFlora.util.CrudUtil;
import lk.ijse.TheFlora.util.DateTimeUtil;
import lk.ijse.TheFlora.view.tm.OrderTM;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class OrderFormController implements Initializable {
    public TextField txtOrderID;
    public TextField txtDate;
    public TextField txtDescription;
    public TextField txtCustomID;
    ObservableList<OrderTM> tms = FXCollections.observableArrayList();
    @FXML
    private TableView<OrderTM> table_users;
    @FXML
    private TableColumn<?, ?> col_id;
    @FXML
    private TableColumn<?, ?> col_date;
    @FXML
    private TableColumn<?, ?> col_description;
    @FXML
    private TableColumn<?, ?> col_customID;

    private void tableLoad() {
        table_users.getItems().clear();
        try {
            ResultSet set = OrderController.getAll();
            while (set.next()) {
                tms.add(new OrderTM(
                        set.getString(1),
                        set.getString(2),
                        set.getString(3),
                        set.getString(4)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        table_users.setItems(tms);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableLoad();
        col_id.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_customID.setCellValueFactory(new PropertyValueFactory<>("customerId"));
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        Order order = new Order(
                txtOrderID.getText(),
                txtDescription.getText(),
                txtCustomID.getText()
        );


        try {

            if (CrudUtil.execute("INSERT INTO Orders VALUES (?,?,?,?)",
                    order.getOrderID(),
                    DateTimeUtil.dateNow(),
                    order.getDescription(),
                    order.getCustomerId()
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
            if (OrderController.update(new Order(
                    txtOrderID.getText(),
                    txtDescription.getText(),
                    txtCustomID.getText())

            )) {
                new Alert(Alert.AlertType.CONFIRMATION, "Ok").show();
                tableLoad();
                Clear();
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        try {
            ResultSet set = OrderController.getAllForId(txtOrderID.getText());
            if (set.next()) {
                //  txtSupID.setText(set.getString(1));
                txtDescription.setText(set.getString(2));
                txtCustomID.setText(set.getString(4));
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
                if (OrderController.remove(txtOrderID.getText())) {
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

    private void Clear() {
        txtOrderID.clear();
        txtDescription.clear();
        txtCustomID.clear();
    }
}
