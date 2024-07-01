package models.cards;

import enums.Ability;
import enums.Factions;
import enums.Unit;
import enums.cards.UnitCardInfo;

public class UnitCard extends Card{
    private final int constantPower;
    private int showingPower;
    private final Unit unit;
    private final Factions faction;
    private final Ability ability;
    public UnitCard(UnitCardInfo unitCardInfo) {
        super(unitCardInfo.name, unitCardInfo.planeImage, unitCardInfo.cardImage, unitCardInfo.maxCapacity);
        this.constantPower = unitCardInfo.power;
        this.showingPower = constantPower;
        this.unit = unitCardInfo.unit;
        this.faction = unitCardInfo.faction;
        this.ability = unitCardInfo.ability;
    }

    public int getConstantPower() {
        return constantPower;
    }

    public int getMaxCapacity() {
        return maxCapacity;
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
