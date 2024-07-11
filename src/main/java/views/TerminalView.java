package views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Game;

import java.util.Objects;

public class TerminalView extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = FXMLLoader.load(Objects.requireNonNull(LoginMenu.class.getResource("/FXML/Terminal.fxml")));
        stage.setScene(new Scene(pane));
        stage.show();
    }
}
