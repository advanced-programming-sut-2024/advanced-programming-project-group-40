package controllers.MenuController;

import enums.Origin;

public class SpectatorBoardController {

    public static void sendMessage(String s){
        //todo
    }

    public static void sendCommand(String s) {
        if (s.startsWith("message")) {
            sendMessage(s.substring(7));
        }
    }

}
