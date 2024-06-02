package models.cards;

import enums.Ability;
import enums.Factions;
import enums.cards.LeaderInfo;

public class Leader extends Card {
    private final Factions faction;

    public Leader(LeaderInfo leaderInfo) {
        super(leaderInfo.name, leaderInfo.boast, leaderInfo.detail);
        this.faction = leaderInfo.faction;
    }

    public Factions getFaction() {
        return faction;
    }
}
