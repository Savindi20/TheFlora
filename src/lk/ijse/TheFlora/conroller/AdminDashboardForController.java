package lk.ijse.TheFlora.conroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.TheFlora.util.CrudUtil;
import lk.ijse.TheFlora.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminDashboardForController extends CrudUtil implements Initializable {
    private Node node;
    public AnchorPane mainPane;
    public Label txtSelling;
    public Label txtEmployee;
    public Label txtSupplier;
    public Label txtStock;

    @FXML
    private LineChart<?, ?> lineChart;
    @FXML
    private PieChart pieChart;

    @Override
    public void initialize(URL location, ResourceBundle resource) {

        iniLineChart();
        iniPieChart();
        EmployeeLode();
        setSupplierCount();
        setStockCount();
        setSellingCount();

    }

    private void setSellingCount() {
    }

    private void setStockCount() {
    }

    private void setSupplierCount() {
    }

    private void EmployeeLode() {
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

    public void IncomeOnAction(ActionEvent actionEvent) {
    }

    public void ManageSuppliersOnAction(ActionEvent actionEvent) throws IOException {
        Node node = FXMLLoader.load(getClass().getResource("../view/SupplierForm.fxml"));
        setNode(node);
    }

    public void CustomizeItemOnAction(ActionEvent actionEvent) throws IOException {
        Node node = FXMLLoader.load(getClass().getResource("../view/ItemForm.fxml"));
        setNode(node);
    }

    public void ManageEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        Node node = FXMLLoader.load(getClass().getResource("../view/EmployeeForm.fxml"));
        setNode(node);
    }

    public void ManageStockOnAction(ActionEvent actionEvent) throws IOException {
        Node node = FXMLLoader.load(getClass().getResource("../view/StockDetailForm.fxml"));
        setNode(node);
    }

    public void LogOutOnAction(ActionEvent actionEvent) throws IOException {
       /* Parent root= FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();*/
        Navigation.swatchNavigation("Login.fxml",actionEvent);
    }
}
