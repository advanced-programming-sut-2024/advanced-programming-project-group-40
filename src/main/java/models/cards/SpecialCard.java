package models.cards;

import enums.cards.SpecialCardInfo;

public class SpecialCard extends Card{
    private final int maxCapacity;

    public SpecialCard(SpecialCardInfo specialCardInfo) {
        super(specialCardInfo.name, specialCardInfo.boast, specialCardInfo.detail);
        this.maxCapacity = specialCardInfo.maxCapacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }
}
