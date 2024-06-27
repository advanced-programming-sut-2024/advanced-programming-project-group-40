package models.cards;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public abstract class Card extends Rectangle {
    protected final String name;
    protected final String boast;
    protected final String detail;

    public Card(String name, String boast, String detail) {
        this.setWidth(65);
        this.setHeight(90);
        this.setFill(new Color(0.5,0.5,0.5,0.5));
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card card)) return false;
        return Objects.equals(name, card.name) && Objects.equals(boast, card.boast) && Objects.equals(detail, card.detail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, boast, detail);
    }
}
