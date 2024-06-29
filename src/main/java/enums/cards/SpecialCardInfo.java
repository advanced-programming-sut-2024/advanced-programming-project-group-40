package enums.cards;

import enums.Factions;
import models.cards.SpecialCard;

public enum SpecialCardInfo {
    Mardoeme("Mardroeme","/Assets/Cards/Plane/special_mardroeme.jpg", "/Assets/Cards/Detailed/special_mardroeme.jpg", 3),
    BITING_FROST("Biting Frost", "/Assets/Cards/Plane/weather_frost.jpg", "/Assets/Cards/Detailed/weather_frost.jpg",3),
    IMPENETRABLE_FOG("Impenetrable fog","/Assets/Cards/Plane/weather_fog.jpg", "/Assets/Cards/Detailed/weather_fog.jpg", 3),
    TORRENTIAL_RAIN("Torrential Rain", "/Assets/Cards/Plane/weather_rain.jpg", "/Assets/Cards/Detailed/weather_rain.jpg",3),
    SKELLIGE_STORM("Skellige Storm", "/Assets/Cards/Plane/weather_storm.jpg", "/Assets/Cards/Detailed/weather_storm.jpg",3),
    CLEAR_WEATHER("Clear Weather", "/Assets/Cards/Plane/weather_clear.jpg", "/Assets/Cards/Detailed/weather_clear.jpg",3),
    SCORCH("Scorch", "/Assets/Cards/Plane/special_scorch.jpg", "/Assets/Cards/Detailed/special_scorch.jpg",3),
    COMMANDERS_HORN("Commander’s horn", "/Assets/Cards/Plane/special_horn.jpg", "/Assets/Cards/Detailed/special_horn.jpg",3),
    DECOY("Decoy", "/Assets/Cards/Plane/special_decoy.jpg", "/Assets/Cards/Detailed/special_decoy.jpg",3);
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
