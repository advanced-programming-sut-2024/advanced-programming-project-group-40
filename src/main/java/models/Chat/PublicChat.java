package models.Chat;

import java.util.ArrayList;

public class PublicChat {
    private final ArrayList<Message> messages = new ArrayList<>();
    public PublicChat() {
    }

    public void addMessages(Message message) {
        messages.add(message);
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }
}
