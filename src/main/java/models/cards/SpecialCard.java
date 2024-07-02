package models.cards;

import enums.Factions;
import enums.cards.SpecialCardInfo;

public class SpecialCard extends Card{

    public SpecialCard(SpecialCardInfo specialCardInfo) {
        super(specialCardInfo.name, specialCardInfo.planeImage, specialCardInfo.cardImage, specialCardInfo.maxCapacity, Factions.NEUTRAL);
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }
}
