package enums.cards;

import enums.Ability;
import enums.Factions;
import enums.Unit;
import models.cards.UnitCard;

public enum UnitCardInfo {
    TEST("testCard",0,0,Unit.Test,"kys","test",Factions.Test,Ability.Test),
    TEST2("test2Card",0,0,Unit.Test,"kys","test",Factions.Test,Ability.Test),
    BEAR("bear",8 ,0 ,Unit.Test ,"empty" ,"empty" ,Factions.Test ,Ability.Test ),
    SPONGE_BOB("sponge bob",8,0,Unit.All,
            "I\'m ready i\'m ready","believe me when i say your goofy ass is'nt ready for sponge bob",
            Factions.NEUTRAL,Ability.Test);
    public final String name;
    public final int power;
    public final int maxCapacity;
    public final Unit unit;
    public final String boast;
    public final String detail;
    public final Factions faction;
    public final Ability ability;

    UnitCardInfo(String name, int power, int maxCapacity, Unit unit, String boast, String detail, Factions faction, Ability ability) {
        this.name = name;
        this.power = power;
        this.maxCapacity = maxCapacity;
        this.unit = unit;
        this.boast = boast;
        this.detail = detail;
        this.faction = faction;
        this.ability = ability;
    }

    public static UnitCard getRegularCardByName(String name) {
        return null;
    }

}
