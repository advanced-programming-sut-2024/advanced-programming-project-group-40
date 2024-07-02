package enums;

import enums.cards.LeaderInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public enum Factions {
    MONSTERS("monsters", "/Assets/Factions/faction_monsters.jpg","/Assets/Icons/Deck_Shield/deck_shield_monsters.png"),
    SCOIATAEL("scoiatael", "/Assets/Factions/faction_scoiatael.jpg","/Assets/Icons/Deck_Shield/deck_shield_scoiatael.png"),
    NILFGAARD("nilfgaard", "/Assets/Factions/faction_nilfgaard.jpg","/Assets/Icons/Deck_Shield/deck_shield_nilfgaard.png"),
    NORTHERN_REALMS("realms", "/Assets/Factions/faction_realms.jpg","/Assets/Icons/Deck_Shield/deck_shield_realms.png"),
    SKELLIGE("skellige", "/Assets/Factions/faction_skellige.jpg","/Assets/Icons/Deck_Shield/deck_shield_skellige.png"),
    NEUTRAL("neutral", "/Assets/Factions/faction_neutral.png",""),
    ;
    public final String name;
    public final String planeImages;
    public final String iconAddress;


    Factions(String name, String planeImages, String iconAddress) {
        this.name = name;
        this.planeImages = planeImages;
        this.iconAddress = iconAddress;
    }

    @Override
    public String toString() {
        return name;
    }

    public static ArrayList<String> getFactionsNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Factions factions : Factions.values()) {
            if (factions.name.equals("neutral"))
                continue;
            names.add(factions.name);
        }
        return names;
    }
    public static Factions toFaction(String name) {
        for (Factions factions : Factions.values()) {
            if (Objects.equals(factions.name, name))
                return factions;
        }
        return null;
    }
}
