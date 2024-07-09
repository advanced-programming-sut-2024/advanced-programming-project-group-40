package views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class WinMenu extends PlayMenu {
    public static void main(String[] args) throws IOException {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        WinMenu.stage = stage;
        Pane pane = FXMLLoader.load(Objects.requireNonNull(WinMenu.class.getResource("/FXML/WinMenu.fxml")));
        stage.setScene(new Scene(pane));
        stage.show();
    }
}
