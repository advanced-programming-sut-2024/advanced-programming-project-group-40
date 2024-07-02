package enums.cards;

import enums.Factions;
import models.cards.Leader;

import java.util.ArrayList;
import java.util.Objects;

public enum LeaderInfo {
    THE_SIEGEMASTER("The Siegemaster", "/Assets/Cards/Plane/realms_foltest_silver.jpg", "/Assets/Cards/Detailed/realms_foltest_silver.jpg", Factions.NORTHERN_REALMS),
    THE_STEEL_FORGED("The Steel-Forged", "/Assets/Cards/Plane/realms_foltest_gold.jpg", "/Assets/Cards/Detailed/realms_foltest_gold.jpg", Factions.NORTHERN_REALMS),
    KING_OF_TEMERIA("King of Temeria", "/Assets/Cards/Plane/realms_foltest_copper.jpg", "/Assets/Cards/Detailed/realms_foltest_copper.jpg", Factions.NORTHERN_REALMS),
    LORD_COMMANDER_OF_THE_NORTH("Lord Commander of the North", "/Assets/Cards/Plane/realms_foltest_bronze.jpg", "/Assets/Cards/Detailed/realms_foltest_bronze.jpg", Factions.NORTHERN_REALMS),
    SON_OF_MEDELL("Son of Medell", "/Assets/Cards/Plane/realms_foltest_son_of_medell.jpg", "/Assets/Cards/Detailed/realms_foltest_son_of_medell.jpg", Factions.NORTHERN_REALMS),
    THE_WHITE_FLAME("The White Flame", "/Assets/Cards/Plane/nilfgaard_emhyr_silver.jpg", "/Assets/Cards/Detailed/nilfgaard_emhyr_silver.jpg", Factions.NILFGAARD),
    HIS_IMPERIAL_MAJESTY("His Imperial Majesty", "/Assets/Cards/Plane/nilfgaard_emhyr_copper.jpg", "/Assets/Cards/Detailed/nilfgaard_emhyr_copper.jpg", Factions.NILFGAARD),
    EMPEROR_OF_NILFGAARD("Emperor of NILFGAARD", "/Assets/Cards/Plane/nilfgaard_emhyr_bronze.jpg", "/Assets/Cards/Detailed/nilfgaard_emhyr_bronze.jpg", Factions.NILFGAARD),
    THE_RELENTLESS("The Relentless", "/Assets/Cards/Plane/nilfgaard_emhyr_gold.jpg", "/Assets/Cards/Detailed/nilfgaard_emhyr_gold.jpg", Factions.NILFGAARD),
    INVADER_OF_THE_NORTH("Invader of the North", "/Assets/Cards/Plane/nilfgaard_emhyr_invader_of_the_north.jpg", "/Assets/Cards/Detailed/nilfgaard_emhyr_invader_of_the_north.jpg", Factions.NILFGAARD),
    BRINGER_OF_DEATH("Bringer of Death", "/Assets/Cards/Plane/monsters_eredin_silver.jpg", "/Assets/Cards/Detailed/monsters_eredin_silver.jpg", Factions.MONSTERS),
    KING_OF_THE_WILD_HUNT("King of the wild Hunt", "/Assets/Cards/Plane/monsters_eredin_bronze.jpg", "/Assets/Cards/Detailed/monsters_eredin_bronze.jpg", Factions.MONSTERS),
    DESTROYER_OF_WORLDS("Destroyer of Worlds", "/Assets/Cards/Plane/monsters_eredin_gold.jpg", "/Assets/Cards/Detailed/monsters_eredin_gold.jpg", Factions.MONSTERS),
    COMMANDER_OF_THE_RED_RIDERS("Commander of the Red Riders", "/Assets/Cards/Plane/monsters_eredin_copper.jpg", "/Assets/Cards/Detailed/monsters_eredin_copper.jpg", Factions.MONSTERS),
    THE_TREACHEROUS("The Treacherous", "/Assets/Cards/Plane/monsters_eredin_the_treacherous.jpg", "/Assets/Cards/Detailed/monsters_eredin_the_treacherous.jpg", Factions.MONSTERS),
    QUEEN_OF_DOL_BLATHANNA("Queen of Dol Blathanna", "/Assets/Cards/Plane/scoiatael_francesca_silver.jpg", "/Assets/Cards/Detailed/scoiatael_francesca_silver.jpg", Factions.SCOIATAEL),
    THE_BEAUTIFUL("The Beautiful", "/Assets/Cards/Plane/scoiatael_francesca_gold.jpg", "/Assets/Cards/Detailed/scoiatael_francesca_gold.jpg", Factions.SCOIATAEL),
    DAISY_OF_THE_VALLEY("Daisy of the Valley", "/Assets/Cards/Plane/scoiatael_francesca_copper.jpg", "/Assets/Cards/Detailed/scoiatael_francesca_copper.jpg", Factions.SCOIATAEL),
    PUREBLOOD_ELF("Pureblood Elf", "/Assets/Cards/Plane/scoiatael_francesca_bronze.jpg", "/Assets/Cards/Detailed/scoiatael_francesca_bronze.jpg", Factions.SCOIATAEL),
    HOPE_OF_THE_AEN_SEIDHE("Hope of the Aen Seidhe", "/Assets/Cards/Plane/scoiatael_francesca_hope_of_the_aen_seidhe.jpg", "/Assets/Cards/Detailed/scoiatael_francesca_hope_of_the_aen_seidhe.jpg", Factions.SCOIATAEL),
    CRACH_AN_CRAITE("Crach an Craite", "/Assets/Cards/Plane/skellige_crach_an_craite.jpg", "/Assets/Cards/Detailed/skellige_crach_an_craite.jpg", Factions.SKELLIGE),
    KING_BRAN("King Bran", "/Assets/Cards/Plane/skellige_king_bran.jpg", "/Assets/Cards/Detailed/skellige_king_bran.jpg", Factions.SKELLIGE);;
    public final String name;

    public final String planeImage;
    public final String cardImage;
    public final Factions faction;

    LeaderInfo(String name, String planeImage, String cardImage, Factions faction) {
        this.name = name;
        this.planeImage = planeImage;
        this.cardImage = cardImage;
        this.faction = faction;
    }

    public static Leader getLeaderByName(String name) {
        return null;
    }

    public static ArrayList<String> getLeaderAddressesByFaction(Factions faction) {
        ArrayList<String> list = new ArrayList<String>();
        for (LeaderInfo leader : LeaderInfo.values()) {
            if (leader.faction == faction)
                list.add(leader.planeImage);
        }
        return list;
    }

    public static ArrayList<String> getLeaderNameByFaction(Factions faction) {
        ArrayList<String> list = new ArrayList<String>();
        for (LeaderInfo leader : LeaderInfo.values()) {
            if (leader.faction == faction)
                list.add(leader.name);
        }
        return list;
    }

    public static LeaderInfo toLeaderInfo(String name) {
        for (LeaderInfo info : LeaderInfo.values()) {
            if (info.name.equals(name))
                return info;
        }
        return null;
    }

}
