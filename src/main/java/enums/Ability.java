package enums;

public enum Ability {
    Test("test"),
    TIGHT_BOND("Tight Bond"),
    MORAL_BOOST("Moral Boost")

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
