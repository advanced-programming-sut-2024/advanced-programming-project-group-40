package enums;

public enum Unit {
    CLOSE_COMBAT("Close Combat"),
    RANGED("Ranged"),
    SIEGE("Siege"),
    AGILE("Agile"),
    All("All"),
    ;
    private final String unit;

    Unit(String name) {
        this.unit = name;
    }
    @Override
    public String toString() {
        return unit;
    }
}
