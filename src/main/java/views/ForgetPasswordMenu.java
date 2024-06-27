package views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ForgetPasswordMenu extends PlayMenu {
    @Override
    public void start(Stage stage) throws Exception {
        ForgetPasswordMenu.stage = stage;
        Pane pane = FXMLLoader.load(LoginMenu.class.getResource("/FXML/ForgetPasswordMenuFXML.fxml"));
        stage.setScene(new Scene(pane));
        stage.show();
    }
}
