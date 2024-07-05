package models.cards;

import enums.Ability;
import enums.Factions;
import enums.cards.LeaderInfo;

public class Leader extends Card {
    private LeaderInfo leaderInfo;

    public Leader(LeaderInfo leaderInfo) {
        super(leaderInfo.name, leaderInfo.planeImage, leaderInfo.cardImage, 1, leaderInfo.faction);
        this.leaderInfo = leaderInfo;
    }

    public LeaderInfo getLeaderInfo() {
        return leaderInfo;
    }

    public static Leader getLeaderByName(String leaderName) {
        for (LeaderInfo leader : LeaderInfo.values()) {
            if (leader.name.equals(leaderName))
                return new Leader(leader);
        }
        return null;
    }
}
