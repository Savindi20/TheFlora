package lk.ijse.TheFlora.conroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.TheFlora.util.CrudUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserDashboardFormConroller extends CrudUtil implements Initializable {
    public AnchorPane mainPane;
    public Label txtCustomer;
    public Label txtOrder;
    public Label txtPayment;
    public Label txtStock;
    public Label txtDelivery;
    private Node node;

    @FXML
    private LineChart<?, ?> lineChart;
    @FXML
    private PieChart pieChart;

    @Override
    public void initialize(URL location, ResourceBundle resource) {

        iniLineChart();
        iniPieChart();
        CustomerLode();
        OrderCount();
        PaymentCount();
        DeliveryCount();

    }

    private void CustomerLode() {
    }

    private void OrderCount() {
    }

    private void PaymentCount() {
    }

    private void DeliveryCount() {
    }

    private void iniLineChart() {

        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data("Monday", 8));
        series.getData().add(new XYChart.Data("Tuesday", 12));
        series.getData().add(new XYChart.Data("Wednesday", 5));
        series.getData().add(new XYChart.Data("Thursday", 20));
        series.getData().add(new XYChart.Data("Friday", 45));
        series.getData().add(new XYChart.Data("Saturday", 12));
        series.getData().add(new XYChart.Data("Sunday", 8));


        lineChart.getData().setAll(series);
        //   lineChart.lookup("chart-plot-background").setStyle("-fx-border-color: transparent");

        series.getNode().setStyle("-fx-stroke: #D07000");
    }







    private void iniPieChart() {



        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Active sales", 40),
                new PieChart.Data("Target sales", 60)
        );
        pieChart.setData(pieChartData);
    }







    private void setNode(Node node) {
        mainPane.getChildren().clear();
        mainPane.getChildren().add((Node) node);
    }

    public void ManageCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Node node = FXMLLoader.load(getClass().getResource("../view/CustomerForm.fxml"));
        setNode(node);
    }

    public void MakeOrderOnAction(ActionEvent actionEvent) throws IOException {
        Node node = FXMLLoader.load(getClass().getResource("../view/OrderForm.fxml"));
        setNode(node);
    }

    public void ManagePaymentOnAction(ActionEvent actionEvent) throws IOException {
        Node node = FXMLLoader.load(getClass().getResource("../view/PaymentForm.fxml"));
        setNode(node);
    }

  /*  public void ManageStockOnAction(ActionEvent actionEvent) throws IOException {
        Node node = FXMLLoader.load(getClass().getResource("../view/StockForm.fxml"));
        setNode(node);
    }*/

    public void DeliveryDetailsOnAction(ActionEvent actionEvent) throws IOException {
        Node node = FXMLLoader.load(getClass().getResource("../view/DeliveryForm.fxml"));
        setNode(node);
    }

    public void OrderDetailOnAction(ActionEvent actionEvent) throws IOException {
        Node node = FXMLLoader.load(getClass().getResource("../view/OrderDetailForm.fxml"));
        setNode(node);
    }
    public void LogOutOnAction(ActionEvent actionEvent) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void StockDetailOnAction(ActionEvent actionEvent) throws IOException {
        Node node = FXMLLoader.load(getClass().getResource("../view/StockForm.fxml"));
        setNode(node);
    }
}
