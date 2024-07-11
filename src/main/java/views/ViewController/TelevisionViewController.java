package views.ViewController;

import Server.ClientHandler;
import Server.Messages.Client.RequestMessage;
import Server.Messages.MessageSubType;
import Server.Messages.ServerMessages;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import controllers.Utilities;
import enums.AlertInfo.AlertHeader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.AlertMaker;
import models.Game;
import views.MainMenu;
import views.TerminalView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TelevisionViewController {
    public VBox player1;
    public VBox player2;
    public VBox watch;


    public void initialize() {
        RequestMessage requestMessage = new RequestMessage("keyName", "subType", MessageSubType.GET_ALL_GAMES_IN_PLAY);
        ServerMessages serverMessages = ClientHandler.client.request(requestMessage);
        String result = serverMessages.getAdditionalInfo();
        boolean success = serverMessages.wasSuccessfull();
        if (success) {
            Gson gson = new Gson();
            HashMap<String, String> list = gson.fromJson(result, new TypeToken<HashMap<String, String>>() {
            }.getType());

            ArrayList<String> player1 = new ArrayList<String>();
            ArrayList<String> player2 = new ArrayList<>();
            for (Map.Entry<String, String> entry : list.entrySet()) {
                player1.add(entry.getValue());
                player2.add(entry.getKey());
            }


            for (int i = 0; i < player1.size(); i++) {
                Label user1 = new Label(player1.get(i));
                user1.setPrefHeight(16.0);
                user1.setPrefWidth(84.0);
                user1.setTranslateX(5.0);
                this.player1.getChildren().add(user1);


                Label user2 = new Label(player2.get(i));
                user2.setPrefHeight(16.0);
                user2.setPrefWidth(84.0);
                user2.setTranslateX(5.0);
                this.player2.getChildren().add(user2);


                if (Utilities.isMatchTablePublic(MessageSubType.IS_MATCH_PUBLIC)) {
                    Label isPublic = new Label(player2.get(i));
                    isPublic.setPrefHeight(16.0);
                    isPublic.setPrefWidth(84.0);
                    isPublic.setTranslateX(5.0);
                    isPublic.setOnMouseClicked(event -> watchGame(isPublic));
                    this.watch.getChildren().add(isPublic);
                }
            }
        }

    }

    public void watchGame(Label label){
        AlertMaker alertMaker = new AlertMaker(Alert.AlertType.CONFIRMATION, AlertHeader.PROFILE_MENU.toString(), "now you can watch the game");
        alertMaker.showAlert();
    }

    public void openTerminal(MouseEvent mouseEvent) {
        try {
            new TerminalView().start(new Stage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void goToLoginMenu(MouseEvent mouseEvent) {
        try {
            new MainMenu().start(Game.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
