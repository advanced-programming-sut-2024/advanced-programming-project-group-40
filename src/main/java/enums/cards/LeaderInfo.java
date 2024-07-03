package enums.cards;

import enums.Factions;
import models.cards.Leader;

import java.util.ArrayList;

public enum LeaderInfo {
    THE_SIEGEMASTER("The Siegemaster", "/Assets/Cards/Plane/realms_foltest_silver.jpg", "/Assets/Cards/Detailed/realms_foltest_silver.jpg", Factions.NORTHERN_REALMS, "Pick an Impenetrable Fog card from your deck and play it instantly."),
    THE_STEEL_FORGED("The Steel-Forged", "/Assets/Cards/Plane/realms_foltest_gold.jpg", "/Assets/Cards/Detailed/realms_foltest_gold.jpg", Factions.NORTHERN_REALMS, "Clear any weather effects (resulting from Biting Frost,Torrential Rain or Impenetrable Fog cards) in play.)"),
    KING_OF_TEMERIA("King of Temeria", "/Assets/Cards/Plane/realms_foltest_copper.jpg", "/Assets/Cards/Detailed/realms_foltest_copper.jpg", Factions.NORTHERN_REALMS, "Doubles the strength of all your Siege units (unless a Commander's Horn is also present on the row)."),
    LORD_COMMANDER_OF_THE_NORTH("Lord Commander of the North", "/Assets/Cards/Plane/realms_foltest_bronze.jpg", "/Assets/Cards/Detailed/realms_foltest_bronze.jpg", Factions.NORTHERN_REALMS, "Destroy your enemy's strongest Siege unit(s) if the combined strength of all his or her Siege units is 10 or more."),
    SON_OF_MEDELL("Son of Medell", "/Assets/Cards/Plane/realms_foltest_son_of_medell.jpg", "/Assets/Cards/Detailed/realms_foltest_son_of_medell.jpg", Factions.NORTHERN_REALMS, "Destroy your enemy's strongest Ranged Combat unit(s) if the combined strength of all his or her or Ranged Combat units is 10 or more"),
    THE_WHITE_FLAME("The White Flame", "/Assets/Cards/Plane/nilfgaard_emhyr_silver.jpg", "/Assets/Cards/Detailed/nilfgaard_emhyr_silver.jpg", Factions.NILFGAARD, "Pick a Torrential Rain card from your deck and play it instantly."),
    HIS_IMPERIAL_MAJESTY("His Imperial Majesty", "/Assets/Cards/Plane/nilfgaard_emhyr_copper.jpg", "/Assets/Cards/Detailed/nilfgaard_emhyr_copper.jpg", Factions.NILFGAARD, "Look at 3 random cards from your opponent's hand."),
    EMPEROR_OF_NILFGAARD("Emperor of NILFGAARD", "/Assets/Cards/Plane/nilfgaard_emhyr_bronze.jpg", "/Assets/Cards/Detailed/nilfgaard_emhyr_bronze.jpg", Factions.NILFGAARD, "Cancel your opponent's Leader Ability."),
    THE_RELENTLESS("The Relentless", "/Assets/Cards/Plane/nilfgaard_emhyr_gold.jpg", "/Assets/Cards/Detailed/nilfgaard_emhyr_gold.jpg", Factions.NILFGAARD, "Draw a card from your opponent's pile."),
    INVADER_OF_THE_NORTH("Invader of the North", "/Assets/Cards/Plane/nilfgaard_emhyr_invader_of_the_north.jpg", "/Assets/Cards/Detailed/nilfgaard_emhyr_invader_of_the_north.jpg", Factions.NILFGAARD, "Abilities that restore a unit to the battlefield restore a randomly-chosen unit.\nAffects both players"),
    BRINGER_OF_DEATH("Bringer of Death", "/Assets/Cards/Plane/monsters_eredin_silver.jpg", "/Assets/Cards/Detailed/monsters_eredin_silver.jpg", Factions.MONSTERS, "Double the strength of all your Close Combat units (unless a Commander's horn is also present on that row)."),
    KING_OF_THE_WILD_HUNT("King of the wild Hunt", "/Assets/Cards/Plane/monsters_eredin_bronze.jpg", "/Assets/Cards/Detailed/monsters_eredin_bronze.jpg", Factions.MONSTERS, "Restore a card from your discard pile to your hand."),
    DESTROYER_OF_WORLDS("Destroyer of Worlds", "/Assets/Cards/Plane/monsters_eredin_gold.jpg", "/Assets/Cards/Detailed/monsters_eredin_gold.jpg", Factions.MONSTERS, "Discard 2 card and draw 1 card of your choice from your deck."),
    COMMANDER_OF_THE_RED_RIDERS("Commander of the Red Riders", "/Assets/Cards/Plane/monsters_eredin_copper.jpg", "/Assets/Cards/Detailed/monsters_eredin_copper.jpg", Factions.MONSTERS, "Pick any weather card from your deck and play it instantly."),
    THE_TREACHEROUS("The Treacherous", "/Assets/Cards/Plane/monsters_eredin_the_treacherous.jpg", "/Assets/Cards/Detailed/monsters_eredin_the_treacherous.jpg", Factions.MONSTERS, "Doubles the strength of all spy cards (affects both players)."),
    QUEEN_OF_DOL_BLATHANNA("Queen of Dol Blathanna", "/Assets/Cards/Plane/scoiatael_francesca_silver.jpg", "/Assets/Cards/Detailed/scoiatael_francesca_silver.jpg", Factions.SCOIATAEL, "Destroy your enemy's strongest Close Combat unit(s) if the combined strength of all his or her Close Combat units is 10 or more."),
    THE_BEAUTIFUL("The Beautiful", "/Assets/Cards/Plane/scoiatael_francesca_gold.jpg", "/Assets/Cards/Detailed/scoiatael_francesca_gold.jpg", Factions.SCOIATAEL,"Doubles the strength of all your Ranged Combat units (unless a Commander's Horn is also present on that row)."),
    DAISY_OF_THE_VALLEY("Daisy of the Valley", "/Assets/Cards/Plane/scoiatael_francesca_copper.jpg", "/Assets/Cards/Detailed/scoiatael_francesca_copper.jpg", Factions.SCOIATAEL,"draw an extra card at the beginning of the battle."),
    PUREBLOOD_ELF("Pureblood Elf", "/Assets/Cards/Plane/scoiatael_francesca_bronze.jpg", "/Assets/Cards/Detailed/scoiatael_francesca_bronze.jpg", Factions.SCOIATAEL,"Pick a Biting Frost card from your deck and play it instantly."),
    HOPE_OF_THE_AEN_SEIDHE("Hope of the Aen Seidhe", "/Assets/Cards/Plane/scoiatael_francesca_hope_of_the_aen_seidhe.jpg", "/Assets/Cards/Detailed/scoiatael_francesca_hope_of_the_aen_seidhe.jpg", Factions.SCOIATAEL,"Move agile units to whichever row maximizes their strength (do not move units already in optimal row)."),
    CRACH_AN_CRAITE("Crach an Craite", "/Assets/Cards/Plane/skellige_crach_an_craite.jpg", "/Assets/Cards/Detailed/skellige_crach_an_craite.jpg", Factions.SKELLIGE,"Shuffle all cards from each player's graveyard back into their deck."),
    KING_BRAN("King Bran", "/Assets/Cards/Plane/skellige_king_bran.jpg", "/Assets/Cards/Detailed/skellige_king_bran.jpg", Factions.SKELLIGE, "Units only lose half their Strength in bad weather condition");;
    public final String name;
    public final String planeImage;
    public final String cardImage;
    public final Factions faction;
    public final String description;

    LeaderInfo(String name, String planeImage, String cardImage, Factions faction, String description) {
        this.name = name;
        this.planeImage = planeImage;
        this.cardImage = cardImage;
        this.faction = faction;
        this.description = description;
    }

    public static Leader getLeaderByName(String name) {
        return null;
    }

    public static ArrayList<String> getLeaderAddressesByFaction(Factions faction) {
        ArrayList<String> list = new ArrayList<String>();
        for (LeaderInfo leader : LeaderInfo.values()) {
            if (leader.faction == faction)
                list.add(leader.cardImage);
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

    public static LeaderInfo getDefaultLeaderInfoByFaction(Factions faction) {
        return switch (faction) {
            case MONSTERS -> BRINGER_OF_DEATH;
            case NILFGAARD -> THE_WHITE_FLAME;
            case SCOIATAEL -> QUEEN_OF_DOL_BLATHANNA;
            case SKELLIGE -> CRACH_AN_CRAITE;
            default -> null;
        };
    }

}
