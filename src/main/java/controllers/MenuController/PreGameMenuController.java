package controllers.MenuController;

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
        if (isUsernameUnique(username))
            return new AlertMaker(Alert.AlertType.ERROR, AlertHeader.PRE_GAME.toString(), PreGameMenuMessages.INVALID_COMPETITOR_USERNAME.toString());
        User user = Game.getUserByName(username);
        System.out.println(user.getNumberOfUnitCards());
        System.out.println(user.getNumberOfSpecialCards());
        if (user.getNumberOfUnitCards() < 22)
            return new AlertMaker(Alert.AlertType.ERROR, AlertHeader.PRE_GAME.toString(), PreGameMenuMessages.NOT_ENOUGH_CARDS.toString());
        if (user.getNumberOfSpecialCards() > 10)
            return new AlertMaker(Alert.AlertType.ERROR, AlertHeader.PRE_GAME.toString(), PreGameMenuMessages.TOO_MUCH_SPECIAL_CARDS.toString());
        return new AlertMaker(Alert.AlertType.INFORMATION, AlertHeader.PRE_GAME.toString(), PreGameMenuMessages.GAME_STARTED.toString());
    }

}
