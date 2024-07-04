package models.cards;

import controllers.Generator;
import enums.Ability;
import enums.Factions;
import enums.cards.HeroInfo;
import enums.cards.LeaderInfo;
import enums.cards.SpecialCardInfo;
import enums.cards.UnitCardInfo;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import models.Game;

import java.net.URL;
import java.util.Objects;
import java.util.Random;

public abstract class Card extends Rectangle {
    private int cardNumber;
    protected final String name;
    protected final String planeImage;
    protected final String cardImage;

    protected final int maxCapacity;
    protected int selectedCards;
    private final Factions faction;
    private final int cardId;

    public Card(String name, String planeImage, String cardImage, int maxCapacity, Factions faction) {
        this.planeImage = planeImage;
        this.cardImage = cardImage;
        this.maxCapacity = maxCapacity;
        this.faction = faction;
        this.cardId = Game.random.nextInt(0,10000);
        this.setWidth(65);
        this.setHeight(90);
        setFill(new ImagePattern(new Image(Objects.requireNonNull(Card.class.getResource(cardImage)).toExternalForm())));
        this.name = name;
    }

    public String getName() {
        return name;
    }


    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public boolean isSpy() {
        if (this instanceof UnitCard unitCard) {
            return unitCard.getAbility() == Ability.SPY;
        }
        if (this instanceof Hero hero) {
            return hero.getAbility() == Ability.SPY;
        }
        return false;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void addToSelected() {
        this.selectedCards++;
    }

    public void removeFromSelected() {
        this.selectedCards--;
    }

    public void setSelectedCards(int selectedCards) {
        this.selectedCards = selectedCards;
    }

    public int getSelectedCards() {
        return selectedCards;
    }

    public static Card getCardByName(String name) {
        for (UnitCardInfo unitCardInfo : UnitCardInfo.values()) {
            if (unitCardInfo.name.equalsIgnoreCase(name)) {
                return new UnitCard(unitCardInfo);
            }
        }
        for (SpecialCardInfo specialCardInfo : SpecialCardInfo.values()) {
            if (specialCardInfo.name.equalsIgnoreCase(name)) {
                return new SpecialCard(specialCardInfo);
            }
        }
        for (HeroInfo heroInfo : HeroInfo.values()) {
            if (heroInfo.name.equalsIgnoreCase(name)) {
                return new Hero(heroInfo);
            }
        }
        for (LeaderInfo leaderInfo : LeaderInfo.values()) {
            if (leaderInfo.name.equalsIgnoreCase(name)) {
                return new Leader(leaderInfo);
            }
        }
        return null;
    }

    public Factions getFaction() {
        return faction;
    }
}

