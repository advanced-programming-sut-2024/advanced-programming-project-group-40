package views.ViewController;

import enums.cards.UnitCardInfo;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import models.Game;
import models.cards.Card;
import models.cards.UnitCard;
import views.GameView;

public class PreGameViewController {

    public FlowPane selectCard;

    public void initialize(){
        selectCard.setHgap(10);
        for (Card card : Game.getAllCards()){
            card.setWidth(113);
            card.setHeight(169.5);
            selectCard.getChildren().add(card);
        }
    }
}
