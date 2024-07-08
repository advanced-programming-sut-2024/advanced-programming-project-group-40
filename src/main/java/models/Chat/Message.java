package models.Chat;

public class Message {
    public String username;
    public String message;
    public ReplyData replyData;
    public String time;

    public Message(String username, String message, ReplyData replyData, String time) {
        this.username = username;
        this.message = message;
        this.replyData = replyData;
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }

    public ReplyData getReplyData() {
        return replyData;
    }

    public String getTime() {
        return time;
    }
}
