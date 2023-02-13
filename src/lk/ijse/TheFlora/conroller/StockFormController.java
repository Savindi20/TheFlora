package lk.ijse.TheFlora.conroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.TheFlora.model.StockController;
import lk.ijse.TheFlora.to.Stock;
import lk.ijse.TheFlora.util.CrudUtil;
import lk.ijse.TheFlora.view.tm.StockTM;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StockFormController implements Initializable {
    public TextField txtStID;
    public TextField txtStName;
    public TextField txtStQuntity;
    public TextField txtStDesctip;

    @FXML
    private TableView tableLoad;

    @FXML
    private TableColumn<?, ?> col_id;

    @FXML
    private TableColumn<?, ?> col_name;

    @FXML
    private TableColumn<?, ?> col_quantity;

    @FXML
    private TableColumn<?, ?> col_description;

    ObservableList<StockTM> tms = FXCollections.observableArrayList();

    private void tableLoad() {
        tableLoad.getItems().clear();
        try {
            ResultSet set= StockController.getAll();
            while (set.next()){
                tms.add(new StockTM(
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
        col_name.setCellValueFactory(new PropertyValueFactory<>("St_name"));
        col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("description"));

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try {
            if (StockController.update(new Stock(txtStID.getText(),
                    txtStName.getText(),
                    Integer.parseInt(txtStQuntity.getText()),
                    txtStDesctip.getText())
            )){
                new Alert(Alert.AlertType.CONFIRMATION,"Ok").show();
                tableLoad();
                Clear();
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        Stock s=new Stock(
                txtStID.getText(),
                txtStName.getText(),
                Integer.parseInt(txtStQuntity.getText()),
                txtStDesctip.getText()
        );
        try {

            if (CrudUtil.execute("INSERT INTO stock VALUES (?,?,?,?)",
                 s.getStockID(),
                    s.getSt_name(),
                    s.getQuantity(),
                    s.getDescription()
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

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are Your Sure ! ", ButtonType.NO, ButtonType.YES);
        alert.showAndWait();
        if (alert.getResult().equals(ButtonType.YES)) {
            try {
                if (StockController.remove(txtStID.getText())) {
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
            ResultSet set= StockController.getAllForId(txtStID.getText());
            if (set.next()){
                //  txtSupID.setText(set.getString(1));
                txtStName.setText(set.getString(2));
                txtStQuntity.setText(set.getString(3));
                txtStDesctip.setText(set.getString(4));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void Clear(){
        txtStID.clear();
        txtStName.clear();
        txtStQuntity.clear();
        txtStDesctip.clear();

    }
}
