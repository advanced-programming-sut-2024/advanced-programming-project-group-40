package enums.cards;

import enums.Ability;
import enums.Factions;
import enums.Unit;
import models.cards.UnitCard;

public enum UnitCardInfo {
    BERSERKER("Berserker", 4, 1, Unit.CLOSE_COMBAT, Factions.SKELLIGE, Ability.BERSERKER),
    VIDKAARL("Vidkaarl", 14, 1, Unit.CLOSE_COMBAT, Factions.SKELLIGE, Ability.MORALE_BOOST),
    SVANRIGE("Svanrige", 4, 1, Unit.CLOSE_COMBAT, Factions.SKELLIGE, Ability.NONE),
    UDALRYK("Udalryk", 4, 1, Unit.CLOSE_COMBAT, Factions.SKELLIGE, Ability.NONE),
    DONAR_AN_HINDAR("Donar an Hindar", 4, 1, Unit.CLOSE_COMBAT, Factions.SKELLIGE, Ability.NONE),
    CLAN_AN_CRAITE("Clan An Craite", 6, 3, Unit.CLOSE_COMBAT, Factions.SKELLIGE, Ability.TIGHT_BOND),
    BLUEBOY_LUGOS("Blueboy Lugos", 6, 1, Unit.CLOSE_COMBAT, Factions.SKELLIGE, Ability.NONE),
    MADMAN_LUGOS("Madman Lugos", 6, 1, Unit.CLOSE_COMBAT, Factions.SKELLIGE, Ability.NONE),
    BIRNA_BRAN("Birna Bran", 2, 1, Unit.CLOSE_COMBAT, Factions.SKELLIGE, Ability.MEDIC),
    CLAN_DRUMMOND_SHIELDMAIDEN("Clan Drummond Shieldmaiden", 4, 3, Unit.CLOSE_COMBAT, Factions.SKELLIGE, Ability.TIGHT_BOND),
    CLAN_TORDARROCH_ARMORSMITH("Clan Tordarroch Armorsmith", 4, 1, Unit.CLOSE_COMBAT, Factions.SKELLIGE, Ability.NONE),
    CLAN_DIMUN_PIRATE("Clan Dimun Pirate", 6, 1, Unit.RANGED, Factions.SKELLIGE, Ability.SCORCH),
    CLAN_BROKVAR_ARCHER("Clan Brokvar Archer", 6, 3, Unit.RANGED, Factions.SKELLIGE, Ability.NONE),
    YOUNG_BERSERKER("Young Berserker", 2, 3, Unit.RANGED, Factions.SKELLIGE, Ability.BERSERKER),
    YOUNG_VIDKAARL("Young Vidkaarl", 8, 1, Unit.RANGED, Factions.SKELLIGE, Ability.TIGHT_BOND),
    LIGHT_LONGSHIP("Light Longship", 4, 3, Unit.RANGED, Factions.SKELLIGE, Ability.MUSTER),
    HOLGER_BLACKHAND("Holger Blackhand", 4, 1, Unit.SIEGE, Factions.SKELLIGE, Ability.NONE),
    WAR_LONGSHIP("War Longship", 6, 3, Unit.SIEGE, Factions.SKELLIGE, Ability.TIGHT_BOND),
    DRAIG_BON_DHU("Draig Bon-Dhu", 2, 1, Unit.SIEGE, Factions.SKELLIGE, Ability.COMMANDERS_HORN),
    OLAF("Olaf", 12, 1, Unit.AGILE, Factions.SKELLIGE, Ability.MORALE_BOOST),
    ELVEN_SKIRMISHER("Elven Skirmisher", 2, 3, Unit.RANGED, Factions.SCOIATAEL, Ability.MUSTER),
    YAEVINN("Yaevinn", 6, 1, Unit.AGILE, Factions.SCOIATAEL, Ability.NONE),
    CIARAN_AEP("Ciaran aep", 3, 1, Unit.AGILE, Factions.SCOIATAEL, Ability.NONE),
    DENNIS_CRANMER("Dennis Cranmer", 6, 1, Unit.CLOSE_COMBAT, Factions.SCOIATAEL, Ability.NONE),
    DOL_BLATHANNA_SCOUT("Dol Blathanna Scout", 6, 3, Unit.AGILE, Factions.SCOIATAEL, Ability.NONE),
    DOL_BLATHANNA_ARCHER("Dol Blathanna Archer", 4, 1, Unit.RANGED, Factions.SCOIATAEL, Ability.NONE),
    DWARVEN_SKIRMISHER("Dwarven Skirmisher", 3, 3, Unit.CLOSE_COMBAT, Factions.SCOIATAEL, Ability.MUSTER),
    FILAVANDREL("Filavandrel", 6, 1, Unit.AGILE, Factions.SCOIATAEL, Ability.NONE),
    HAVEKAR_HEALER("Havekar Healer", 0, 3, Unit.RANGED, Factions.SCOIATAEL, Ability.MEDIC),
    HAVEKAR_SMUGGLER("Havekar Smuggler", 5, 3, Unit.CLOSE_COMBAT, Factions.SCOIATAEL, Ability.MUSTER),
    IDA_EMEAN_AEP("Ida Emean aep", 6, 1, Unit.RANGED, Factions.SCOIATAEL, Ability.NONE),
    RIORDAIN("Riordain", 1, 1, Unit.RANGED, Factions.SCOIATAEL, Ability.NONE),
    TORUVIEL("Toruviel", 2, 1, Unit.RANGED, Factions.SCOIATAEL, Ability.NONE),
    VRIHEDD_BRIGADE_RECRUIT("Vrihedd Brigade Recruit", 4, 1, Unit.RANGED, Factions.SCOIATAEL, Ability.NONE),
    MAHAKAMAN_DEFENDER("Mahakaman Defender", 5, 5, Unit.CLOSE_COMBAT, Factions.SCOIATAEL, Ability.NONE),
    VRIHEDD_BRIGADE_VETERAN("Vrihedd Brigade Veteran", 5, 2, Unit.AGILE, Factions.SCOIATAEL, Ability.NONE),
    MILVA("Milva", 10, 1, Unit.RANGED, Factions.SCOIATAEL, Ability.MORALE_BOOST),
    SCHIRRU("Schirru", 8, 1, Unit.SIEGE, Factions.SCOIATAEL, Ability.SCORCH),
    BARCLAY_ELS("Barclay Els", 6, 1, Unit.AGILE, Factions.SCOIATAEL, Ability.NONE),
    BALLISTA("Ballista", 6, 2, Unit.SIEGE, Factions.NORTHERN_REALMS, Ability.NONE),
    BLUE_STRIPES_COMMANDO("Blue Stripes Commando", 4, 3, Unit.CLOSE_COMBAT, Factions.NORTHERN_REALMS, Ability.TIGHT_BOND),
    CATAPULT("Catapult", 8, 2, Unit.SIEGE, Factions.NORTHERN_REALMS, Ability.TIGHT_BOND),
    DRAGON_HUNTER("Dragon Hunter", 5, 3, Unit.RANGED, Factions.NORTHERN_REALMS, Ability.TIGHT_BOND),
    DETHMOLD("Dethmold", 6, 1, Unit.RANGED, Factions.NORTHERN_REALMS, Ability.NONE),
    DUN_BANNER_MEDIC("Dun Banner Medic", 5, 1, Unit.SIEGE, Factions.NORTHERN_REALMS, Ability.MEDIC),
    KAEDWENI_SIEGE_EXPERT("Kaedweni Siege Expert", 1, 3, Unit.SIEGE, Factions.NORTHERN_REALMS, Ability.MORALE_BOOST),
    KEIRA_METZ("Keira Metz", 5, 1, Unit.RANGED, Factions.NORTHERN_REALMS, Ability.NONE),
    POOR_FUCKING_INFANTRY("Poor Fucking Infantry", 1, 4, Unit.CLOSE_COMBAT, Factions.NORTHERN_REALMS, Ability.TIGHT_BOND),
    PRINCE_STENNIS("Prince Stennis", 5, 1, Unit.CLOSE_COMBAT, Factions.NORTHERN_REALMS, Ability.SPY),
    REDANIAN_FOOT_SOLDIER("Redanian Foot Soldier", 1, 2, Unit.CLOSE_COMBAT, Factions.NORTHERN_REALMS, Ability.NONE),
    SABRINA_GLEVISSING("Sabrina Glevissing", 4, 1, Unit.RANGED, Factions.NORTHERN_REALMS, Ability.NONE),
    SHELDON_SKAGGS("Sheldon Skaggs", 4, 1, Unit.RANGED, Factions.NORTHERN_REALMS, Ability.NONE),
    SIEGE_TOWER("Siege Tower", 6, 1, Unit.SIEGE, Factions.NORTHERN_REALMS, Ability.NONE),
    SIEGFRIED_OF_DENESLE("Siegfried of Denesle", 5, 1, Unit.CLOSE_COMBAT, Factions.NORTHERN_REALMS, Ability.NONE),
    SIGISMUND_DIJKSTRA("Sigismund Dijkstra", 4, 1, Unit.CLOSE_COMBAT, Factions.NORTHERN_REALMS, Ability.SPY),
    SILE_DE_TANSARVILLE("Síle de Tansarville", 5, 1, Unit.RANGED, Factions.NORTHERN_REALMS, Ability.NONE),
    THALER("Thaler", 1, 1, Unit.SIEGE, Factions.NORTHERN_REALMS, Ability.SPY),
    TREBUCHET("Trebuchet", 6, 2, Unit.SIEGE, Factions.NORTHERN_REALMS, Ability.NONE),
    VES("Ves", 5, 1, Unit.CLOSE_COMBAT, Factions.NORTHERN_REALMS, Ability.NONE),
    YARPEN_ZIGRIN("Yarpen Zigrin", 2, 1, Unit.CLOSE_COMBAT, Factions.NORTHERN_REALMS, Ability.NONE),
    IMPERA_BRIGADE_GUARD("Impera Brigade Guard", 3, 4, Unit.CLOSE_COMBAT, Factions.NILFGAARD, Ability.TIGHT_BOND),
    STEFAN_SKELLEN("Stefan Skellen", 9, 1, Unit.CLOSE_COMBAT, Factions.NILFGAARD, Ability.SPY),
    SHILARD_FITZ_OESTERLEN("Shilard Fitz-Oesterlen", 7, 1, Unit.CLOSE_COMBAT, Factions.NILFGAARD, Ability.SPY),
    YOUNG_EMISSARY("Young Emissary", 5, 2, Unit.CLOSE_COMBAT, Factions.NILFGAARD, Ability.TIGHT_BOND),
    CAHIR_MAWR_DYFFRYN_AEP_CEALLACH("Cahir Mawr Dyffryn aep Ceallach", 6, 1, Unit.CLOSE_COMBAT, Factions.NILFGAARD, Ability.NONE),
    VATTIER_DE_RIDEAUX("Vattier de Rideaux", 4, 1, Unit.CLOSE_COMBAT, Factions.NILFGAARD, Ability.SPY),
    PUTTKAMMER("Puttkammer", 3, 1, Unit.RANGED, Factions.NILFGAARD, Ability.NONE),
    ASSIRE_VAR_ANAHID("Assire var Anahid", 6, 1, Unit.RANGED, Factions.NILFGAARD, Ability.NONE),
    BLACK_INFANTRY_ARCHER("Black Infantry Archer", 10, 2, Unit.RANGED, Factions.NILFGAARD, Ability.NONE),
    RENUALD_AEP_MATSEN("Renuald aep Matsen", 5, 1, Unit.RANGED, Factions.NILFGAARD, Ability.NONE),
    FRINGILLA_VIGO("Fringilla Vigo", 6, 1, Unit.RANGED, Factions.NILFGAARD, Ability.NONE),
    ROTTEN_MANGONEL("Rotten Mangonel", 3, 1, Unit.SIEGE, Factions.NILFGAARD, Ability.NONE),
    HEAVY_ZERRIKANIAN_FIRE_SCORPION("Heavy Zerrikanian Fire Scorpion", 10, 1, Unit.SIEGE, Factions.NILFGAARD, Ability.NONE),
    ZERRIKANIAN_FIRE_SCORPION("Zerrikanian Fire Scorpion", 5, 1, Unit.SIEGE, Factions.NILFGAARD, Ability.NONE),
    SIEGE_ENGINEER("Siege Engineer", 6, 1, Unit.SIEGE, Factions.NILFGAARD, Ability.NONE),
    ALBRICH("Albrich", 2, 1, Unit.RANGED, Factions.NILFGAARD, Ability.NONE),
    CYNTHIA("Cynthia", 4, 1, Unit.RANGED, Factions.NILFGAARD, Ability.NONE),
    ETOLIAN_AUXILIARY_ARCHERS("Etolian Auxiliary Archers", 1, 2, Unit.RANGED, Factions.NILFGAARD, Ability.MEDIC),
    MORTEISEN("Morteisen", 3, 1, Unit.CLOSE_COMBAT, Factions.NILFGAARD, Ability.NONE),
    NAUSICAA_CAVALRY_RIDER("Nausicaa Cavalry Rider", 2, 3, Unit.CLOSE_COMBAT, Factions.NILFGAARD, Ability.TIGHT_BOND),
    RAINFARN("Rainfarn", 4, 1, Unit.CLOSE_COMBAT, Factions.NILFGAARD, Ability.NONE),
    SIEGE_TECHNICIAN("Siege Technician", 0, 1, Unit.SIEGE, Factions.NILFGAARD, Ability.MEDIC),
    SWEERS("Sweers", 2, 1, Unit.RANGED, Factions.NILFGAARD, Ability.NONE),
    VANHEMAR("Vanhemar", 4, 1, Unit.RANGED, Factions.NILFGAARD, Ability.NONE),
    VREEMDE("Vreemde", 2, 1, Unit.CLOSE_COMBAT, Factions.NILFGAARD, Ability.NONE),
    TOAD("Toad", 7, 1, Unit.RANGED, Factions.MONSTERS, Ability.SCORCH),
    ARACHAS_BEHEMOTH("Arachas Behemoth", 6, 1, Unit.SIEGE, Factions.MONSTERS, Ability.MUSTER),
    CRONE_BREWESS("Crone: Brewess", 6, 1, Unit.CLOSE_COMBAT, Factions.MONSTERS, Ability.MUSTER),
    CRONE_WEAVESS("Crone: Weavess", 6, 1, Unit.CLOSE_COMBAT, Factions.MONSTERS, Ability.MUSTER),
    CRONE_WHISPESS("Crone: Whispess", 6, 1, Unit.CLOSE_COMBAT, Factions.MONSTERS, Ability.MUSTER),
    EARTH_ELEMENTAL("Earth Elemental", 6, 1, Unit.SIEGE, Factions.MONSTERS, Ability.NONE),
    FIEND("Fiend", 6, 1, Unit.CLOSE_COMBAT, Factions.MONSTERS, Ability.NONE),
    FIRE_ELEMENTAL("Fire Elemental", 6, 1, Unit.SIEGE, Factions.MONSTERS, Ability.NONE),
    FORKTAIL("Forktail", 5, 1, Unit.CLOSE_COMBAT, Factions.MONSTERS, Ability.NONE),
    FRIGHTENER("Frightener", 5, 1, Unit.CLOSE_COMBAT, Factions.MONSTERS, Ability.NONE),
    GRAVE_HAG("Grave Hag", 5, 1, Unit.RANGED, Factions.MONSTERS, Ability.NONE),
    GRIFFIN("Griffin", 5, 1, Unit.CLOSE_COMBAT, Factions.MONSTERS, Ability.NONE),
    ICE_GIANT("Ice Giant", 5, 1, Unit.SIEGE, Factions.MONSTERS, Ability.NONE),
    PLAGUE_MAIDEN("Plague Maiden", 5, 1, Unit.CLOSE_COMBAT, Factions.MONSTERS, Ability.NONE),
    VAMPIRE_KATAKAN("Vampire: Katakan", 5, 1, Unit.CLOSE_COMBAT, Factions.MONSTERS, Ability.MUSTER),
    WEREWOLF("Werewolf", 5, 1, Unit.CLOSE_COMBAT, Factions.MONSTERS, Ability.NONE),
    ARACHAS("Arachas", 4, 3, Unit.CLOSE_COMBAT, Factions.MONSTERS, Ability.MUSTER),
    BOTCHLING("Botchling", 4, 1, Unit.CLOSE_COMBAT, Factions.MONSTERS, Ability.NONE),
    VAMPIRE_BRUXA("Vampire: Bruxa", 4, 1, Unit.CLOSE_COMBAT, Factions.MONSTERS, Ability.MUSTER),
    VAMPIRE_EKIMMARA("Vampire: Ekimmara", 4, 1, Unit.CLOSE_COMBAT, Factions.MONSTERS, Ability.MUSTER),
    VAMPIRE_FLEDER("Vampire: Fleder", 4, 1, Unit.CLOSE_COMBAT, Factions.MONSTERS, Ability.MUSTER),
    VAMPIRE_GARKAIN("Vampire: Garkain", 4, 1, Unit.CLOSE_COMBAT, Factions.MONSTERS, Ability.MUSTER),
    CELAENO_HARPY("Celaeno Harpy", 2, 1, Unit.AGILE, Factions.MONSTERS, Ability.NONE),
    COCKATRICE("Cockatrice", 2, 1, Unit.RANGED, Factions.MONSTERS, Ability.NONE),
    ENDREGA("Endrega", 2, 1, Unit.RANGED, Factions.MONSTERS, Ability.NONE),
    FOGLET("Foglet", 2, 1, Unit.CLOSE_COMBAT, Factions.MONSTERS, Ability.NONE),
    GARGOYLE("Gargoyle", 2, 1, Unit.RANGED, Factions.MONSTERS, Ability.NONE),
    HARPY("Harpy", 2, 1, Unit.AGILE, Factions.MONSTERS, Ability.NONE),
    NEKKER("Nekker", 2, 3, Unit.CLOSE_COMBAT, Factions.MONSTERS, Ability.MUSTER),
    WYVERN("Wyvern", 2, 1, Unit.RANGED, Factions.MONSTERS, Ability.NONE),
    GHOUL("Ghoul", 1, 3, Unit.CLOSE_COMBAT, Factions.MONSTERS, Ability.MUSTER),
    DANDELION("Dandelion", 2, 1, Unit.CLOSE_COMBAT, Factions.NEUTRAL, Ability.COMMANDERS_HORN),
    COW("Cow", 0, 1, Unit.RANGED, Factions.NEUTRAL, Ability.TRANSFORMER),
    EMIEL_REGIS("Emiel Regis", 5, 1, Unit.CLOSE_COMBAT, Factions.NEUTRAL, Ability.NONE),
    GAUNTER_ODIMM("Gaunter O’Dimm", 2, 1, Unit.SIEGE, Factions.NEUTRAL, Ability.MUSTER),
    GAUNTER_ODIMM_DARKNESS("Gaunter O’DImm Darkness", 4, 3, Unit.RANGED, Factions.NEUTRAL, Ability.MUSTER),
    OLGIERD_VON_EVERC("Olgierd Von Everc", 6, 1, Unit.AGILE, Factions.NEUTRAL, Ability.MORALE_BOOST),
    VESEMIR("Vesemir", 6, 1, Unit.CLOSE_COMBAT, Factions.NEUTRAL, Ability.NONE),
    VILLENTRETENMERTH("Villentretenmerth", 7, 1, Unit.CLOSE_COMBAT, Factions.NEUTRAL, Ability.SCORCH),
    ZOLTAN_CHIVAY("Zoltan Chivay", 5, 1, Unit.CLOSE_COMBAT, Factions.NEUTRAL, Ability.NONE);


    public final String name;
    public final int power;
    public final int maxCapacity;
    public final Unit unit;
    public final Factions faction;
    public final Ability ability;

    UnitCardInfo(String name, int power, int maxCapacity, Unit unit, Factions faction, Ability ability) {
        this.name = name;
        this.power = power;
        this.maxCapacity = maxCapacity;
        this.unit = unit;
        this.faction = faction;
        this.ability = ability;
    }

    public static UnitCard getRegularCardByName(String name) {
        return null;
    }

}
