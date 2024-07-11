package Server.Models;

import Server.Messages.Client.ClickedOnCardMessages;
import Server.Messages.MessageType;
import com.google.gson.*;
import enums.cards.CardInfo;
import enums.cards.UnitCardInfo;
import models.Game;
import models.User;

import java.lang.reflect.Type;

public class MessageAdapter implements JsonSerializer<ClickedOnCardMessages>, JsonDeserializer<ClickedOnCardMessages> {
    @Override
    public ClickedOnCardMessages deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        // Extract the values from the JSON object
        String token = jsonObject.get("token").getAsString();

        MessageType type = context.deserialize(jsonObject.get("type"), MessageType.class);
        String cardInfoClass = jsonObject.get("cardInfoClass").getAsString();

        Class<?> clazz;
        try {
            clazz = Class.forName(cardInfoClass);
        } catch (ClassNotFoundException e) {
            throw new JsonParseException("Unknown class: " + cardInfoClass, e);
        }

        CardInfo cardInfo = context.deserialize(jsonObject.get("cardInfo"), clazz);
        boolean isSelectable = jsonObject.get("isSelectable").getAsBoolean();
        String parentID = jsonObject.get("parentID").getAsString();

        ClickedOnCardMessages message = new ClickedOnCardMessages(cardInfo.toString(), isSelectable, parentID);
        message.setToken(token); // Set the token inherited from ClientMessages
        message.setType(type);
        // Set the type directly as it's a final field in the constructor
        // You might need to modify ClickedOnCardMessages to allow setting type if necessary

        return message;
    }

    @Override
    public JsonElement serialize(ClickedOnCardMessages messages, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();

        // Serialize the inherited properties
        jsonObject.addProperty("token", messages.getToken());
        jsonObject.add("type", context.serialize(messages.getType()));

        // Serialize the cardInfo object and its class name
        jsonObject.add("cardInfo", context.serialize(messages.getCardInfo()));
        jsonObject.addProperty("cardInfoClass", messages.getCardInfo().getClass().getName());
        jsonObject.addProperty("isSelectable", messages.isSelectable());
        jsonObject.addProperty("parentID", messages.getParentID());

        return jsonObject;
    }

    public static void main(String[] args) {
        User ass = new User("ass","a","a","a");
        Game.setLoggedInUser(ass);
        GsonBuilder a = new GsonBuilder();
        a.registerTypeAdapter(ClickedOnCardMessages.class,new MessageAdapter());
        Gson w = a.create();
        ClickedOnCardMessages q = new ClickedOnCardMessages(UnitCardInfo.ALBRICH.toString(),true,"bussy");
        String json = w.toJson(q);
        ClickedOnCardMessages ret = w.fromJson(json,ClickedOnCardMessages.class);
        System.out.println(ret.getType());
        System.out.println(ret.getToken());
        System.out.println(ret.getParentID());
        System.out.println(ret.getCardInfo());
    }
}
