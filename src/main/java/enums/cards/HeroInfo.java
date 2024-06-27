package enums.cards;

import enums.Ability;
import enums.Factions;
import enums.Unit;
import models.cards.Hero;

public enum HeroInfo {
    Cerys("Cerys", 10, Unit.CLOSE_COMBAT, Factions.SKELLIGE, Ability.MUSTER),
    Kambi("Kambi", 11, Unit.CLOSE_COMBAT, Factions.SKELLIGE, Ability.TRANSFORMER),
    Ermion("Ermion", 8, Unit.RANGED, Factions.SKELLIGE, Ability.MARDROEME),
    Hjalmer("Hjalmer", 10, Unit.RANGED, Factions.SKELLIGE, Ability.HERO),
    Iorveth("Iorveth", 10, Unit.RANGED, Factions.SCOIATAEL, Ability.HERO),
    Seasenthessis("Seasenthessis", 10, Unit.RANGED, Factions.SCOIATAEL, Ability.HERO),
    Eithne("Eithne", 10, Unit.RANGED, Factions.SCOIATAEL, Ability.HERO),
    Isengrim_Faoiltiarna("Isengrim Faoiltiarna", 10, Unit.CLOSE_COMBAT, Factions.SCOIATAEL, Ability.MORALE_BOOST),
    ESTERAD_THYSSEN("Esterad Thyssen", 10, Unit.CLOSE_COMBAT, Factions.NORTHERN_REALMS, Ability.HERO),
    JOHN_NATALIS("John Natalis", 10, Unit.CLOSE_COMBAT, Factions.NORTHERN_REALMS, Ability.HERO),
    PHILIPPA_EILHART("Philippa Eilhart", 10, Unit.RANGED, Factions.NORTHERN_REALMS, Ability.HERO),
    VERNON_ROCHE("Vernon Roche", 10, Unit.CLOSE_COMBAT, Factions.NORTHERN_REALMS, Ability.HERO),
    MENNO_COEHOORN("Menno Coehoorn", 10, Unit.CLOSE_COMBAT, Factions.NILFGAARD, Ability.MEDIC),
    TIBOR_EGGEBRACHT("Tibor Eggebracht", 10, Unit.RANGED, Factions.NILFGAARD, Ability.HERO),
    MORVRAN_VOORHIS("Morvran Voorhis", 10, Unit.SIEGE, Factions.NILFGAARD, Ability.HERO),
    LETHO_OF_GULET("Letho of Gulet", 10, Unit.CLOSE_COMBAT, Factions.NILFGAARD, Ability.HERO),
    MENNO_COEHOORN_2("Menno Coehoorn", 10, Unit.CLOSE_COMBAT, Factions.NILFGAARD, Ability.MEDIC),
    MORVRAN_VOORHIS_2("Morvran Voorhis", 10, Unit.SIEGE, Factions.NILFGAARD, Ability.HERO),
    DRAUG("Draug", 10, Unit.CLOSE_COMBAT, Factions.MONSTERS, Ability.HERO),
    IMLERITH("Imlerith", 10, Unit.CLOSE_COMBAT, Factions.MONSTERS, Ability.HERO),
    LESHEN("Leshen", 10, Unit.CLOSE_COMBAT, Factions.MONSTERS, Ability.HERO),
    KAYRAN("Kayran", 8, Unit.AGILE, Factions.MONSTERS, Ability.MORALE_BOOST),
    GERALT_OF_RIVIA("Geralt of Rivia", 15, Unit.CLOSE_COMBAT, Factions.NEUTRAL, Ability.HERO),
    MYSTERIOUS_ELF("Mysterious Elf", 0, Unit.CLOSE_COMBAT, Factions.NEUTRAL, Ability.SPY),
    TRISS_MERIGOLD("Triss Merigold", 7, Unit.CLOSE_COMBAT, Factions.NEUTRAL, Ability.HERO),
    YENNEFER_OF_VENGERBERG("Yennefer of Vengerberg", 7, Unit.RANGED, Factions.NEUTRAL, Ability.MEDIC)
    ;
    public final String name;
    public final int power;
    public final Unit unit;
    public final Factions faction;
    public final Ability ability;

    HeroInfo(String name, int power, Unit unit, Factions faction, Ability ability) {
        this.name = name;
        this.power = power;
        this.unit = unit;
        this.faction = faction;
        this.ability = ability;
    }

    public static Hero getHeroCardByName(String name) {
        return null;
    }
}
