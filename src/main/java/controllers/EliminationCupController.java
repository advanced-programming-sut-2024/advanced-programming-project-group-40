package controllers;

import Server.ClientHandler;
import Server.Messages.Client.EliminationMessage;
import Server.Messages.MessageSubType;
import Server.Messages.ServerMessages;
import Server.Services.EliminationCup.Match;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.Game;

import java.util.ArrayList;

public class EliminationCupController {

    public static void sendEliminationRequest(MessageSubType subType) {
        EliminationMessage eliminationMessage = new EliminationMessage(Game.getLoggedInUser().getUsername(), subType, -1);
        ServerMessages serverMessages = ClientHandler.client.elimination(eliminationMessage);
    }

    public static Match getMatch(int number) {
        EliminationMessage eliminationMessage = new EliminationMessage(Game.getLoggedInUser().getUsername(), MessageSubType.GET_MATCH_ELIMINATION, number);
        ServerMessages serverMessages = ClientHandler.client.elimination(eliminationMessage);
        String result = serverMessages.getAdditionalInfo();
        boolean success = serverMessages.wasSuccessfull();
        if (!success)
            return new Match("", "", number, -1);
        Gson gson = new Gson();
        return gson.fromJson(result, new TypeToken<Match>() {
        }.getType());
    }

    public static boolean isStarted() {
        EliminationMessage eliminationMessage = new EliminationMessage(Game.getLoggedInUser().getUsername(), MessageSubType.GET_MATCH_ELIMINATION, -1);
        ServerMessages serverMessages = ClientHandler.client.elimination(eliminationMessage);
        String result = serverMessages.getAdditionalInfo();
        boolean success = serverMessages.wasSuccessfull();
        return success;
    }
    public static boolean checkTeams(ArrayList<String> teams){
        return teams.size() == 8;
    }
}
