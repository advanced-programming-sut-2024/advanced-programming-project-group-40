package controllers.MenuController;

import Server.ClientHandler;
import Server.Messages.Client.StartGameMessages;
import Server.Messages.ServerMessages;
import controllers.Utilities;
import enums.AlertInfo.AlertHeader;
import enums.AlertInfo.messages.PreGameMenuMessages;
import javafx.scene.control.Alert;
import models.AlertMaker;
import models.Game;
import models.User;

public class PreGameMenuController extends UserInfoController {


    public static AlertMaker checkCompetitorData(String username) {

        if (Game.getLoggedInUser().getNumberOfUnitCards() < 22)
            return new AlertMaker(Alert.AlertType.ERROR, AlertHeader.PRE_GAME.toString(), PreGameMenuMessages.YOU_DO_NOT_HAVE_ENOUGH_CARDS.toString());
        User user = Utilities.getUser(username);
        if (user == null)
            return new AlertMaker(Alert.AlertType.ERROR, AlertHeader.PRE_GAME.toString(), PreGameMenuMessages.INVALID_COMPETITOR_USERNAME.toString());
        if (user.getNumberOfUnitCards() < 22)
            return new AlertMaker(Alert.AlertType.ERROR, AlertHeader.PRE_GAME.toString(), PreGameMenuMessages.NOT_ENOUGH_CARDS.toString());
        if (user.getNumberOfSpecialCards() > 10)
            return new AlertMaker(Alert.AlertType.ERROR, AlertHeader.PRE_GAME.toString(), PreGameMenuMessages.TOO_MUCH_SPECIAL_CARDS.toString());
        return new AlertMaker(Alert.AlertType.INFORMATION, AlertHeader.PRE_GAME.toString(), PreGameMenuMessages.GAME_STARTED.toString());
    }

    public static void startGame(String username) {
        StartGameMessages message = new StartGameMessages(username);
        ServerMessages serverMessages = ClientHandler.client.startGame(message);

    }
}
