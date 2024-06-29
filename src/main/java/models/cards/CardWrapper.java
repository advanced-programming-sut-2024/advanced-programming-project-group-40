package models.cards;

import enums.Origin;


//wraps the card with the data of its origin

public record CardWrapper(Card card, Origin origin) {
}
