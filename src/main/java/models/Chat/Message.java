package models.Chat;

public record Message(String username, String message,ReplyData replyData,String time) {
}
