package enums;

public enum Unit {
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
