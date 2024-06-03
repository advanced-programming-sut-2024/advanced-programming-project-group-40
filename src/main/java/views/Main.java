package views;

import enums.cards.UnitCardInfo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.cards.Card;
import models.cards.UnitCard;

import java.net.URL;

public class Main extends Application {
    //this is a test class only and main extending application is not needed

    public static void main(String[] args) {
        launch(args);
        //GameView.run();
    }

    @Override
    public void start(Stage stage) throws Exception {
        URL url = Main.class.getResource("/FXML/GameBoard.fxml");
        Scene scene = new Scene(FXMLLoader.load(url));
        stage.setScene(scene);
        stage.show();
    }
}