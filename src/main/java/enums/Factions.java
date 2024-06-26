package enums;

import models.cards.Card;

public enum Factions {
    Test("test"),
    NORTHERN_REALMS("Realms Northern"),
    MONSTERS("Monsters"),
    SKELLIGE("Skellige"),
    NEUTRAL("neutral"),
    SCOIATAEL("scoiatael"),
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
