package lk.ijse.TheFlora.conroller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import lk.ijse.TheFlora.util.CrudUtil;
import lk.ijse.TheFlora.util.Navigation;
import lk.ijse.TheFlora.util.RegexUtil;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginConroller {

    public JFXTextField txtUserName;
    public JFXTextField txtPassword;
    public JFXButton btn;
    public Label lblWarning;

    public void btnLoginOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM User WHERE userName=? AND password=?", txtUserName.getText(), txtPassword.getText());
            if (resultSet.next()) {
                String role = resultSet.getString(4);
                if (role.equals("Admin")) {
                    Navigation.swatchNavigation("AdminDashboardForm.fxml", actionEvent);
                }
                if (role.equals("User")) {
                    Navigation.swatchNavigation("UserDashboardForm.fxml", actionEvent);
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "Incorrect Password").show();
                //     lblWarning.setText("Incorrect Password");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void User(KeyEvent keyEvent) {
        RegexUtil.regex(btn, txtUserName, txtUserName.getText(), "\\b([a-z]|[A-Z])+", "black");
    }

    public void Password(KeyEvent keyEvent) {
        RegexUtil.regex(btn, txtPassword, txtPassword.getText(), "^(?=.{8,})(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$", "black");
    }


}
