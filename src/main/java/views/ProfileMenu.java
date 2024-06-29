package views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Scanner;

public class ProfileMenu extends PlayMenu {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        MainMenu.stage = stage;
        Pane pane = FXMLLoader.load(MainMenu.class.getResource("/FXML/MainMenuFXML.fxml"));
        stage.setScene(new Scene(pane));
        stage.show();
    }
}
