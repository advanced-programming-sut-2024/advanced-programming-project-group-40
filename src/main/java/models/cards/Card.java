package models.cards;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.Objects;

public abstract class Card extends Rectangle {
    protected final String name;
    protected final Image planeImage;
    protected final Image cardImage;
    public Card(String name, URL cardImageURL, URL planeImageURL, Image planeImage, Image cardImage) {
        this.setWidth(65);
        this.setHeight(90);
        this.setFill(new Color(0.5,0.5,0.5,0.5));
        this.name = name;
        this.planeImage = planeImage;
        this.cardImage = cardImage;
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
}
