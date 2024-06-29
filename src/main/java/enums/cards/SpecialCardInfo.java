package enums.cards;

import enums.Factions;
import models.cards.SpecialCard;

public enum SpecialCardInfo {
    Mardoeme("Mardroeme", 3),
    BITING_FROST("Biting Frost", 3),
    IMPENETRABLE_FOG("Impenetrable fog", 3),
    TORRENTIAL_RAIN("Torrential Rain", 3),
    SKELLIGE_STORM("Skellige Storm", 3),
    CLEAR_WEATHER("Clear Weather", 3),
    SCORCH("Scorch", 3),
    COMMANDERS_HORN("Commander’s horn", 3),
    DECOY("Decoy", 3);
    ;
    public final String name;
    public final String planeImage;
    public final String cardImage;
    public final int maxCapacity;
    SpecialCardInfo(String name, String planeImage, String cardImage, int maxCapacity) {
        this.name = name;
        this.planeImage = planeImage;
        this.cardImage = cardImage;
        this.maxCapacity = maxCapacity;
    }
    public static SpecialCard getSpecialCardByName(String name){
        return null;
    }
}
