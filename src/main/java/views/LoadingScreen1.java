package views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import Server.GameServer;

import java.net.URL;

public class LoadingScreen1 extends PlayMenu {
    @Override
    public void start(Stage stage) throws Exception {
        GameServer.stage = stage;
        URL url = getClass().getResource("/FXML/LoadingScreen.fxml");
        assert url != null;
        Pane root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
