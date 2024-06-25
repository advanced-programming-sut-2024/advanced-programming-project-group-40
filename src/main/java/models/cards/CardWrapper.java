package models.cards;

import enums.Origin;


//wraps the card with the data of its origin
public class CardWrapper {
    private final Card card;
    private final Origin origin;

    public CardWrapper(Card card, Origin origin) {
        this.card = card;
        this.origin = origin;
    }

    public Card getCard() {
        return card;
    }

    public Origin getOrigin() {
        return origin;
    }
}
