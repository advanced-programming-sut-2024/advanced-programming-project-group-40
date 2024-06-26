package enums;

public enum Ability {
    Test("test"),
    MARDROEME("Mardroeme"),
    TIGHT_BOND("Tight Bond"),
    MORAL_BOOST("Moral Boost"),
    TRANSFORMER("transformer"),
    SCORCH("scorch"),
    MUSTER("muster"),
    SPY("spy"),
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
