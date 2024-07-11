package controllers.MenuController;

import Server.Messages.MessageSubType;

import controllers.Utilities;
import enums.AlertInfo.AlertHeader;
import enums.AlertInfo.messages.PreGameMenuMessages;
import javafx.scene.control.Alert;
import models.AlertMaker;
import models.Game;
import models.User;
import views.GameView;

public class PreGameMenuController extends UserInfoController {

    private static String specificUser = "";

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

    public static String getSpecificUser() {
        return specificUser;
    }

    public static void setSpecificUser(String specificUser) {
        PreGameMenuController.specificUser = specificUser;
    }

    public static void startGame(String username) {
        Utilities.sendRequest(Game.getLoggedInUser().getUsername(), username, MessageSubType.GAME_REQUEST);

    }

    public static boolean isCompetitorValid(User user) {
        return user.getNumberOfUnitCards() >= 22 && user.getNumberOfSpecialCards() <= 10;
    }

    public static void goToGameView() {
        try {
            new GameView().start(Game.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
