package enums.cards;

import enums.Ability;
import enums.Factions;
import enums.Unit;

public enum HeroInfo implements CardInfo {
    Cerys("Cerys", "/Assets/Cards/Plane/skellige_cerys.jpg", "/Assets/Cards/Detailed/skellige_cerys.jpg", 10, Unit.CLOSE_COMBAT, Factions.SKELLIGE, Ability.MUSTER),
    Kambi("Kambi", "/Assets/Cards/Plane/skellige_kambi.jpg", "/Assets/Cards/Detailed/skellige_kambi.jpg", 11, Unit.CLOSE_COMBAT, Factions.SKELLIGE, Ability.TRANSFORMER),
    Ermion("Ermion", "/Assets/Cards/Plane/skellige_ermion.jpg", "/Assets/Cards/Detailed/skellige_ermion.jpg", 8, Unit.RANGED, Factions.SKELLIGE, Ability.MARDROEME),
    Hjalmer("Hjalmer", "/Assets/Cards/Plane/skellige_hjalmar.jpg", "/Assets/Cards/Detailed/skellige_hjalmar.jpg", 10, Unit.RANGED, Factions.SKELLIGE, Ability.HERO),
    Iorveth("Iorveth", "/Assets/Cards/Plane/scoiatael_iorveth.jpg", "/Assets/Cards/Detailed/scoiatael_iorveth.jpg", 10, Unit.RANGED, Factions.SCOIATAEL, Ability.HERO),
    Seasenthessis("Seasenthessis", "/Assets/Cards/Plane/scoiatael_saskia.jpg", "/Assets/Cards/Detailed/scoiatael_saskia.jpg", 10, Unit.RANGED, Factions.SCOIATAEL, Ability.HERO),
    Eithne("Eithne", "/Assets/Cards/Plane/scoiatael_eithne.jpg", "/Assets/Cards/Detailed/scoiatael_eithne.jpg", 10, Unit.RANGED, Factions.SCOIATAEL, Ability.HERO),
    Isengrim_Faoiltiarna("Isengrim Faoiltiarna", "/Assets/Cards/Plane/scoiatael_isengrim.jpg", "/Assets/Cards/Detailed/scoiatael_isengrim.jpg", 10, Unit.CLOSE_COMBAT, Factions.SCOIATAEL, Ability.MORALE_BOOST),
    ESTERAD_THYSSEN("Esterad Thyssen", "/Assets/Cards/Plane/realms_esterad.jpg", "/Assets/Cards/Detailed/realms_esterad.jpg", 10, Unit.CLOSE_COMBAT, Factions.NORTHERN_REALMS, Ability.HERO),
    JOHN_NATALIS("John Natalis", "/Assets/Cards/Plane/realms_natalis.jpg", "/Assets/Cards/Detailed/realms_natalis.jpg", 10, Unit.CLOSE_COMBAT, Factions.NORTHERN_REALMS, Ability.HERO),
    PHILIPPA_EILHART("Philippa Eilhart", "/Assets/Cards/Plane/realms_philippa.jpg", "/Assets/Cards/Detailed/realms_philippa.jpg", 10, Unit.RANGED, Factions.NORTHERN_REALMS, Ability.HERO),
    VERNON_ROCHE("Vernon Roche", "/Assets/Cards/Plane/realms_vernon.jpg", "/Assets/Cards/Detailed/realms_vernon.jpg", 10, Unit.CLOSE_COMBAT, Factions.NORTHERN_REALMS, Ability.HERO),
    MENNO_COEHOORN("Menno Coehoorn", "/Assets/Cards/Plane/nilfgaard_menno.jpg", "/Assets/Cards/Detailed/nilfgaard_menno.jpg", 10, Unit.CLOSE_COMBAT, Factions.NILFGAARD, Ability.MEDIC),
    TIBOR_EGGEBRACHT("Tibor Eggebracht", "/Assets/Cards/Plane/nilfgaard_tibor.jpg", "/Assets/Cards/Detailed/nilfgaard_tibor.jpg", 10, Unit.RANGED, Factions.NILFGAARD, Ability.HERO),
    MORVRAN_VOORHIS("Morvran Voorhis", "/Assets/Cards/Plane/nilfgaard_moorvran.jpg", "/Assets/Cards/Detailed/nilfgaard_moorvran.jpg", 10, Unit.SIEGE, Factions.NILFGAARD, Ability.HERO),
    LETHO_OF_GULET("Letho of Gulet", "/Assets/Cards/Plane/nilfgaard_letho.jpg", "/Assets/Cards/Detailed/nilfgaard_letho.jpg", 10, Unit.CLOSE_COMBAT, Factions.NILFGAARD, Ability.HERO),
    DRAUG("Draug", "/Assets/Cards/Plane/monsters_draug.jpg", "/Assets/Cards/Detailed/monsters_draug.jpg", 10, Unit.CLOSE_COMBAT, Factions.MONSTERS, Ability.HERO),
    IMLERITH("Imlerith", "/Assets/Cards/Plane/monsters_imlerith.jpg", "/Assets/Cards/Detailed/monsters_imlerith.jpg", 10, Unit.CLOSE_COMBAT, Factions.MONSTERS, Ability.HERO),
    LESHEN("Leshen", "/Assets/Cards/Plane/monsters_leshen.jpg", "/Assets/Cards/Detailed/monsters_leshen.jpg", 10, Unit.CLOSE_COMBAT, Factions.MONSTERS, Ability.HERO),
    KAYRAN("Kayran", "/Assets/Cards/Plane/monsters_kayran.jpg", "/Assets/Cards/Detailed/monsters_kayran.jpg", 8, Unit.AGILE, Factions.MONSTERS, Ability.MORALE_BOOST),
    GERALT_OF_RIVIA("Geralt of Rivia", "/Assets/Cards/Plane/neutral_geralt.jpg", "/Assets/Cards/Detailed/neutral_geralt.jpg", 15, Unit.CLOSE_COMBAT, Factions.NEUTRAL, Ability.HERO),
    MYSTERIOUS_ELF("Mysterious Elf", "/Assets/Cards/Plane/neutral_mysterious_elf.jpg", "/Assets/Cards/Detailed/neutral_mysterious_elf.jpg", 0, Unit.CLOSE_COMBAT, Factions.NEUTRAL, Ability.SPY),
    TRISS_MERIGOLD("Triss Merigold", "/Assets/Cards/Plane/neutral_triss.jpg", "/Assets/Cards/Detailed/neutral_triss.jpg", 7, Unit.CLOSE_COMBAT, Factions.NEUTRAL, Ability.HERO),
    YENNEFER_OF_VENGERBERG("Yennefer of Vengerberg", "/Assets/Cards/Plane/neutral_yennefer.jpg", "/Assets/Cards/Detailed/neutral_yennefer.jpg", 7, Unit.RANGED, Factions.NEUTRAL, Ability.MEDIC);
    public final String name;
    public final String planeImage;
    public final String cardImage;
    public final int power;
    public final Unit unit;
    public final Factions faction;
    public final Ability ability;

    HeroInfo(String name, String planeImage, String cardImage, int power, Unit unit, Factions faction, Ability ability) {
        this.name = name;
        this.planeImage = planeImage;
        this.cardImage = cardImage;
        this.power = power;
        this.unit = unit;
        this.faction = faction;
        this.ability = ability;
    }


    @Override
    public String getType() {
        return "hero";
    }
}
