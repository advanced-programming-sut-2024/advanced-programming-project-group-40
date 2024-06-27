package enums;

public enum Ability {
    NONE("None"),
    HERO("Hero"),
    MARDROEME("Mardroeme"),
    BERSERKER("Berserker"),
    MEDIC("Medic"),
    COMMANDERS_HORN("Commander Horn"),
    TIGHT_BOND("Tight Bond"),
    MORALE_BOOST("Moral Boost"),
    TRANSFORMER("Transformer"),
    SCORCH("Scorch"),
    MUSTER("Muster"),
    SPY("Spy"),
    ;
    private final String ability;


    Ability(String name) {
        this.ability = name;
    }

    @Override
    public String toString() {
        return ability;
    }
}
