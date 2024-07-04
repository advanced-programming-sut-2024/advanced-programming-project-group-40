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
    private final Ability ability;
    public Hero(HeroInfo heroInfo) {
        super(heroInfo.name, heroInfo.planeImage, heroInfo.cardImage, 1, heroInfo.faction);
        this.constantPower = heroInfo.power;
        this.showingPower = constantPower;
        this.unit = heroInfo.unit;
        this.ability = heroInfo.ability;
    }

    public int getConstantPower() {
        return constantPower;
    }

    public Unit getUnit() {
        return unit;
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
