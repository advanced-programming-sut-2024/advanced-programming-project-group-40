package controllers.MenuController;

import Server.ClientHandler;
import Server.Messages.Client.EliminationMessage;
import Server.Messages.MessageSubType;
import Server.Messages.ServerMessages;
import models.Game;

public class EliminationCupController {

    public static void sendEliminationRequest(MessageSubType subType) {
        EliminationMessage eliminationMessage = new EliminationMessage(Game.getLoggedInUser().getUsername(), subType);
        ServerMessages serverMessages = ClientHandler.client.elimination(eliminationMessage);
    }
}
