package models.cards;

import enums.Factions;
import enums.cards.SpecialCardInfo;

public class SpecialCard extends Card{
private SpecialCardInfo specialCardInfo;
    public SpecialCard(SpecialCardInfo specialCardInfo) {

        super(specialCardInfo.name, specialCardInfo.planeImage, specialCardInfo.cardImage, specialCardInfo.maxCapacity, Factions.NEUTRAL);
        this.specialCardInfo = specialCardInfo;
    }

    public SpecialCardInfo getSpecialCardInfo() {
        return specialCardInfo;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }
}
