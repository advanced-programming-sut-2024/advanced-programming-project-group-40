package enums.cards;

public enum FactionInfo {
    Monster("monsters"),
    Scoiatael("scoiatael"),
    Nilfgaard("nilfgaard"),
    Realms("realms"),
    Skellige("skellige"),
    ;

    public final String name;

    FactionInfo(String name) {
        this.name = name;
    }
}
