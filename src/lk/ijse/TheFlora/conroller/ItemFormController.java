package lk.ijse.TheFlora.conroller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.TheFlora.model.ItemController;
import lk.ijse.TheFlora.to.Item;
import lk.ijse.TheFlora.util.CrudUtil;
import lk.ijse.TheFlora.view.tm.ItemTM;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ItemFormController implements Initializable {
    public TextField txtItemID;
    public TextField txtItemName;
    public TextField txtItemPrice;
    public TextField txtItemDescribe;
    public TextField txtStockID;
    public JFXTextField txtQuentity;
    ObservableList<ItemTM> tms = FXCollections.observableArrayList();
    @FXML
    private TableView<ItemTM> table_users;
    @FXML
    private TableColumn<?, ?> col_id;
    @FXML
    private TableColumn<?, ?> col_name;
    @FXML
    private TableColumn<?, ?> col_price;
    @FXML
    private TableColumn<?, ?> col_description;
    @FXML
    private TableColumn<?, ?> col_stId;
    @FXML
    private TableColumn<?, ?> colQTY;

    private void tableLoad() {
        table_users.getItems().clear();
        try {
            ResultSet set = ItemController.getAll();
            while (set.next()) {
                tms.add(new ItemTM(
                        set.getString(1),
                        set.getString(2),
                        set.getString(3),
                        set.getString(4),
                        set.getString(5),
                        set.getInt(6)
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
        col_id.setCellValueFactory(new PropertyValueFactory<>("itemID"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("It_name"));
        col_price.setCellValueFactory(new PropertyValueFactory<>("Price"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_stId.setCellValueFactory(new PropertyValueFactory<>("stockID"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));

    }

    public void btnAddItem(ActionEvent actionEvent) {
        Item item = new Item(
                txtItemID.getText(),
                txtItemName.getText(),
                txtItemPrice.getText(),
                txtItemDescribe.getText(),
                txtStockID.getText(),
                Integer.parseInt(txtQuentity.getText())


        );

        try {

            if (CrudUtil.execute("INSERT INTO Item VALUES (?,?,?,?,?,?)",
                    item.getItemID(),
                    item.getIt_name(),
                    item.getPrice(),
                    item.getDescription(),
                    item.getStockID(),
                    item.getQty()


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

    public void btnUpdateItem(ActionEvent actionEvent) {
        try {
            if (ItemController.update(new Item(txtItemID.getText(),
                    txtItemName.getText(),
                    txtItemPrice.getText(),
                    txtItemDescribe.getText(),
                    txtStockID.getText(),
                    Integer.parseInt(txtQuentity.getText())
            ))){
                new Alert(Alert.AlertType.CONFIRMATION,"Ok").show();
                tableLoad();
                Clear();
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void btnDeleteItem(ActionEvent actionEvent) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are Your Sure ! ", ButtonType.NO, ButtonType.YES);
        alert.showAndWait();
        if (alert.getResult().equals(ButtonType.YES)) {
            try {
                if (ItemController.remove(txtItemID.getText())) {
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

    public void btnSearchItem(ActionEvent actionEvent) {
        try {
            ResultSet set = ItemController.getAllForId(txtItemID.getText());
            if (set.next()) {
                txtItemName.setText(set.getString(2));
                txtItemPrice.setText(set.getString(3));
                txtItemDescribe.setText(set.getString(4));
                txtStockID.setText(set.getString(5));
                txtQuentity.setText(set.getString(6));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void Clear() {
        txtItemID.clear();
        txtItemName.clear();
        txtItemPrice.clear();
        txtItemDescribe.clear();
        txtStockID.clear();
        txtQuentity.clear();
    }
}
