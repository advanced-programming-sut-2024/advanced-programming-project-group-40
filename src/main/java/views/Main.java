package views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Server.GameServer;

import java.net.URL;

public class Main extends Application {
    //this is a test class only and main extending application is not needed

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        GameServer.stage = stage;
        URL url = Main.class.getResource("/FXML/LoginMenuFXML.fxml");
        assert url != null;
        Scene scene = new Scene(FXMLLoader.load(url));
        stage.setScene(scene);
        stage.show();
    }
}