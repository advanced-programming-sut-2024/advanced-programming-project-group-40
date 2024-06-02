package models.actions;

import models.MatchTable;

public abstract class SpecialCardsActions {
    private static MatchTable matchTable;

    private static void bitingFrost() {
    }

    private static void impenetrableFog() {
    }

    private static void torrentialRain() {
    }
    public static void doActionByName(String name, MatchTable currentMatchTable) {
        matchTable = currentMatchTable;
    }

}
