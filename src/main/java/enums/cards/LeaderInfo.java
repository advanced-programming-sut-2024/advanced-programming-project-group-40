package enums.cards;

import enums.Factions;
import models.cards.Leader;

public enum LeaderInfo {
    ;
    public final String name;
    public final String boast;
    public final String detail;
    public final Factions faction;
    LeaderInfo(String name, String boast, String detail, Factions faction) {
        this.name = name;
        this.boast = boast;
        this.detail = detail;
        this.faction = faction;
    }
    public static Leader getLeaderByName(String name){
        return null;
    }
}
