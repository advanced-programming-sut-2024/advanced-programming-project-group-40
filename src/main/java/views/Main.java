package views;

import enums.cards.UnitCardInfo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Game;
import models.actions.UnitCardActions;
import models.cards.Card;
import models.cards.UnitCard;

import java.net.URL;

public class Main extends Application {
    //this is a test class only and main extending application is not needed

    public static void main(String[] args) {
        Game.setAllCards();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        URL url = Main.class.getResource("/FXML/LoginMenuFXML.fxml");
        assert url != null;
        Scene scene = new Scene(FXMLLoader.load(url));
        stage.setScene(scene);
        stage.show();
    }
}