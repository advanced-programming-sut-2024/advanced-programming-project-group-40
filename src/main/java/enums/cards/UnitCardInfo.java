package enums.cards;

import enums.Ability;
import enums.Factions;
import enums.Unit;
import models.cards.UnitCard;

public enum UnitCardInfo {
    TEST("testCard",0,0,Unit.SIEGE,"kys","test",Factions.Test,Ability.Test),
    TEST2("test2Card",1,0,Unit.RANGED,"kys","test",Factions.Test,Ability.Test),
    TEST3("test3Card",2,0,Unit.CLOSE_COMBAT,"kys","test",Factions.Test,Ability.Test),
    TEST4("test4Card",3,0,Unit.RANGED,"kys","test",Factions.Test,Ability.Test),
    TEST5("test5Card",4,0,Unit.SIEGE,"kys","test",Factions.Test,Ability.Test),
    TEST6("test6Card",5,0,Unit.CLOSE_COMBAT,"kys","test",Factions.Test,Ability.Test),
    TEST7("test7Card",6,0,Unit.RANGED,"kys","test",Factions.Test,Ability.Test),
    TEST8("test8Card",7,0,Unit.CLOSE_COMBAT,"kys","test",Factions.Test,Ability.Test),
    TEST9("test9Card",8,0,Unit.RANGED,"kys","test",Factions.Test,Ability.Test),
    TEST10("test10Card",9,0,Unit.CLOSE_COMBAT,"kys","test",Factions.Test,Ability.Test),
    TEST11("test11Card",10,0,Unit.SIEGE,"kys","test",Factions.Test,Ability.Test),
    TEST12("test12Card",11,0,Unit.SIEGE,"kys","test",Factions.Test,Ability.Test),

    BEAR("bear",8 ,0 ,Unit.CLOSE_COMBAT ,"empty" ,"empty" ,Factions.Test ,Ability.Test ),
    SPONGE_BOB("sponge bob",8,0,Unit.All,
            "I'm ready i'm ready","believe me when i say your goofy ass isn't ready for sponge bob",
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
