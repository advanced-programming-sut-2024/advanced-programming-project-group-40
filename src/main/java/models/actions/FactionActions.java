package models.actions;

import models.MatchTable;

public abstract class FactionActions {
    private static void monster() {
    }
    private static void nilfgaardianEmpire() {
    }
    private static void northernRealms() {
    }
    private static void scoiatael() {
    }
    private static void skellige() {
    }
    private static MatchTable matchTable;

    public static void doActionByName(String name, MatchTable currentMatchTable) {
        matchTable = currentMatchTable;
    }

}
