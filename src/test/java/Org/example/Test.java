package Org.example;

import enums.Factions;
import enums.cards.UnitCardInfo;
import models.MatchTable;
import models.User;
import models.cards.Card;
import models.cards.Leader;
import models.cards.UnitCard;
import org.junit.Before;

public class Test {
    MatchTable matchTable;
    User user1;
    User user2;

    Card card1;
    Card card2;

    @Before
    public void before() {
        user1 = new User("a","a","a","a");
        user1 = new User("b","b","b","b");
    }

    @org.junit.Test
    public void test1() {
        card2 = new UnitCard(UnitCardInfo.CATAPULT);
        card1 = Card.getCardByName("Catapult");
        assert !card1.equals(card2);
    }

    @org.junit.Test
    public void test2() {
        card2 = new UnitCard(UnitCardInfo.PRINCE_STENNIS);
        assert card2.isSpy();
    }

    @org.junit.Test
    public void test3() {
        matchTable = new MatchTable(user1,user2);
        assert matchTable.getFirstPlayerDeckCards().isEmpty();
        assert matchTable.getSecondPlayerDeckCards().isEmpty();
    }

    @org.junit.Test
    public void test4() {
        assert  user1.getFaction() == Factions.MONSTERS;
    }

    @org.junit.Test
    public void test5() {
        assert  user1.getLeader() == Leader.getLeaderByName("Bringer of Death");
    }


}
