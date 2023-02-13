package lk.ijse.TheFlora.conroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.TheFlora.model.StockDetailController;
import lk.ijse.TheFlora.to.StockDetail;
import lk.ijse.TheFlora.util.CrudUtil;
import lk.ijse.TheFlora.view.tm.StockDetailTM;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StockDetailFormController implements Initializable {
    public TextField txtStID;
    public TextField txtSupID;
    public TextField txtStQuantity;
    public TextField txtTotalPrice;

    @FXML
    private TableView tableLoad;

    @FXML
    private TableColumn<? , ?> col_id;

    @FXML
    private TableColumn<? , ?> col_SuppID;

    @FXML
    private TableColumn<? , ?> col_quantity;

    @FXML
    private TableColumn<? , ?> col_TotalPrice;

    ObservableList<StockDetailTM> tms = FXCollections.observableArrayList();

    private void tableLoad() {
        tableLoad.getItems().clear();
        try {
            ResultSet set= StockDetailController.getAll();
            while (set.next()){
                tms.add(new StockDetailTM(
                        set.getString(1),
                        set.getString(2),
                        set.getInt(3),
                        set.getString(4)
                ));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        tableLoad.setItems(tms);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableLoad();
        col_id.setCellValueFactory(new PropertyValueFactory<>("stockID"));
        col_SuppID.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
        col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        col_TotalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        StockDetail stockDetail=new StockDetail(
                txtStID.getText(),
                txtSupID.getText(),
                Integer.parseInt(txtStQuantity.getText()),
                txtTotalPrice.getText()

        );

        try {

            if (CrudUtil.execute("INSERT INTO StockDetail VALUES (?,?,?,?)",
                    stockDetail.getStockID(),
                    stockDetail.getSupplierID(),
                    stockDetail.getQuantity(),
                    stockDetail.getTotalPrice()

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
            if (StockDetailController.update(new StockDetail(txtStID.getText(),
                    txtSupID.getText(),
                    Integer.parseInt(txtStQuantity.getText()),
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
            ResultSet set= StockDetailController.getAllForId(txtStID.getText());
            if (set.next()){
                //  txtSupID.setText(set.getString(1));
                txtSupID.setText(set.getString(2));
                txtStQuantity.setText(set.getString(3));
                txtTotalPrice.setText(set.getString(4));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are Your Sure ! ", ButtonType.NO, ButtonType.YES);
        alert.showAndWait();
        if (alert.getResult().equals(ButtonType.YES)) {
            try {
                if (StockDetailController.remove(txtStID.getText())) {
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
        txtStID.clear();
        txtSupID.clear();
        txtStQuantity.clear();
        txtTotalPrice.clear();

    }
}
