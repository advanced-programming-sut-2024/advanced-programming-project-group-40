package views;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Objects;


public class GameView extends PlayMenu{
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        GameView.stage = stage;
        Pane pane = FXMLLoader.load(Objects.requireNonNull(GameView.class.getResource("/FXML/GameBoard.fxml")));
        stage.setScene(new Scene(pane));
        stage.show();

    }
}
