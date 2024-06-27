package models.UserInputHandler;

import javafx.css.Match;
import models.MatchTable;
import models.cards.Card;

import javafx.scene.input.MouseEvent;

public class ClickCommand extends Command {
    private final Card card;
    private final String parent;

    public ClickCommand(Card card, String parent) {
        this.card = card;
        this.parent = parent;
    }


    @Override
    public void excute() {
        System.out.println(card.getName());
        System.out.println(parent);
    }
}
