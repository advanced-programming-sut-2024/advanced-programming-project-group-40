package views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SignUpMenu extends PlayMenu{

    public static Stage stage;
    @Override
    public void start(Stage stage) throws Exception {
        SignUpMenu.stage = stage;
        Pane pane = FXMLLoader.load(LoginMenu.class.getResource("/FXML/SignUpMenuController.fxml"));
        stage.setScene(new Scene(pane));
        stage.show();
    }
}
