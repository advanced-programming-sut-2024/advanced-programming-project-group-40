package controllers.MenuController;

import enums.Origin;

public class SpectatorBoardController {

    public static void sendMessage(String s){
    }

    public static void sendCommand(String s) {
        if (s.startsWith("message")) {
            sendMessage(s.substring(7));
        }
    }
    public static boolean isCommandValid(String command) {
        return command.startsWith("message");
    }

}
