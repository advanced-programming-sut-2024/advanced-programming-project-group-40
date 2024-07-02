package models.cards;

import enums.Ability;
import enums.Factions;
import enums.cards.LeaderInfo;

public class Leader extends Card {

    public Leader(LeaderInfo leaderInfo) {
        super(leaderInfo.name, leaderInfo.planeImage, leaderInfo.cardImage, 1, leaderInfo.faction);
    }

}
