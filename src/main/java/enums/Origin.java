package enums;

import models.cards.Card;

public enum Origin {
    FIRSTPLAYER_CLOSECOMBAT(),
    FIRSTPLAYER_RANGED(),
    FIRSTPLAYER_SIEGE(),
    SECONDPLAYER_CLOSECOMBAT(),
    SECONDPLAYER_RANGED(),
    SECONDPLAYER_SIEGE(),
    FIRSTPLAYER_INPLAY(),
    SECONDPLAYER_INPLAY(),
    FIRSTPLAYER_DECK(),
    SECONDPLAYER_DECK(),
    FIRSTPLATER_DEAD(),
    SECONDPLAYER_DEAD(),
    WEATHER(),
    FIRSTPLAYER_AGILE(),
    SECONDPLAYER_AGILE(),
    FIRSTPLAYER_ALL(),

    NULL();

    public boolean isSubOrigin(Origin destination) {
        if (this == destination) return true;
        if (destination == Origin.FIRSTPLAYER_AGILE &&
                (this == Origin.FIRSTPLAYER_CLOSECOMBAT || this == Origin.FIRSTPLAYER_RANGED)) {
            return true;
        }
        if (destination == Origin.SECONDPLAYER_AGILE &&
                (this == Origin.SECONDPLAYER_CLOSECOMBAT || this == Origin.SECONDPLAYER_RANGED)) {
            return true;
        }
        if (destination == Origin.FIRSTPLAYER_ALL &&
                (this == Origin.FIRSTPLAYER_CLOSECOMBAT ||
                        this == Origin.FIRSTPLAYER_RANGED ||
                        this == Origin.FIRSTPLAYER_SIEGE)) {
            return true;
        }
        return false;
    }
}
