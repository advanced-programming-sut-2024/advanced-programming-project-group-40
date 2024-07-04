package models.cards;

import enums.Ability;
import enums.Factions;
import enums.cards.LeaderInfo;

public class Leader extends Card {

    public Leader(LeaderInfo leaderInfo) {
        super(leaderInfo.name, leaderInfo.planeImage, leaderInfo.cardImage, 1, leaderInfo.faction);
    }
    public String getImageLink(){
        return super.planeImage;
    }
    public Factions getFaction() {
        return faction;

    public static Leader getLeaderByName(String leaderName) {
        for (LeaderInfo leader : LeaderInfo.values()) {
            if (leader.name.equals(leaderName))
                return new Leader(leader);
        }
        return null;
    }
}
