package controllers;

import Server.ClientHandler;
import Server.Messages.Client.GetListOfNamesMessage;
import Server.Messages.Client.GetUserMessage;
import Server.Messages.MessageSubType;
import Server.Messages.ServerMessages;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.User;

import java.util.ArrayList;

public class Utilities {
    public static User getUser(String username) {
        GetUserMessage getUserMessage = new GetUserMessage(username);
        ServerMessages serverMessages = ClientHandler.client.getUser(getUserMessage);
        String result = serverMessages.getAdditionalInfo();
        boolean success = serverMessages.wasSuccessfull();
        if (!success)
            return null;
        Gson gson = new Gson();
        return gson.fromJson(result, new TypeToken<User>() {
        }.getType());
    }

    public  static ArrayList<String> getListOfNames(String keyName, MessageSubType subType) {
        System.out.println(2);
        GetListOfNamesMessage getListOfNamesMessage = new GetListOfNamesMessage(keyName, subType);
        System.out.println(10);
        ServerMessages serverMessages = ClientHandler.client.getListOfNames(getListOfNamesMessage);
        System.out.println(11);
        String result = serverMessages.getAdditionalInfo();
        boolean success = serverMessages.wasSuccessfull();
        if (!success)
            return null;
        Gson gson = new Gson();
        System.out.println(4);
        return gson.fromJson(result, new TypeToken<ArrayList<String>>() {
        }.getType());
    }
}
