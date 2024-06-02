package models.actions;

import models.MatchTable;

public abstract class UnitCardActions {
    private static void commandersHorn() {
    }

    private static void decoy() {
    }

    private static void medic() {
    }

    private static void moralBoost() {
    }

    private static void muster() {
    }

    private static void spy() {
    }

    private static void tightBond() {
    }

    private static void scorch() {
    }
    private static void scorchAll() {
    }

    private static void berserker() {
    }

    private static void mardroeme() {
    }
    private static void transformers() {
    }

    private static MatchTable matchTable;

    public static void doActionByName(String name, MatchTable currentMatchTable) {
        matchTable = currentMatchTable;
    }
}
