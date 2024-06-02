package enums.cards;

import enums.Ability;
import enums.Factions;
import enums.Unit;
import models.cards.Hero;

public enum HeroInfo {
    ;
    public final String name;
    public final int power;
    public final Unit unit;
    public final String boast;
    public final String detail;
    public final Factions faction;
    public final Ability ability;

    HeroInfo(String name, int power, Unit unit, String boast, String detail, Factions faction, Ability ability) {
        this.name = name;
        this.power = power;
        this.unit = unit;
        this.boast = boast;
        this.detail = detail;
        this.faction = faction;
        this.ability = ability;
    }

    public static Hero getHeroCardByName(String name) {
        return null;
    }
}
