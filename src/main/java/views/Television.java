package views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Objects;

public class Television extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = FXMLLoader.load(Objects.requireNonNull(LoginMenu.class.getResource("/FXML/TelevisionFXML.fxml")));
        stage.setScene(new Scene(pane));
        stage.show();
    }
}
