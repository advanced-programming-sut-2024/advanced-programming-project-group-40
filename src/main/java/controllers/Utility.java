package controllers;

import Server.ClientHandler;
import Server.Messages.Client.GetUserMessage;
import Server.Messages.ServerMessages;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.User;

public class Utility {
    public static User getUser(String username) {
        GetUserMessage getUserMessage = new GetUserMessage(username);
        ServerMessages serverMessages = ClientHandler.client.getUser(getUserMessage);
        String result = serverMessages.getAdditionalInfo();
        boolean success = serverMessages.wasSuccessfull();
        if (!success)
            return null;
        Gson gson = new Gson();
        User user = gson.fromJson(result, new TypeToken<User>() {
        }.getType());
        return user;
    }
}
