package models.cards;

import enums.Ability;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.Objects;

public abstract class Card extends Rectangle {
    protected final String name;
    protected final String planeImage;
    protected final String cardImage;
    public Card(String name, String planeImage, String cardImage) {
        this.planeImage = planeImage;
        this.cardImage = cardImage;
        this.setWidth(65);
        this.setHeight(90);
        setFill(new ImagePattern((new Image(Card.class.getResource(cardImage).toExternalForm()))));
        this.name = name;
    }

    public String getName() {
        return name;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card card)) return false;
        return Objects.equals(name, card.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public boolean isSpy() {
        if (this instanceof UnitCard unitCard){
            return unitCard.getAbility() == Ability.SPY;
        }
        if (this instanceof Hero hero){
            return hero.getAbility() == Ability.SPY;
        }
        return false;
    }

}
