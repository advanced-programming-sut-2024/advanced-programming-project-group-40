package enums.cards;

import enums.Factions;
import models.cards.SpecialCard;

public enum SpecialCardInfo {
    ;
    public final String name;
    public final String boast;
    public final int maxCapacity;
    public final String detail;

    SpecialCardInfo(String name, String boast, int maxCapacity, String detail) {
        this.name = name;
        this.boast = boast;
        this.maxCapacity = maxCapacity;
        this.detail = detail;
    }
    public static SpecialCard getSpecialCardByName(String name){
        return null;
    }
}
