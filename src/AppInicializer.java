import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class AppInicializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL resource = getClass().getResource("/lk/ijse/TheFlora/view/Login.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("The Flora");
        //primaryStage.getIcons().add(new Image(""));
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();


    }


}
