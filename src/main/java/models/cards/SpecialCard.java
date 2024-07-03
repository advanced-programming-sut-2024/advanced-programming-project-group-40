package models.cards;

import enums.cards.SpecialCardInfo;
import models.Game;

public class SpecialCard extends Card{
    private final int maxCapacity;
    private final long rand;

    public SpecialCard(SpecialCardInfo specialCardInfo) {
        super(specialCardInfo.name, specialCardInfo.planeImage, specialCardInfo.cardImage);
        this.rand = Game.random.nextLong(0,1000000);

        this.maxCapacity = specialCardInfo.maxCapacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }
}
