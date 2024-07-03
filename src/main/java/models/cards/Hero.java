package models.cards;

import enums.Ability;
import enums.Factions;
import enums.Unit;
import enums.cards.HeroInfo;
import models.Game;

public class Hero extends Card {
    ;
    private final int constantPower;
    private int showingPower;
    private final Unit unit;
    private final Factions faction;
    private final Ability ability;
    public Hero(HeroInfo heroInfo) {
        super(heroInfo.name, heroInfo.planeImage, heroInfo.cardImage);
        this.constantPower = heroInfo.power;
        this.showingPower = constantPower;
        this.unit = heroInfo.unit;
        this.faction = heroInfo.faction;
        this.ability = heroInfo.ability;
    }

    public int getConstantPower() {
        return constantPower;
    }

    public Unit getUnit() {
        return unit;
    }

    public Factions getFaction() {
        return faction;
    }

    public Ability getAbility() {
        return ability;
    }

    public int getShowingPower() {
        return showingPower;
    }

    public void setShowingPower(int showingPower) {
        this.showingPower = showingPower;
    }
}
