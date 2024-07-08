package Tests;

import Server.Models.GameBoardVisualData;
import controllers.MenuController.GameMenuController;
import enums.cards.UnitCardInfo;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Game;
import models.MatchTable;
import models.User;
import models.cards.Card;
import models.cards.UnitCard;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javafx.application.*;
import views.GameView;

import java.util.ArrayList;
import java.util.Objects;

public class test {
    //Test should be called from the Main class
    static User user1 = new User("a", "a", "a", "a");
    static User user2 = new User("b", "b", "b", "b");
    static ArrayList<Card> deck1 = new ArrayList<>();
    static ArrayList<Card> deck2 = new ArrayList<>();
    static MatchTable matchTable;
    public static void test1() {

        matchTable = new MatchTable(user1, user2);
        GameBoardVisualData s = new GameBoardVisualData(matchTable,
                false, false, false, false, false);
        String json = s.toJSON();
        GameBoardVisualData d = GameBoardVisualData.deSerialize(json);
        assert s.equals(d);
    }
    public static void test2(){
        MatchTable matchTable1 = new MatchTable(user2,user1);
        matchTable = new MatchTable(user1,user2);
        GameBoardVisualData v1 = new GameBoardVisualData(matchTable1,
                false, false, false, false, false);
        GameBoardVisualData v2 = new GameBoardVisualData(matchTable,
                false, false, false, false, false);
        assert v1.toJSON().equals(v2.toJSON());

    }
    public static void test3(){
        UnitCard unitCard = new UnitCard(UnitCardInfo.CATAPULT);
        UnitCard unitCard2 = new UnitCard(UnitCardInfo.ARACHAS);
        UnitCard unitCard3 = new UnitCard(UnitCardInfo.DECOY);
        UnitCard unitCard4 = new UnitCard(UnitCardInfo.FIEND);
        UnitCard unitCard5 = new UnitCard(UnitCardInfo.NEKKER);
        UnitCard unitCard6 = new UnitCard(UnitCardInfo.NEKKER);
        deck1.add(unitCard);
        deck1.add(unitCard2);
        deck1.add(unitCard3);
        deck1.add(unitCard4);
        deck1.add(unitCard5);
        deck1.add(unitCard6);
        deck2.add(unitCard);
        deck2.add(unitCard2);
        deck2.add(unitCard3);
        deck2.add(unitCard4);
        deck2.add(unitCard5);
        deck2.add(unitCard6);
        user1.getDeckCards().addAll(deck1);
        user2.getDeckCards().addAll(deck2);
        matchTable = new MatchTable(user1,user2);
        try {
            GameMenuController.setMatchTable(matchTable);
            new GameView().start(Game.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
