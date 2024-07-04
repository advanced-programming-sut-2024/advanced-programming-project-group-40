package views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SecurityQuestionMenu extends PlayMenu {
    @Override
    public void start(Stage stage) throws Exception {
        SecurityQuestionMenu.stage = stage;
        Pane pane = FXMLLoader.load(LoginMenu.class.getResource("/FXML/SecurityQuestionFXML.fxml"));
        stage.setScene(new Scene(pane));
        stage.show();
    }
}
