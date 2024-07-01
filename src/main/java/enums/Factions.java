package enums;

import enums.cards.LeaderInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public enum Factions {
    MONSTERS("monsters", "/Assets/Factions/faction_monsters.jpg"),
    SCOIATAEL("scoiatael", "/Assets/Factions/faction_scoiatael.jpg"),
    NILFGAARD("nilfgaard", "/Assets/Factions/faction_nilfgaard.jpg"),
    NORTHERN_REALMS("realms", "/Assets/Factions/faction_realms.jpg"),
    SKELLIGE("skellige", "/Assets/Factions/faction_skellige.jpg"),
    NEUTRAL("neutral", "/Assets/Factions/faction_neutral.png"),
    ;
    public final String name;
    public final String planeImages;


    Factions(String name, String planeImages) {
        this.name = name;
        this.planeImages = planeImages;
    }

    @Override
    public String toString() {
        return name;
    }

    public static ArrayList<Factions> getImagesAddresses() {
        ArrayList<Factions> addresses = new ArrayList<>();
        addresses.addAll(Arrays.asList(Factions.values()));
        return addresses;
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
