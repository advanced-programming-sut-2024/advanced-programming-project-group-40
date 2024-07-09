package models.Chat;

public class ReplyData {
    public boolean isReply;
    public String userName;

    public ReplyData(boolean isReply, String userName) {
        this.isReply = isReply;
        this.userName = userName;
    }

    public boolean isReply() {
        return isReply;
    }

    public String getUserName() {
        return userName;
    }
}
