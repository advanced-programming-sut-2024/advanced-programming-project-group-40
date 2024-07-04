package enums.cards;

import enums.Ability;
import enums.Factions;
import enums.Unit;
import models.cards.UnitCard;

public enum UnitCardInfo {
    BERSERKER("Berserker", "/Assets/Cards/Plane/skellige_berserker.jpg", "/Assets/Cards/Detailed/skellige_berserker.jpg", 4, 1, Unit.CLOSE_COMBAT, Factions.SKELLIGE, Ability.BERSERKER),
    VIDKAARL("Vidkaarl", "/Assets/Cards/Plane/skellige_vildkaarl.jpg", "/Assets/Cards/Detailed/skellige_vildkaarl.jpg", 14, 1, Unit.CLOSE_COMBAT, Factions.SKELLIGE, Ability.MORALE_BOOST),
    SVANRIGE("Svanrige", "/Assets/Cards/Plane/skellige_svanrige.jpg", "/Assets/Cards/Detailed/skellige_svanrige.jpg", 4, 1, Unit.CLOSE_COMBAT, Factions.SKELLIGE, Ability.NONE),
    UDALRYK("Udalryk", "/Assets/Cards/Plane/skellige_udalryk.jpg", "/Assets/Cards/Detailed/skellige_udalryk.jpg", 4, 1, Unit.CLOSE_COMBAT, Factions.SKELLIGE, Ability.NONE),
    DONAR_AN_HINDAR("Donar an Hindar", "/Assets/Cards/Plane/skellige_donar.jpg", "/Assets/Cards/Detailed/skellige_donar.jpg", 4, 1, Unit.CLOSE_COMBAT, Factions.SKELLIGE, Ability.NONE),
    CLAN_AN_CRAITE("Clan An Craite", "/Assets/Cards/Plane/skellige_craite_warrior.jpg", "/Assets/Cards/Detailed/skellige_craite_warrior.jpg", 6, 3, Unit.CLOSE_COMBAT, Factions.SKELLIGE, Ability.TIGHT_BOND),
    BLUEBOY_LUGOS("Blueboy Lugos", "/Assets/Cards/Plane/skellige_blueboy.jpg", "/Assets/Cards/Detailed/skellige_blueboy.jpg", 6, 1, Unit.CLOSE_COMBAT, Factions.SKELLIGE, Ability.NONE),
    MADMAN_LUGOS("Madman Lugos", "/Assets/Cards/Plane/skellige_madmad_lugos.jpg", "/Assets/Cards/Detailed/skellige_madmad_lugos.jpg", 6, 1, Unit.CLOSE_COMBAT, Factions.SKELLIGE, Ability.NONE),
    BIRNA_BRAN("Birna Bran", "/Assets/Cards/Plane/skellige_birna.jpg", "/Assets/Cards/Detailed/skellige_birna.jpg", 2, 1, Unit.CLOSE_COMBAT, Factions.SKELLIGE, Ability.MEDIC),
    CLAN_DRUMMOND_SHIELDMAIDEN("Clan Drummond Shieldmaiden", "/Assets/Cards/Plane/skellige_shield_maiden.jpg", "/Assets/Cards/Detailed/skellige_shield_maiden.jpg", 4, 3, Unit.CLOSE_COMBAT, Factions.SKELLIGE, Ability.TIGHT_BOND),
    CLAN_TORDARROCH_ARMORSMITH("Clan Tordarroch Armorsmith", "/Assets/Cards/Plane/skellige_tordarroch.jpg", "/Assets/Cards/Detailed/skellige_tordarroch.jpg", 4, 1, Unit.CLOSE_COMBAT, Factions.SKELLIGE, Ability.NONE),
    CLAN_DIMUN_PIRATE("Clan Dimun Pirate", "/Assets/Cards/Plane/skellige_dimun_pirate.jpg", "/Assets/Cards/Detailed/skellige_dimun_pirate.jpg", 6, 1, Unit.RANGED, Factions.SKELLIGE, Ability.SCORCH),
    CLAN_BROKVAR_ARCHER("Clan Brokvar Archer", "/Assets/Cards/Plane/skellige_brokva_archer.jpg", "/Assets/Cards/Detailed/skellige_brokva_archer.jpg", 6, 3, Unit.RANGED, Factions.SKELLIGE, Ability.NONE),
    YOUNG_BERSERKER("Young Berserker", "/Assets/Cards/Plane/skellige_young_berserker.jpg", "/Assets/Cards/Detailed/skellige_young_berserker.jpg", 2, 3, Unit.RANGED, Factions.SKELLIGE, Ability.BERSERKER),
    YOUNG_VIDKAARL("Young Vidkaarl", "/Assets/Cards/Plane/skellige_young_vildkaarl.jpg", "/Assets/Cards/Detailed/skellige_young_vildkaarl.jpg", 8, 1, Unit.RANGED, Factions.SKELLIGE, Ability.TIGHT_BOND),
    LIGHT_LONGSHIP("Light Longship", "/Assets/Cards/Plane/skellige_light_longship.jpg", "/Assets/Cards/Detailed/skellige_light_longship.jpg", 4, 3, Unit.RANGED, Factions.SKELLIGE, Ability.MUSTER),
    HOLGER_BLACKHAND("Holger Blackhand", "/Assets/Cards/Plane/skellige_holger.jpg", "/Assets/Cards/Detailed/skellige_holger.jpg", 4, 1, Unit.SIEGE, Factions.SKELLIGE, Ability.NONE),
    WAR_LONGSHIP("War Longship", "/Assets/Cards/Plane/skellige_war_longship.jpg", "/Assets/Cards/Detailed/skellige_war_longship.jpg", 6, 3, Unit.SIEGE, Factions.SKELLIGE, Ability.TIGHT_BOND),
    DRAIG_BON_DHU("Draig Bon-Dhu", "/Assets/Cards/Plane/skellige_draig.jpg", "/Assets/Cards/Detailed/skellige_draig.jpg", 2, 1, Unit.SIEGE, Factions.SKELLIGE, Ability.COMMANDERS_HORN),
    OLAF("Olaf", "/Assets/Cards/Plane/skellige_olaf.jpg", "/Assets/Cards/Detailed/skellige_olaf.jpg", 12, 1, Unit.AGILE, Factions.SKELLIGE, Ability.MORALE_BOOST),
    ELVEN_SKIRMISHER("Elven Skirmisher", "/Assets/Cards/Plane/scoiatael_elf_skirmisher.jpg", "/Assets/Cards/Detailed/scoiatael_elf_skirmisher.jpg", 2, 3, Unit.RANGED, Factions.SCOIATAEL, Ability.MUSTER),
    YAEVINN("Yaevinn", "/Assets/Cards/Plane/scoiatael_yaevinn.jpg", "/Assets/Cards/Detailed/scoiatael_yaevinn.jpg", 6, 1, Unit.AGILE, Factions.SCOIATAEL, Ability.NONE),
    CIARAN_AEP("Ciaran aep", "/Assets/Cards/Plane/scoiatael_ciaran.jpg", "/Assets/Cards/Detailed/scoiatael_ciaran.jpg", 3, 1, Unit.AGILE, Factions.SCOIATAEL, Ability.NONE),
    DENNIS_CRANMER("Dennis Cranmer", "/Assets/Cards/Plane/scoiatael_dennis.jpg", "/Assets/Cards/Detailed/scoiatael_dennis.jpg", 6, 1, Unit.CLOSE_COMBAT, Factions.SCOIATAEL, Ability.NONE),
    DOL_BLATHANNA_SCOUT("Dol Blathanna Scout", "/Assets/Cards/Plane/scoiatael_dol_infantry.jpg", "/Assets/Cards/Detailed/scoiatael_dol_infantry.jpg", 6, 3, Unit.AGILE, Factions.SCOIATAEL, Ability.NONE),
    DOL_BLATHANNA_ARCHER("Dol Blathanna Archer", "/Assets/Cards/Plane/scoiatael_dol_archer.jpg", "/Assets/Cards/Detailed/scoiatael_dol_archer.jpg", 4, 1, Unit.RANGED, Factions.SCOIATAEL, Ability.NONE),
    DWARVEN_SKIRMISHER("Dwarven Skirmisher", "/Assets/Cards/Plane/scoiatael_dwarf.jpg", "/Assets/Cards/Detailed/scoiatael_dwarf.jpg", 3, 3, Unit.CLOSE_COMBAT, Factions.SCOIATAEL, Ability.MUSTER),
    FILAVANDREL("Filavandrel", "/Assets/Cards/Plane/scoiatael_filavandrel.jpg", "/Assets/Cards/Detailed/scoiatael_filavandrel.jpg", 6, 1, Unit.AGILE, Factions.SCOIATAEL, Ability.NONE),
    HAVEKAR_HEALER("Havekar Healer", "/Assets/Cards/Plane/scoiatael_havekar_nurse.jpg", "/Assets/Cards/Detailed/scoiatael_havekar_nurse.jpg", 0, 3, Unit.RANGED, Factions.SCOIATAEL, Ability.MEDIC),
    HAVEKAR_SMUGGLER("Havekar Smuggler", "/Assets/Cards/Plane/scoiatael_havekar_support_2.jpg", "/Assets/Cards/Detailed/scoiatael_havekar_support_2.jpg", 5, 3, Unit.CLOSE_COMBAT, Factions.SCOIATAEL, Ability.MUSTER),
    IDA_EMEAN_AEP("Ida Emean aep", "/Assets/Cards/Plane/scoiatael_ida.jpg", "/Assets/Cards/Detailed/scoiatael_ida.jpg", 6, 1, Unit.RANGED, Factions.SCOIATAEL, Ability.NONE),
    RIORDAIN("Riordain", "/Assets/Cards/Plane/scoiatael_riordain.jpg", "/Assets/Cards/Detailed/scoiatael_riordain.jpg", 1, 1, Unit.RANGED, Factions.SCOIATAEL, Ability.NONE),
    TORUVIEL("Toruviel", "/Assets/Cards/Plane/scoiatael_toruviel.jpg", "/Assets/Cards/Detailed/scoiatael_toruviel.jpg", 2, 1, Unit.RANGED, Factions.SCOIATAEL, Ability.NONE),
    VRIHEDD_BRIGADE_RECRUIT("Vrihedd Brigade Recruit", "/Assets/Cards/Plane/scoiatael_vrihedd_brigade_1.jpg", "/Assets/Cards/Detailed/scoiatael_vrihedd_brigade_1.jpg", 4, 1, Unit.RANGED, Factions.SCOIATAEL, Ability.NONE),
    MAHAKAMAN_DEFENDER("Mahakaman Defender", "/Assets/Cards/Plane/scoiatael_mahakam.jpg", "/Assets/Cards/Detailed/scoiatael_mahakam.jpg", 5, 5, Unit.CLOSE_COMBAT, Factions.SCOIATAEL, Ability.NONE),
    VRIHEDD_BRIGADE_VETERAN("Vrihedd Brigade Veteran", "/Assets/Cards/Plane/scoiatael_vrihedd_brigade.jpg", "/Assets/Cards/Detailed/scoiatael_vrihedd_brigade.jpg", 5, 2, Unit.AGILE, Factions.SCOIATAEL, Ability.NONE),
    MILVA("Milva", "/Assets/Cards/Plane/scoiatael_milva.jpg", "/Assets/Cards/Detailed/scoiatael_milva.jpg", 10, 1, Unit.RANGED, Factions.SCOIATAEL, Ability.MORALE_BOOST),
    SCHIRRU("Schirru", "/Assets/Cards/Plane/scoiatael_schirru.jpg", "/Assets/Cards/Detailed/scoiatael_schirru.jpg", 8, 1, Unit.SIEGE, Factions.SCOIATAEL, Ability.SCORCH),
    BARCLAY_ELS("Barclay Els", "/Assets/Cards/Plane/scoiatael_barclay.jpg", "/Assets/Cards/Detailed/scoiatael_barclay.jpg", 6, 1, Unit.AGILE, Factions.SCOIATAEL, Ability.NONE),
    BALLISTA("Ballista", "/Assets/Cards/Plane/realms_ballista.jpg", "/Assets/Cards/Detailed/realms_ballista.jpg", 6, 2, Unit.SIEGE, Factions.NORTHERN_REALMS, Ability.NONE),
    BLUE_STRIPES_COMMANDO("Blue Stripes Commando", "/Assets/Cards/Plane/realms_blue_stripes.jpg", "/Assets/Cards/Detailed/realms_blue_stripes.jpg", 4, 3, Unit.CLOSE_COMBAT, Factions.NORTHERN_REALMS, Ability.TIGHT_BOND),
    CATAPULT("Catapult", "/Assets/Cards/Plane/realms_catapult_1.jpg", "/Assets/Cards/Detailed/realms_catapult_1.jpg", 8, 2, Unit.SIEGE, Factions.NORTHERN_REALMS, Ability.TIGHT_BOND),
    DRAGON_HUNTER("Dragon Hunter", "/Assets/Cards/Plane/realms_crinfrid.jpg", "/Assets/Cards/Detailed/realms_crinfrid.jpg", 5, 3, Unit.RANGED, Factions.NORTHERN_REALMS, Ability.TIGHT_BOND),
    DETHMOLD("Dethmold", "/Assets/Cards/Plane/realms_dethmold.jpg", "/Assets/Cards/Detailed/realms_dethmold.jpg", 6, 1, Unit.RANGED, Factions.NORTHERN_REALMS, Ability.NONE),
    DUN_BANNER_MEDIC("Dun Banner Medic", "/Assets/Cards/Plane/realms_banner_nurse.jpg", "/Assets/Cards/Detailed/realms_banner_nurse.jpg", 5, 1, Unit.SIEGE, Factions.NORTHERN_REALMS, Ability.MEDIC),
    KAEDWENI_SIEGE_EXPERT("Kaedweni Siege Expert", "/Assets/Cards/Plane/realms_kaedwen_siege_2.jpg", "/Assets/Cards/Detailed/realms_kaedwen_siege_2.jpg", 1, 3, Unit.SIEGE, Factions.NORTHERN_REALMS, Ability.MORALE_BOOST),
    KEIRA_METZ("Keira Metz", "/Assets/Cards/Plane/realms_keira.jpg", "/Assets/Cards/Detailed/realms_keira.jpg", 5, 1, Unit.RANGED, Factions.NORTHERN_REALMS, Ability.NONE),
    POOR_FUCKING_INFANTRY("Poor Fucking Infantry", "/Assets/Cards/Plane/realms_poor_infantry.jpg", "/Assets/Cards/Detailed/realms_poor_infantry.jpg", 1, 4, Unit.CLOSE_COMBAT, Factions.NORTHERN_REALMS, Ability.TIGHT_BOND),
    PRINCE_STENNIS("Prince Stennis", "/Assets/Cards/Plane/realms_stennis.jpg", "/Assets/Cards/Detailed/realms_stennis.jpg", 5, 1, Unit.CLOSE_COMBAT, Factions.NORTHERN_REALMS, Ability.SPY),
    REDANIAN_FOOT_SOLDIER("Redanian Foot Soldier", "/Assets/Cards/Plane/realms_redania_1.jpg", "/Assets/Cards/Detailed/realms_redania_1.jpg", 1, 2, Unit.CLOSE_COMBAT, Factions.NORTHERN_REALMS, Ability.NONE),
    SABRINA_GLEVISSING("Sabrina Glevissing", "/Assets/Cards/Plane/realms_sabrina.jpg", "/Assets/Cards/Detailed/realms_sabrina.jpg", 4, 1, Unit.RANGED, Factions.NORTHERN_REALMS, Ability.NONE),
    SHELDON_SKAGGS("Sheldon Skaggs", "/Assets/Cards/Plane/realms_sheldon.jpg", "/Assets/Cards/Detailed/realms_sheldon.jpg", 4, 1, Unit.RANGED, Factions.NORTHERN_REALMS, Ability.NONE),
    SIEGE_TOWER("Siege Tower", "/Assets/Cards/Plane/realms_siege_tower.jpg", "/Assets/Cards/Detailed/realms_siege_tower.jpg", 6, 1, Unit.SIEGE, Factions.NORTHERN_REALMS, Ability.NONE),
    SIEGFRIED_OF_DENESLE("Siegfried of Denesle", "/Assets/Cards/Plane/realms_siegfried.jpg", "/Assets/Cards/Detailed/realms_siegfried.jpg", 5, 1, Unit.CLOSE_COMBAT, Factions.NORTHERN_REALMS, Ability.NONE),
    SIGISMUND_DIJKSTRA("Sigismund Dijkstra", "/Assets/Cards/Plane/realms_dijkstra.jpg", "/Assets/Cards/Detailed/realms_dijkstra.jpg", 4, 1, Unit.CLOSE_COMBAT, Factions.NORTHERN_REALMS, Ability.SPY),
    SILE_DE_TANSARVILLE("Síle de Tansarville", "/Assets/Cards/Plane/realms_sheala.jpg", "/Assets/Cards/Detailed/realms_sheala.jpg", 5, 1, Unit.RANGED, Factions.NORTHERN_REALMS, Ability.NONE),
    THALER("Thaler", "/Assets/Cards/Plane/realms_thaler.jpg", "/Assets/Cards/Detailed/realms_thaler.jpg", 1, 1, Unit.SIEGE, Factions.NORTHERN_REALMS, Ability.SPY),
    TREBUCHET("Trebuchet", "/Assets/Cards/Plane/realms_trebuchet_1.jpg", "/Assets/Cards/Detailed/realms_trebuchet_1.jpg", 6, 2, Unit.SIEGE, Factions.NORTHERN_REALMS, Ability.NONE),
    VES("Ves", "/Assets/Cards/Plane/realms_ves.jpg", "/Assets/Cards/Detailed/realms_ves.jpg", 5, 1, Unit.CLOSE_COMBAT, Factions.NORTHERN_REALMS, Ability.NONE),
    YARPEN_ZIGRIN("Yarpen Zigrin", "/Assets/Cards/Plane/realms_yarpen.jpg", "/Assets/Cards/Detailed/realms_yarpen.jpg", 2, 1, Unit.CLOSE_COMBAT, Factions.NORTHERN_REALMS, Ability.NONE),
    IMPERA_BRIGADE_GUARD("Impera Brigade Guard", "/Assets/Cards/Plane/nilfgaard_imperal_brigade.jpg", "/Assets/Cards/Detailed/nilfgaard_imperal_brigade.jpg", 3, 4, Unit.CLOSE_COMBAT, Factions.NILFGAARD, Ability.TIGHT_BOND),
    STEFAN_SKELLEN("Stefan Skellen", "/Assets/Cards/Plane/nilfgaard_stefan.jpg", "/Assets/Cards/Detailed/nilfgaard_stefan.jpg", 9, 1, Unit.CLOSE_COMBAT, Factions.NILFGAARD, Ability.SPY),
    SHILARD_FITZ_OESTERLEN("Shilard Fitz-Oesterlen", "/Assets/Cards/Plane/nilfgaard_shilard.jpg", "/Assets/Cards/Detailed/nilfgaard_shilard.jpg", 7, 1, Unit.CLOSE_COMBAT, Factions.NILFGAARD, Ability.SPY),
    YOUNG_EMISSARY("Young Emissary", "/Assets/Cards/Plane/nilfgaard_young_emissary.jpg", "/Assets/Cards/Detailed/nilfgaard_young_emissary.jpg", 5, 2, Unit.CLOSE_COMBAT, Factions.NILFGAARD, Ability.TIGHT_BOND),
    CAHIR_MAWR_DYFFRYN_AEP_CEALLACH("Cahir Mawr Dyffryn aep Ceallach", "/Assets/Cards/Plane/nilfgaard_cahir.jpg", "/Assets/Cards/Detailed/nilfgaard_cahir.jpg", 6, 1, Unit.CLOSE_COMBAT, Factions.NILFGAARD, Ability.NONE),
    VATTIER_DE_RIDEAUX("Vattier de Rideaux", "/Assets/Cards/Plane/nilfgaard_vattier.jpg", "/Assets/Cards/Detailed/nilfgaard_vattier.jpg", 4, 1, Unit.CLOSE_COMBAT, Factions.NILFGAARD, Ability.SPY),
    PUTTKAMMER("Puttkammer", "/Assets/Cards/Plane/nilfgaard_puttkammer.jpg", "/Assets/Cards/Detailed/nilfgaard_puttkammer.jpg", 3, 1, Unit.RANGED, Factions.NILFGAARD, Ability.NONE),
    ASSIRE_VAR_ANAHID("Assire var Anahid", "/Assets/Cards/Plane/nilfgaard_assire.jpg", "/Assets/Cards/Detailed/nilfgaard_assire.jpg", 6, 1, Unit.RANGED, Factions.NILFGAARD, Ability.NONE),
    BLACK_INFANTRY_ARCHER("Black Infantry Archer", "/Assets/Cards/Plane/nilfgaard_black_archer_1.jpg", "/Assets/Cards/Detailed/nilfgaard_black_archer_1.jpg", 10, 2, Unit.RANGED, Factions.NILFGAARD, Ability.NONE),
    RENUALD_AEP_MATSEN("Renuald aep Matsen", "/Assets/Cards/Plane/nilfgaard_renuald.jpg", "/Assets/Cards/Detailed/nilfgaard_renuald.jpg", 5, 1, Unit.RANGED, Factions.NILFGAARD, Ability.NONE),
    FRINGILLA_VIGO("Fringilla Vigo", "/Assets/Cards/Plane/nilfgaard_fringilla.jpg", "/Assets/Cards/Detailed/nilfgaard_fringilla.jpg", 6, 1, Unit.RANGED, Factions.NILFGAARD, Ability.NONE),
    ROTTEN_MANGONEL("Rotten Mangonel", "/Assets/Cards/Plane/nilfgaard_rotten.jpg", "/Assets/Cards/Detailed/nilfgaard_rotten.jpg", 3, 1, Unit.SIEGE, Factions.NILFGAARD, Ability.NONE),
    HEAVY_ZERRIKANIAN_FIRE_SCORPION("Heavy Zerrikanian Fire Scorpion", "/Assets/Cards/Plane/nilfgaard_heavy_zerri.jpg", "/Assets/Cards/Detailed/nilfgaard_heavy_zerri.jpg", 10, 1, Unit.SIEGE, Factions.NILFGAARD, Ability.NONE),
    ZERRIKANIAN_FIRE_SCORPION("Zerrikanian Fire Scorpion", "/Assets/Cards/Plane/nilfgaard_zerri.jpg", "/Assets/Cards/Detailed/nilfgaard_zerri.jpg", 5, 1, Unit.SIEGE, Factions.NILFGAARD, Ability.NONE),
    SIEGE_ENGINEER("Siege Engineer", "/Assets/Cards/Plane/nilfgaard_siege_engineer.jpg", "/Assets/Cards/Detailed/nilfgaard_siege_engineer.jpg", 6, 1, Unit.SIEGE, Factions.NILFGAARD, Ability.NONE),
    ALBRICH("Albrich", "/Assets/Cards/Plane/nilfgaard_albrich.jpg", "/Assets/Cards/Detailed/nilfgaard_albrich.jpg", 2, 1, Unit.RANGED, Factions.NILFGAARD, Ability.NONE),
    CYNTHIA("Cynthia", "/Assets/Cards/Plane/nilfgaard_cynthia.jpg", "/Assets/Cards/Detailed/nilfgaard_cynthia.jpg", 4, 1, Unit.RANGED, Factions.NILFGAARD, Ability.NONE),
    ETOLIAN_AUXILIARY_ARCHERS("Etolian Auxiliary Archers", "/Assets/Cards/Plane/nilfgaard_archer_support.jpg", "/Assets/Cards/Detailed/nilfgaard_archer_support.jpg", 1, 2, Unit.RANGED, Factions.NILFGAARD, Ability.MEDIC),
    MORTEISEN("Morteisen", "/Assets/Cards/Plane/nilfgaard_morteisen.jpg", "/Assets/Cards/Detailed/nilfgaard_morteisen.jpg", 3, 1, Unit.CLOSE_COMBAT, Factions.NILFGAARD, Ability.NONE),
    NAUSICAA_CAVALRY_RIDER("Nausicaa Cavalry Rider", "/Assets/Cards/Plane/nilfgaard_nauzicaa_2.jpg", "/Assets/Cards/Detailed/nilfgaard_nauzicaa_2.jpg", 2, 3, Unit.CLOSE_COMBAT, Factions.NILFGAARD, Ability.TIGHT_BOND),
    RAINFARN("Rainfarn", "/Assets/Cards/Plane/nilfgaard_rainfarn.jpg", "/Assets/Cards/Detailed/nilfgaard_rainfarn.jpg", 4, 1, Unit.CLOSE_COMBAT, Factions.NILFGAARD, Ability.NONE),
    SIEGE_TECHNICIAN("Siege Technician", "/Assets/Cards/Plane/nilfgaard_siege_support.jpg", "/Assets/Cards/Detailed/nilfgaard_siege_support.jpg", 0, 1, Unit.SIEGE, Factions.NILFGAARD, Ability.MEDIC),
    SWEERS("Sweers", "/Assets/Cards/Plane/nilfgaard_sweers.jpg", "/Assets/Cards/Detailed/nilfgaard_sweers.jpg", 2, 1, Unit.RANGED, Factions.NILFGAARD, Ability.NONE),
    VANHEMAR("Vanhemar", "/Assets/Cards/Plane/nilfgaard_vanhemar.jpg", "/Assets/Cards/Detailed/nilfgaard_vanhemar.jpg", 4, 1, Unit.RANGED, Factions.NILFGAARD, Ability.NONE),
    VREEMDE("Vreemde", "/Assets/Cards/Plane/nilfgaard_vreemde.jpg", "/Assets/Cards/Detailed/nilfgaard_vreemde.jpg", 2, 1, Unit.CLOSE_COMBAT, Factions.NILFGAARD, Ability.NONE),
    TOAD("Toad", "/Assets/Cards/Plane/monsters_toad.jpg", "/Assets/Cards/Detailed/monsters_toad.jpg", 7, 1, Unit.RANGED, Factions.MONSTERS, Ability.SCORCH),
    ARACHAS_BEHEMOTH("Arachas Behemoth", "/Assets/Cards/Plane/monsters_arachas_behemoth.jpg", "/Assets/Cards/Detailed/monsters_arachas_behemoth.jpg", 6, 1, Unit.SIEGE, Factions.MONSTERS, Ability.MUSTER),
    CRONE_BREWESS("Crone: Brewess", "/Assets/Cards/Plane/monsters_witch_velen.jpg", "/Assets/Cards/Detailed/monsters_witch_velen.jpg", 6, 1, Unit.CLOSE_COMBAT, Factions.MONSTERS, Ability.MUSTER),
    CRONE_WEAVESS("Crone: Weavess", "/Assets/Cards/Plane/monsters_witch_velen_1.jpg", "/Assets/Cards/Detailed/monsters_witch_velen_1.jpg", 6, 1, Unit.CLOSE_COMBAT, Factions.MONSTERS, Ability.MUSTER),
    CRONE_WHISPESS("Crone: Whispess", "/Assets/Cards/Plane/monsters_witch_velen_2.jpg", "/Assets/Cards/Detailed/monsters_witch_velen_2.jpg", 6, 1, Unit.CLOSE_COMBAT, Factions.MONSTERS, Ability.MUSTER),
    EARTH_ELEMENTAL("Earth Elemental", "/Assets/Cards/Plane/monsters_earth_elemental.jpg", "/Assets/Cards/Detailed/monsters_earth_elemental.jpg", 6, 1, Unit.SIEGE, Factions.MONSTERS, Ability.NONE),
    FIEND("Fiend", "/Assets/Cards/Plane/monsters_fiend.jpg", "/Assets/Cards/Detailed/monsters_fiend.jpg", 6, 1, Unit.CLOSE_COMBAT, Factions.MONSTERS, Ability.NONE),
    FIRE_ELEMENTAL("Fire Elemental", "/Assets/Cards/Plane/monsters_fire_elemental.jpg", "/Assets/Cards/Detailed/monsters_fire_elemental.jpg", 6, 1, Unit.SIEGE, Factions.MONSTERS, Ability.NONE),
    FORKTAIL("Forktail", "/Assets/Cards/Plane/monsters_forktail.jpg", "/Assets/Cards/Detailed/monsters_forktail.jpg", 5, 1, Unit.CLOSE_COMBAT, Factions.MONSTERS, Ability.NONE),
    FRIGHTENER("Frightener", "/Assets/Cards/Plane/monsters_frightener.jpg", "/Assets/Cards/Detailed/monsters_frightener.jpg", 5, 1, Unit.CLOSE_COMBAT, Factions.MONSTERS, Ability.NONE),
    GRAVE_HAG("Grave Hag", "/Assets/Cards/Plane/monsters_gravehag.jpg", "/Assets/Cards/Detailed/monsters_gravehag.jpg", 5, 1, Unit.RANGED, Factions.MONSTERS, Ability.NONE),
    GRIFFIN("Griffin", "/Assets/Cards/Plane/monsters_gryffin.jpg", "/Assets/Cards/Detailed/monsters_gryffin.jpg", 5, 1, Unit.CLOSE_COMBAT, Factions.MONSTERS, Ability.NONE),
    ICE_GIANT("Ice Giant", "/Assets/Cards/Plane/monsters_frost_giant.jpg", "/Assets/Cards/Detailed/monsters_frost_giant.jpg", 5, 1, Unit.SIEGE, Factions.MONSTERS, Ability.NONE),
    PLAGUE_MAIDEN("Plague Maiden", "/Assets/Cards/Plane/monsters_mighty_maiden.jpg", "/Assets/Cards/Detailed/monsters_mighty_maiden.jpg", 5, 1, Unit.CLOSE_COMBAT, Factions.MONSTERS, Ability.NONE),
    VAMPIRE_KATAKAN("Vampire: Katakan", "/Assets/Cards/Plane/monsters_katakan.jpg", "/Assets/Cards/Detailed/monsters_katakan.jpg", 5, 1, Unit.CLOSE_COMBAT, Factions.MONSTERS, Ability.MUSTER),
    WEREWOLF("Werewolf", "/Assets/Cards/Plane/monsters_werewolf.jpg", "/Assets/Cards/Detailed/monsters_werewolf.jpg", 5, 1, Unit.CLOSE_COMBAT, Factions.MONSTERS, Ability.NONE),
    ARACHAS("Arachas", "/Assets/Cards/Plane/monsters_arachas_1.jpg", "/Assets/Cards/Detailed/monsters_arachas_1.jpg", 4, 3, Unit.CLOSE_COMBAT, Factions.MONSTERS, Ability.MUSTER),
    BOTCHLING("Botchling", "/Assets/Cards/Plane/monsters_poroniec.jpg", "/Assets/Cards/Detailed/monsters_poroniec.jpg", 4, 1, Unit.CLOSE_COMBAT, Factions.MONSTERS, Ability.NONE),
    VAMPIRE_BRUXA("Vampire: Bruxa", "/Assets/Cards/Plane/monsters_bruxa.jpg", "/Assets/Cards/Detailed/monsters_bruxa.jpg", 4, 1, Unit.CLOSE_COMBAT, Factions.MONSTERS, Ability.MUSTER),
    VAMPIRE_EKIMMARA("Vampire: Ekimmara", "/Assets/Cards/Plane/monsters_ekkima.jpg", "/Assets/Cards/Detailed/monsters_ekkima.jpg", 4, 1, Unit.CLOSE_COMBAT, Factions.MONSTERS, Ability.MUSTER),
    VAMPIRE_FLEDER("Vampire: Fleder", "/Assets/Cards/Plane/monsters_fleder.jpg", "/Assets/Cards/Detailed/monsters_fleder.jpg", 4, 1, Unit.CLOSE_COMBAT, Factions.MONSTERS, Ability.MUSTER),
    VAMPIRE_GARKAIN("Vampire: Garkain", "/Assets/Cards/Plane/monsters_garkain.jpg", "/Assets/Cards/Detailed/monsters_garkain.jpg", 4, 1, Unit.CLOSE_COMBAT, Factions.MONSTERS, Ability.MUSTER),
    CELAENO_HARPY("Celaeno Harpy", "/Assets/Cards/Plane/monsters_celaeno_harpy.jpg", "/Assets/Cards/Detailed/monsters_celaeno_harpy.jpg", 2, 1, Unit.AGILE, Factions.MONSTERS, Ability.NONE),
    COCKATRICE("Cockatrice", "/Assets/Cards/Plane/monsters_cockatrice.jpg", "/Assets/Cards/Detailed/monsters_cockatrice.jpg", 2, 1, Unit.RANGED, Factions.MONSTERS, Ability.NONE),
    ENDREGA("Endrega", "/Assets/Cards/Plane/monsters_endrega.jpg", "/Assets/Cards/Detailed/monsters_endrega.jpg", 2, 1, Unit.RANGED, Factions.MONSTERS, Ability.NONE),
    FOGLET("Foglet", "/Assets/Cards/Plane/monsters_fogling.jpg", "/Assets/Cards/Detailed/monsters_fogling.jpg", 2, 1, Unit.CLOSE_COMBAT, Factions.MONSTERS, Ability.NONE),
    GARGOYLE("Gargoyle", "/Assets/Cards/Plane/monsters_gargoyle.jpg", "/Assets/Cards/Detailed/monsters_gargoyle.jpg", 2, 1, Unit.RANGED, Factions.MONSTERS, Ability.NONE),
    HARPY("Harpy", "/Assets/Cards/Plane/monsters_harpy.jpg", "/Assets/Cards/Detailed/monsters_harpy.jpg", 2, 1, Unit.AGILE, Factions.MONSTERS, Ability.NONE),
    NEKKER("Nekker", "/Assets/Cards/Plane/monsters_nekker.jpg", "/Assets/Cards/Detailed/monsters_nekker.jpg", 2, 3, Unit.CLOSE_COMBAT, Factions.MONSTERS, Ability.MUSTER),
    WYVERN("Wyvern", "/Assets/Cards/Plane/monsters_wyvern.jpg", "/Assets/Cards/Detailed/monsters_wyvern.jpg", 2, 1, Unit.RANGED, Factions.MONSTERS, Ability.NONE),
    GHOUL("Ghoul", "/Assets/Cards/Plane/monsters_ghoul.jpg", "/Assets/Cards/Detailed/monsters_ghoul.jpg", 1, 3, Unit.CLOSE_COMBAT, Factions.MONSTERS, Ability.MUSTER),
    DANDELION("Dandelion", "/Assets/Cards/Plane/neutral_dandelion.jpg", "/Assets/Cards/Detailed/neutral_dandelion.jpg", 2, 1, Unit.CLOSE_COMBAT, Factions.NEUTRAL, Ability.COMMANDERS_HORN),
    COW("Cow", "/Assets/Cards/Plane/neutral_cow.jpg", "/Assets/Cards/Detailed/neutral_cow.jpg", 0, 1, Unit.RANGED, Factions.NEUTRAL, Ability.TRANSFORMER),
    EMIEL_REGIS("Emiel Regis", "/Assets/Cards/Plane/neutral_emiel.jpg", "/Assets/Cards/Detailed/neutral_emiel.jpg", 5, 1, Unit.CLOSE_COMBAT, Factions.NEUTRAL, Ability.NONE),
    GAUNTER_ODIMM("Gaunter O’Dimm", "/Assets/Cards/Plane/neutral_gaunter_odimm.jpg", "/Assets/Cards/Detailed/neutral_gaunter_odimm.jpg", 2, 1, Unit.SIEGE, Factions.NEUTRAL, Ability.MUSTER),
    GAUNTER_ODIMM_DARKNESS("Gaunter O’DImm Darkness", "/Assets/Cards/Plane/neutral_gaunter_odimm_darkness.jpg", "/Assets/Cards/Detailed/neutral_gaunter_odimm_darkness.jpg", 4, 3, Unit.RANGED, Factions.NEUTRAL, Ability.MUSTER),
    OLGIERD_VON_EVERC("Olgierd Von Everc", "/Assets/Cards/Plane/neutral_olgierd.jpg", "/Assets/Cards/Detailed/neutral_olgierd.jpg", 6, 1, Unit.AGILE, Factions.NEUTRAL, Ability.MORALE_BOOST),
    VESEMIR("Vesemir", "/Assets/Cards/Plane/neutral_vesemir.jpg", "/Assets/Cards/Detailed/neutral_vesemir.jpg", 6, 1, Unit.CLOSE_COMBAT, Factions.NEUTRAL, Ability.NONE),
    VILLENTRETENMERTH("Villentretenmerth", "/Assets/Cards/Plane/neutral_villen.jpg", "/Assets/Cards/Detailed/neutral_villen.jpg", 7, 1, Unit.CLOSE_COMBAT, Factions.NEUTRAL, Ability.SCORCH),
    ZOLTAN_CHIVAY("Zoltan Chivay", "/Assets/Cards/Plane/neutral_zoltan.jpg", "/Assets/Cards/Detailed/neutral_zoltan.jpg", 5, 1, Unit.CLOSE_COMBAT, Factions.NEUTRAL, Ability.NONE),
    DECOY("Decoy", "/Assets/Cards/Plane/special_decoy.jpg", "/Assets/Cards/Detailed/special_decoy.jpg",0,3,Unit.All,Factions.NEUTRAL,Ability.NONE);


    public final String name;
    public final String planeImage;
    public final String cardImage;
    public final int power;
    public final int maxCapacity;
    public final Unit unit;
    public final Factions faction;
    public final Ability ability;

    UnitCardInfo(String name, String planeImage, String cardImage, int power, int maxCapacity, Unit unit, Factions faction, Ability ability) {
        this.name = name;
        this.planeImage = planeImage;
        this.cardImage = cardImage;
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
