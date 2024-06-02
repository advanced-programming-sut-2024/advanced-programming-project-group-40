package enums;

public enum Ability {
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
