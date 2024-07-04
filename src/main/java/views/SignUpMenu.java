package views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Objects;

public class SignUpMenu extends PlayMenu{

    @Override
    public void start(Stage stage) throws Exception {
        SignUpMenu.stage = stage;
        Pane pane = FXMLLoader.load(Objects.requireNonNull(LoginMenu.class.getResource("/FXML/SignUpMenuFXML.fxml")));
        stage.setScene(new Scene(pane));
        stage.show();
    }
}
