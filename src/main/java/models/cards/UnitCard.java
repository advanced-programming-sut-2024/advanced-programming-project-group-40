package models.cards;

import enums.Ability;
import enums.Unit;
import enums.cards.UnitCardInfo;

public class UnitCard extends Card{
    private final int constantPower;
    private int showingPower;
    private final Unit unit;
    private final Ability ability;
    private UnitCardInfo unitCardInfo;
    public UnitCard(UnitCardInfo unitCardInfo) {

        super(unitCardInfo.name, unitCardInfo.planeImage, unitCardInfo.cardImage, unitCardInfo.maxCapacity, unitCardInfo.faction);
        this.constantPower = unitCardInfo.power;
        this.showingPower = constantPower;
        this.unit = unitCardInfo.unit;
        this.unitCardInfo = unitCardInfo;
        this.ability = unitCardInfo.ability;
    }

    public UnitCardInfo getUnitCardInfo() {
        return unitCardInfo;
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
