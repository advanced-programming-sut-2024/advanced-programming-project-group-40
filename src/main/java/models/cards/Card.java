package models.cards;

public abstract class Card {
    protected final String name;
    protected final String boast;
    protected final String detail;

    public Card(String name, String boast, String detail) {
        this.name = name;
        this.boast = boast;
        this.detail = detail;
    }

    public String getName() {
        return name;
    }


    public String getBoast() {
        return boast;
    }

    public String getDetail() {
        return detail;
    }

}
