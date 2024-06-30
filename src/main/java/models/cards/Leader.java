package models.cards;

import enums.Ability;
import enums.Factions;
import enums.cards.LeaderInfo;

public class Leader extends Card {
    private final Factions faction;

    public Leader(LeaderInfo leaderInfo) {
        super(leaderInfo.name, leaderInfo.planeImage, leaderInfo.cardImage, 1);
        this.faction = leaderInfo.faction;
    }

    public Factions getFaction() {
        return faction;
    }
}
