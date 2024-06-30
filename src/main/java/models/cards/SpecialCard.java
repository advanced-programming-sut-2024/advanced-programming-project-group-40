package models.cards;

import enums.cards.SpecialCardInfo;

public class SpecialCard extends Card{

    public SpecialCard(SpecialCardInfo specialCardInfo) {
        super(specialCardInfo.name, specialCardInfo.planeImage, specialCardInfo.cardImage, specialCardInfo.maxCapacity);
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }
}
