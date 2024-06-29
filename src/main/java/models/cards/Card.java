package models.cards;

import enums.Ability;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public abstract class Card extends Rectangle {
    protected final String name;

    public Card(String name) {
        this.setWidth(65);
        this.setHeight(90);
        this.setFill(new Color(0.5,0.5,0.5,0.5));
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
