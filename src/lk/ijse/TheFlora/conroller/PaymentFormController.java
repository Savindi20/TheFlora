package lk.ijse.TheFlora.conroller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.TheFlora.model.CustomerController;
import lk.ijse.TheFlora.model.ItemController;
import lk.ijse.TheFlora.model.OrderController;
import lk.ijse.TheFlora.to.Order;
import lk.ijse.TheFlora.util.DateTimeUtil;
import lk.ijse.TheFlora.view.tm.CustomerOderTm;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PaymentFormController implements Initializable {


    public JFXComboBox cmbCrustId;
    public JFXComboBox cmbitemId;
    public Label txtTotal;
    public TextField txtCustomerName;
    public TextField txtItemName;
    public TableView tblCart;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colUnitPrice;
    public TableColumn colQty;
    public TableColumn colTotalCost;
    public TableColumn colButton;
    public TextField txtItemQTy;
    public TextField txtItemPrice;
    public TextField txtQTY;

    ObservableList<CustomerOderTm> oderTms = FXCollections.observableArrayList();

    public void addOnAction(ActionEvent actionEvent) {
        if (Integer.parseInt(txtItemQTy.getText())-Integer.parseInt(txtQTY.getText())>0){
            Button button=new Button();
            button.setText("Remove");
            button.setStyle("\n" +
                    "    -fx-font-weight: bold;" +
                    "    -fx-background-color:#EE9919 ;" +
                    "    -fx-font-size: 10;" +
                    "    -fx-text-fill: white;");
            CustomerOderTm tm =new CustomerOderTm(
                    String.valueOf(cmbitemId.getValue()),
                    txtItemName.getText(),
                    txtItemPrice.getText(),
                    txtQTY.getText(),
                    String.valueOf(Double.parseDouble(txtQTY.getText())*Double.parseDouble(txtItemPrice.getText())),
                    button
            );
            button.setOnAction(event -> {
                oderTms.remove(tm);
                tblCart.refresh();
            });
            oderTms.add(tm);
            tblCart.refresh();
            setTotal();
            txtItemPrice.setText("");
            txtItemName.setText("");
            txtItemQTy.setText("");
            txtQTY.setText("");
            cmbitemId.getItems().clear();
            setItemId();
        }else {
            txtQTY.setText("");
            txtQTY.requestFocus();
        }


    }

    private void setTotal() {
        double total=0;
        for (int i = 0; i < oderTms.size(); i++) {
            total+=Double.parseDouble(oderTms.get(i).getQty())*Double.parseDouble(oderTms.get(i).getPrice());
        }
        txtTotal.setText(String.valueOf(total));
    }

    public void payOnAction(ActionEvent actionEvent) {
        try {
            if (OrderController.setOrder(
                    new Order(
                         next() ,
                            DateTimeUtil.dateNow(),
                            String.valueOf(cmbCrustId.getValue())
                    ),oderTms
            )){
                tblCart.getItems().clear();
                oderTms.clear();
                cmbCrustId.getItems().clear();
                cmbitemId.getItems().clear();
                setCustomer();
                setItemId();
                txtTotal.setText("");
                txtCustomerName.setText("");
                txtItemPrice.setText("");
                txtItemName.setText("");
                txtItemQTy.setText("");
                txtQTY.setText("");
                new Alert(Alert.AlertType.CONFIRMATION,"Ok").show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private String next() {
        String id=null;
        try {
            ResultSet set=OrderController.getAllId();
            while (set.next()){
                id=set.getString(1);
            }
            try{
                String[] O = id.split("O");
                int nextId = Integer.parseInt(O[1]);
                nextId++;
                return "O" + nextId;
            }catch (NullPointerException e){
                return "O1";
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void custIdOnActioon(ActionEvent actionEvent) {
        try {
            ResultSet set=CustomerController.getAllForId(String.valueOf(cmbCrustId.getValue()));
            if (set.next()){
                txtCustomerName.setText(set.getString(2));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void itemidOnAction(ActionEvent actionEvent) {
        try {
            ResultSet set=ItemController.getAllForid(String.valueOf(cmbitemId.getValue()));
            if (set.next()){
                txtItemQTy.setText(set.getString(1));
                txtItemName.setText(set.getString(2));
                txtItemPrice.setText(set.getString(3));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colCode.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotalCost.setCellValueFactory(new PropertyValueFactory<>("total"));
        colButton.setCellValueFactory(new PropertyValueFactory<>("button"));
        tblCart.setItems(oderTms);

        setCustomer();
        setItemId();
    }

    private void setItemId() {
        ArrayList<String>ids=new ArrayList<>();
        try {
            ResultSet set= ItemController.getAll();
            while (set.next()){
                ids.add(set.getString(1));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }cmbitemId.getItems().addAll(ids);
    }

    private void setCustomer() {
        ArrayList<String> ids = new ArrayList<>();
        try {
            ResultSet set = CustomerController.getAll();
            while (set.next()) {
                ids.add(set.getString(1));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        cmbCrustId.getItems().addAll(ids);
    }

}
