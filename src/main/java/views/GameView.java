package views;

import enums.Menu;
import models.Game;

import java.util.Scanner;

public class GameView{
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        do {
            Game.getCurrentMenu().checkCommand(scanner);
        } while (Game.getCurrentMenu() != Menu.Exit);
    }
}
