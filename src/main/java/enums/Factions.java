package enums;

import models.cards.Card;

public enum Factions {
    Test("test")
    ;
    private final String name;

    Factions(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return name;
    }
}