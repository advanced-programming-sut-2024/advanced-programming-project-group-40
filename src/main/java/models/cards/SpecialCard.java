package models.cards;

import enums.cards.SpecialCardInfo;
import models.Game;

public class SpecialCard extends Card{
    private final int maxCapacity;

    public SpecialCard(SpecialCardInfo specialCardInfo) {
        super(specialCardInfo.name, specialCardInfo.planeImage, specialCardInfo.cardImage);

        this.maxCapacity = specialCardInfo.maxCapacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }
}
