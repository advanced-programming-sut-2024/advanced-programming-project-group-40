package enums;

public enum Unit {
    CLOSE_COMBAT("close combat"),
    RANGED("ranged"),
    SIEGE("siege"),
    AGILE("agile"),
    All("all"),
    Test("test")
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
