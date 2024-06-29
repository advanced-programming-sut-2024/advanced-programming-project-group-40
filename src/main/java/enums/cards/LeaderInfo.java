package enums.cards;

import enums.Factions;
import models.cards.Leader;

public enum LeaderInfo {
    THE_SIEGEMASTER("The Siegemaster", Factions.NORTHERN_REALMS),
    THE_STEEL_FORGED("The Steel-Forged", Factions.NORTHERN_REALMS),
    KING_OF_TEMERIA("King of Temeria", Factions.NORTHERN_REALMS),
    LORD_COMMANDER_OF_THE_NORTH("Lord Commander of the North", Factions.NORTHERN_REALMS),
    SON_OF_MEDELL("Son of Medell", Factions.NORTHERN_REALMS),
    THE_WHITE_FLAME("The White Flame", Factions.NILFGAARD),
    HIS_IMPERIAL_MAJESTY("His Imperial Majesty", Factions.NILFGAARD),
    EMPEROR_OF_NILFGAARD("Emperor of Nilfgaard", Factions.NILFGAARD),
    THE_RELENTLESS("The Relentless", Factions.NILFGAARD),
    INVADER_OF_THE_NORTH("Invader of the North", Factions.NILFGAARD),
    BRINGER_OF_DEATH("Bringer of Death", Factions.MONSTERS),
    KING_OF_THE_WILD_HUNT("King of the wild Hunt", Factions.MONSTERS),
    DESTROYER_OF_WORLDS("Destroyer of Worlds", Factions.MONSTERS),
    COMMANDER_OF_THE_RED_RIDERS("Commander of the Red Riders", Factions.MONSTERS),
    THE_TREACHEROUS("The Treacherous", Factions.MONSTERS),
    QUEEN_OF_DOL_BLATHANNA("Queen of Dol Blathanna", Factions.SCOIATAEL),
    THE_BEAUTIFUL("The Beautiful", Factions.SCOIATAEL),
    DAISY_OF_THE_VALLEY("Daisy of the Valley", Factions.SCOIATAEL),
    PUREBLOOD_ELF("Pureblood Elf", Factions.SCOIATAEL),
    HOPE_OF_THE_AEN_SEIDHE("Hope of the Aen Seidhe", Factions.SCOIATAEL),
    CRACH_AN_CRAITE("Crach an Craite", Factions.SKELLIGE),
    KING_BRAN("King Bran", Factions.SKELLIGE);
    ;
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
    public static Leader getLeaderByName(String name){
        return null;
    }
}
