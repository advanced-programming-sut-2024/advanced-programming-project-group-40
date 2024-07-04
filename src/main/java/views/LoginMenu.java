package views;
import Server.Client;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Game;
import java.io.IOException;
import java.util.Objects;


public class LoginMenu extends PlayMenu {
    public static void main(String[] args) throws IOException {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
      //TODO
//        Game.setAllUsers(Objects.requireNonNull(DataSaver.loadUsers()));
        Game.stage = stage;
        Pane pane = FXMLLoader.load(Objects.requireNonNull(LoginMenu.class.getResource("/FXML/LoginMenuFXML.fxml")));
        stage.setScene(new Scene(pane));
        stage.show();
    }
}
