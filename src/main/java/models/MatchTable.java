package models;

import enums.Ability;
import enums.Factions;
import enums.Origin;
import enums.cards.LeaderInfo;
import enums.cards.UnitCardInfo;
import models.actions.FactionActions;
import models.actions.LeaderActions;
import models.actions.UnitCardActions;
import models.cards.*;

import java.util.*;

public class MatchTable {
    private final User firstPlayer;
    private final User secondPlayer;
    private boolean isFirstPlayerTurn;
    private boolean secondPlayerPassed = false;
    private boolean firstPlayerPassed = false;
    private int round = 1;
    private Date date;
    private final LeaderEffects leaderEffects = new LeaderEffects();

    private int firstPlayerCurrentPoint;
    private int secondPlayerCurrentPoint;
    private final ArrayList<Integer> firstPlayerRowPoints = new ArrayList<>(Arrays.asList(0, 0, 0));
    private final ArrayList<Integer> secondPlayerRowPoints = new ArrayList<>(Arrays.asList(0, 0, 0));
    private final ArrayList<Card> firstPlayerDeckCards = new ArrayList<>();
    private final ArrayList<Card> secondPlayerDeckCards = new ArrayList<>();
    private final ArrayList<Card> firstPlayerCloseCombatRow = new ArrayList<>();
    private Card firstPlayerCloseCombatBoostCard;
    private final ArrayList<Card> secondPlayerCloseCombatRow = new ArrayList<>();
    private Card secondPlayerCloseCombatBoostCard;

    private final ArrayList<Card> firstPlayerRangedRow = new ArrayList<>();
    private Card firstPlayerRangedBoostCard;
    private final ArrayList<Card> secondPlayerRangedRow = new ArrayList<>();
    private Card secondPlayerRangedBoostCard;
    private final ArrayList<Card> firstPlayerSiegeRow = new ArrayList<>();
    private Card firstPlayerSiegeBoostCard;
    private final ArrayList<Card> secondPlayerSiegeRow = new ArrayList<>();
    private Card secondPlayerSiegeBoostCard;


    private final ArrayList<Card> firstPlayerDeadCards = new ArrayList<>();
    private final ArrayList<Card> secondPlayerDeadCards = new ArrayList<>();
    private final ArrayList<Card> firstPlayerInPlayCards = new ArrayList<>();
    private final ArrayList<Card> secondPlayerInPlayCards = new ArrayList<>();
    private int firstPlayerCrystals = 2;
    private int secondPlayerCrystals = 2;
    private final ArrayList<Card> spellCards = new ArrayList<>();
    private Leader firstPlayerLeader;
    private Leader secondPlayerLeader;
    private boolean isFirstPlayerLeaderUsed = false;
    private boolean isSecondPlayerLeaderUsed = false;
    private boolean isStrongerCheatActivated = false;

    public MatchTable(User firstPlayer, User secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        firstPlayerDeckCards.addAll(firstPlayer.getDeckCards());
        secondPlayerDeckCards.addAll(secondPlayer.getDeckCards());
        initializeMatchTable();
    }


    //cheats
    public void makeCardsStronger() {
        isStrongerCheatActivated = true;
    }

    public void GiveBackLeaderAbility(int userID) {
        switch (userID) {
            case 0:
                isFirstPlayerLeaderUsed = false;
                break;
            case 1:
                isSecondPlayerLeaderUsed = false;
        }
    }

    public void giveBackCrystal(int userID) {
        if (userID == 0) {
            if (firstPlayerCrystals != 2) firstPlayerCrystals++;
        } else {
            if (secondPlayerCrystals != 2) secondPlayerCrystals++;
        }
    }

    public void clearWeather() {
        spellCards.clear();

    }

    public void skipTurn() {
        endTurn();
    }

    public boolean isFirstPlayerTurn() {
        return isFirstPlayerTurn;
    }

    //firstPlayer -> id=0
    //secondPlayer -> id=1
    public int getPlayerTotalScore(int user_id) {
        int retVal = 0;
        for (int i = 0; i < 3; i++) {
            retVal += getPlayerRowScore(user_id, i);
        }

        return retVal;
    }

    //firstPlayer -> id=0
    //secondPlayer -> id=1
    // closeCombat:0 Range:1 Siege:2
    public int getPlayerRowScore(int user_id, int rowNumber) {
        int retVal = 0;
        ArrayList<Card> row = getRowByID(user_id, rowNumber);
        for (Card card : row) {
            if (card != null) {
                retVal += ((UnitCard) card).getShowingPower();
            }
        }
        return retVal;
    }

    public void doDecoy(CardWrapper decoy, CardWrapper cardToSwap, boolean isFirstPlayerTurn) {
        if (isFirstPlayerTurn) {
            this.placeCard(decoy
                    , 0
                    , getRowID(cardToSwap.getOrigin()));

            this.addToInPlayCards(0, cardToSwap);
        } else {
            this.placeCard(decoy
                    , 1
                    , getRowID(cardToSwap.getOrigin()));

            this.addToInPlayCards(1, cardToSwap);
        }


    }

    private static int getRowID(Origin origin) {
        switch (origin) {
            case FIRSTPLAYER_CLOSECOMBAT, SECONDPLAYER_CLOSECOMBAT -> {
                return 0;
            }
            case FIRSTPLAYER_RANGED, SECONDPLAYER_RANGED -> {
                return 1;
            }
            case FIRSTPLAYER_SIEGE, SECONDPLAYER_SIEGE -> {
                return 2;
            }
        }
        return -1;
    }

    public void setPlayerRowScore(int user_id, int rowNumber) {
        boolean areCardsUnderWeather = isRowUnderWeather(rowNumber);
        boolean areCardsBoosted = isRowUnderBoost(user_id, rowNumber);
        ArrayList<Card> row = getRowByID(user_id, rowNumber);
        ArrayList<Card> tightBondCards = getCardsWithAbility(Ability.TIGHT_BOND, row);
        ArrayList<Card> moralBoostCards = getCardsWithAbility(Ability.MORALE_BOOST, row);
        for (Card card : row) {
            if (card instanceof UnitCard unitCard) {
                unitCard.setShowingPower(unitCard.getConstantPower());
            }
            if (card instanceof Hero hero) {
                hero.setShowingPower(hero.getConstantPower());
            }
        }
        if (areCardsUnderWeather) {
            for (Card card : row) {
                if (card instanceof UnitCard unitCard) {
                    if (leaderEffects.isKingBran()) {
                        unitCard.setShowingPower(unitCard.getShowingPower() / 2);
                    } else {
                        unitCard.setShowingPower(1);
                    }
                }
                if (card instanceof Hero hero) {
                    if (leaderEffects.isKingBran()) {
                        hero.setShowingPower(hero.getShowingPower() / 2);
                    } else {
                        hero.setShowingPower(1);
                    }
                }
            }
        }
        if (leaderEffects.isSpyDoublePower()) {
            for (Card card : row) {
                if (card instanceof UnitCard unitCard) {
                    if (unitCard.getAbility() == Ability.SPY) {
                        unitCard.setShowingPower(unitCard.getShowingPower() * 2);
                    }
                }
                if (card instanceof Hero hero) {
                    if (hero.getAbility() == Ability.SPY) {
                        hero.setShowingPower(hero.getShowingPower() * 2);
                    }
                }
            }
        }

        for (Card card : row) {
            if (card instanceof UnitCard unitCard) {
                if (tightBondCards.contains(card)) {
                    unitCard.setShowingPower(unitCard.getShowingPower() * getNumberOfCards(card, tightBondCards));
                }
            }
        }
        for (Card card : row) {
            if (card instanceof UnitCard unitCard) {
                if (moralBoostCards.contains(card)) {
                    unitCard.setShowingPower(unitCard.getShowingPower() + moralBoostCards.size() - 1);
                } else {
                    unitCard.setShowingPower(unitCard.getShowingPower() + moralBoostCards.size());
                }
            }
            if (card instanceof Hero hero) {
                if (moralBoostCards.contains(card)) {
                    hero.setShowingPower(hero.getShowingPower() + moralBoostCards.size() - 1);
                } else {
                    hero.setShowingPower(hero.getShowingPower() + moralBoostCards.size());
                }
            }
        }
        if (areCardsBoosted) {
            for (Card card : row) {
                if (card instanceof UnitCard unitCard) {
                    unitCard.setShowingPower(unitCard.getShowingPower() * 2);
                }
            }
        }
        if (user_id == 0 && isStrongerCheatActivated) {
            for (Card card : row) {
                if (card instanceof UnitCard unitCard) {
                    unitCard.setShowingPower(unitCard.getConstantPower() * 10);
                }
                if (card instanceof Hero hero) {
                    hero.setShowingPower(hero.getConstantPower() * 10);
                }
            }
        }
    }


    public Date getDate() {
        return date;
    }

    public LeaderEffects getLeaderEffects() {
        return leaderEffects;
    }


    public Card getFirstPlayerCloseCombatBoostCard() {
        return firstPlayerCloseCombatBoostCard;
    }

    public Card getSecondPlayerCloseCombatBoostCard() {
        return secondPlayerCloseCombatBoostCard;
    }

    public Card getFirstPlayerRangedBoostCard() {
        return firstPlayerRangedBoostCard;
    }

    public Card getSecondPlayerRangedBoostCard() {
        return secondPlayerRangedBoostCard;
    }

    public Card getFirstPlayerSiegeBoostCard() {
        return firstPlayerSiegeBoostCard;
    }

    public Card getSecondPlayerSiegeBoostCard() {
        return secondPlayerSiegeBoostCard;
    }


    public void setFirstPlayerLeaderUsed(boolean firstPlayerLeaderUsed) {
        isFirstPlayerLeaderUsed = firstPlayerLeaderUsed;
    }

    public void setSecondPlayerLeaderUsed(boolean secondPlayerLeaderUsed) {
        isSecondPlayerLeaderUsed = secondPlayerLeaderUsed;
    }


    public int getFirstPlayerCurrentPoint() {
        return firstPlayerCurrentPoint;
    }

    public int getSecondPlayerCurrentPoint() {
        return secondPlayerCurrentPoint;
    }

    public ArrayList<Card> getFirstPlayerCloseCombatRow() {
        return firstPlayerCloseCombatRow;
    }

    public ArrayList<Card> getSecondPlayerCloseCombatRow() {
        return secondPlayerCloseCombatRow;
    }

    public ArrayList<Card> getFirstPlayerRangedRow() {
        return firstPlayerRangedRow;
    }

    public ArrayList<Card> getSecondPlayerRangedRow() {
        return secondPlayerRangedRow;
    }

    public ArrayList<Card> getFirstPlayerSiegeRow() {
        return firstPlayerSiegeRow;
    }

    public ArrayList<Card> getSecondPlayerSiegeRow() {
        return secondPlayerSiegeRow;
    }

    public ArrayList<Card> getFirstPlayerDeadCards() {
        return firstPlayerDeadCards;
    }

    public ArrayList<Card> getSecondPlayerDeadCards() {
        return secondPlayerDeadCards;
    }

    public ArrayList<Card> getFirstPlayerInPlayCards() {
        return firstPlayerInPlayCards;
    }

    public ArrayList<Card> getFirstPlayerDeckCards() {
        return firstPlayerDeckCards;
    }

    public ArrayList<Card> getSecondPlayerDeckCards() {
        return secondPlayerDeckCards;
    }

    public User getFirstPlayer() {
        return firstPlayer;
    }

    public User getSecondPlayer() {
        return secondPlayer;
    }

    public ArrayList<Card> getSecondPlayerInPlayCards() {
        return secondPlayerInPlayCards;
    }

    public int getFirstPlayerCrystals() {
        return firstPlayerCrystals;
    }

    public int getSecondPlayerCrystals() {
        return secondPlayerCrystals;
    }

    public ArrayList<Card> getSpellCards() {
        return spellCards;
    }

    public Leader getFirstPlayerLeader() {
        return firstPlayerLeader;
    }

    public Leader getSecondPlayerLeader() {
        return secondPlayerLeader;
    }

    public ArrayList<Integer> getFirstPlayerRowPoints() {
        return firstPlayerRowPoints;
    }

    public ArrayList<Integer> getSecondPlayerRowPoints() {
        return secondPlayerRowPoints;
    }

    //gives some random card without removing them from the deck
    public static ArrayList<Card> randomSelectedCards(ArrayList<Card> deck, int numOfRandomCards) {
        ArrayList<Card> randomCards = new ArrayList<>();
        int i = 0;
        int size = deck.size();
        while (i < numOfRandomCards) {
            if (deck.isEmpty() || deck.size() < numOfRandomCards) {
                return randomCards;
            }
            Card tempCard = deck.get(Game.random.nextInt(size));
            if (!randomCards.contains(tempCard)) {
                randomCards.add(tempCard);
                i++;
            }
        }
        return randomCards;
    }


    //places card and acivates ability
    public void placeCard(CardWrapper cardWrapper, int userID, int rowNumber) {
        ArrayList<Card> row = getRowByID(userID, rowNumber);


        Ability ability = null;
        if (cardWrapper.getCard() instanceof Hero hero) {
            ability = hero.getAbility();
        } else if (cardWrapper.getCard() instanceof UnitCard unitCard) {
            ability = unitCard.getAbility();
        }
        if (ability != null) {
            switch (ability) {
                case SPY -> {
                    UnitCardActions.doActionByName("spy", this);
                    int inverseUserID = -1;
                    if (userID == 1) inverseUserID = 0;
                    else inverseUserID = 1;
                    row = getRowByID(inverseUserID, rowNumber);
                    row.add(cardWrapper.getCard());
                    removeCard(cardWrapper);
                }
                case MUSTER -> {
                    row.add(cardWrapper.getCard());
                    removeCard(cardWrapper);

                    UnitCardActions.doActionWhenPlaced(cardWrapper.getCard(), userID, rowNumber, "muster", this);
                }
                case SCORCH -> {
                    row.add(cardWrapper.getCard());
                    UnitCardActions.doActionWhenPlaced(cardWrapper.getCard(), userID, rowNumber, "scorch", this);
                }
                default -> {
                    row.add(cardWrapper.getCard());
                }
            }
            removeCard(cardWrapper);
        } else {
            row.add(cardWrapper.getCard());
            removeCard(cardWrapper);
        }
    }


    //places card without acivating ability
    public void placeCardNoAbility(CardWrapper cardWrapper, int userID, int rowNumber) {
        ArrayList<Card> row = getRowByID(userID, rowNumber);
        row.add(cardWrapper.getCard());
        removeCard(cardWrapper);
    }

    public void removeCard(CardWrapper cardWrapper) {
        switch (cardWrapper.getOrigin()) {
            case Origin.FIRSTPLAYER_CLOSECOMBAT:
                firstPlayerCloseCombatRow.remove(cardWrapper.getCard());
                break;
            case Origin.FIRSTPLAYER_RANGED:
                firstPlayerRangedRow.remove(cardWrapper.getCard());
                break;
            case Origin.FIRSTPLAYER_SIEGE:
                firstPlayerSiegeRow.remove(cardWrapper.getCard());
                break;
            case Origin.SECONDPLAYER_CLOSECOMBAT:
                secondPlayerCloseCombatRow.remove(cardWrapper.getCard());
                break;
            case Origin.SECONDPLAYER_RANGED:
                secondPlayerRangedRow.remove(cardWrapper.getCard());

                break;
            case Origin.SECONDPLAYER_SIEGE:
                secondPlayerSiegeRow.remove(cardWrapper.getCard());

                break;
            case Origin.FIRSTPLATER_DEAD:
                firstPlayerDeadCards.remove(cardWrapper.getCard());

                break;
            case Origin.SECONDPLAYER_DEAD:
                secondPlayerDeadCards.remove(cardWrapper.getCard());

                break;
            case Origin.FIRSTPLAYER_INPLAY:
                firstPlayerInPlayCards.remove(cardWrapper.getCard());
                break;
            case Origin.SECONDPLAYER_INPLAY:
                secondPlayerInPlayCards.remove(cardWrapper.getCard());

                break;
            case Origin.FIRSTPLAYER_DECK:
                firstPlayerDeckCards.remove(cardWrapper.getCard());

                break;
            case Origin.SECONDPLAYER_DECK:
                secondPlayerDeckCards.remove(cardWrapper.getCard());

                break;
            case Origin.WEATHER:
                spellCards.remove(cardWrapper.getCard());
                break;
            default:

        }
    }

    //places card
    public void placeBoostCard(CardWrapper cardWrapper, int userID, int rowNumber) {
        switch (cardWrapper.getCard().getName()) {
            case "Commander’s horn":
                switch (userID) {
                    case 0:
                        switch (rowNumber) {
                            case 0:
                                firstPlayerCloseCombatBoostCard = cardWrapper.getCard();
                                break;
                            case 1:
                                firstPlayerRangedBoostCard = cardWrapper.getCard();
                                break;
                            case 2:
                                firstPlayerSiegeBoostCard = cardWrapper.getCard();
                                break;
                        }
                        break;
                    case 1:
                        switch (rowNumber) {
                            case 0:
                                secondPlayerCloseCombatBoostCard = cardWrapper.getCard();
                                break;
                            case 1:
                                secondPlayerRangedBoostCard = cardWrapper.getCard();
                                break;
                            case 2:
                                secondPlayerSiegeBoostCard = cardWrapper.getCard();

                                break;
                        }
                        break;
                }
                removeCard(cardWrapper);
                break;
            case "Mardroeme":
                applyMardroeme(userID, rowNumber);
                removeCard(cardWrapper);
                break;
            default:

        }
    }

    public void placeBoostCard(Card card, int userID, int rowNumber) {
        switch (card.getName()) {
            case "Commander's Horn":
                switch (userID) {
                    case 0:
                        switch (rowNumber) {
                            case 0:
                                firstPlayerCloseCombatBoostCard = card;
                                break;
                            case 1:
                                firstPlayerRangedBoostCard = card;
                                break;
                            case 2:
                                firstPlayerSiegeBoostCard = card;
                                break;
                        }
                        break;
                    case 1:
                        switch (rowNumber) {
                            case 0:
                                secondPlayerCloseCombatBoostCard = card;
                                break;
                            case 1:
                                secondPlayerRangedBoostCard = card;
                                break;
                            case 2:
                                secondPlayerSiegeBoostCard = card;

                                break;
                        }
                        break;
                }
                break;
            case "Mardroeme":
                applyMardroeme(userID, rowNumber);
                break;
            default:

        }
    }

    //places card in spell cards
    public void addToSpellCards(CardWrapper cardWrapper) {
        if (Objects.equals(cardWrapper.getCard().getName(), "Clear Weather")) {
            spellCards.add(cardWrapper.getCard());
            removeCard(cardWrapper);
            int id = 0;
            if (!isFirstPlayerTurn) id = 1;
            while (!spellCards.isEmpty()) {
                addToDeadCards(id, new CardWrapper(spellCards.getLast(), Origin.WEATHER));
            }
        } else if (Objects.equals(cardWrapper.getCard().getName(), "Scorch")) {
            removeCard(cardWrapper);
            UnitCardActions.doActionWhenPlaced(cardWrapper.getCard(), -1, -1, "scorch", this);

        } else {
            spellCards.add(cardWrapper.getCard());
            removeCard(cardWrapper);
        }
    }

    //places card to inplay cards
    public void addToInPlayCards(int userID, CardWrapper cardWrapper) {
        switch (userID) {
            case 0:
                firstPlayerInPlayCards.add(cardWrapper.getCard());
                break;
            case 1:
                secondPlayerInPlayCards.add(cardWrapper.getCard());
                break;
        }
        removeCard(cardWrapper);
    }

    //places card to deck cards
    public void addToDeckCards(int userID, CardWrapper cardWrapper) {
        switch (userID) {
            case 0:
                firstPlayerDeckCards.add(cardWrapper.getCard());
                break;
            case 1:
                secondPlayerDeckCards.add(cardWrapper.getCard());
                break;
        }
        removeCard(cardWrapper);
    }

    //places card to dead cards
    public void addToDeadCards(int userID, Card card) {
        switch (userID) {
            case 0:
                firstPlayerDeadCards.add(card);
                break;
            case 1:
                secondPlayerDeadCards.add(card);
                break;
        }
    }

    public void addToDeadCards(int userID, CardWrapper cardWrapper) {
        switch (userID) {
            case 0:
                firstPlayerDeadCards.add(cardWrapper.getCard());
                break;
            case 1:
                secondPlayerDeadCards.add(cardWrapper.getCard());
                break;
        }
        removeCard(cardWrapper);
    }


    public void updatePoints() {
        //update everything
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                setPlayerRowScore(i, j);
            }
        }
        for (int i = 0; i < 3; i++) {
            firstPlayerRowPoints.set(i, getPlayerRowScore(0, i));
            secondPlayerRowPoints.set(i, getPlayerRowScore(1, i));
        }
        firstPlayerCurrentPoint = getPlayerTotalScore(0);
        secondPlayerCurrentPoint = getPlayerTotalScore(1);
    }


    public void leaderAction() {
        if (isFirstPlayerTurn) {
            if (!isFirstPlayerLeaderUsed) LeaderActions.doActionByName(firstPlayerLeader.getName(), this);
        } else {
            if (!isSecondPlayerLeaderUsed) LeaderActions.doActionByName(secondPlayerLeader.getName(), this);
        }
    }

    public void factionAction(int playerID, Factions faction) {
        FactionActions.doActionByName(playerID, faction.name(), this);
    }


    public void endTurn() {
        if (isFirstPlayerTurn) {
            if (!secondPlayerPassed) isFirstPlayerTurn = false;

        } else {
            if (!firstPlayerPassed) isFirstPlayerTurn = true;
        }

    }

    public void pass(int userID) {
        switch (userID) {
            case 0:
                firstPlayerPassed = true;
                break;
            case 1:
                secondPlayerPassed = true;
                break;
        }
        if (isRoundFinished()) finishRound();
    }

    private void finishRound() {
        round++;
        int firstPlayerScore = getPlayerTotalScore(0);
        int secondPlayerScore = getPlayerTotalScore(1);
        if (firstPlayerScore == secondPlayerScore) {
            //nillfgardian empire ability
            if (Objects.equals(firstPlayer.getFaction().name, "nilfgaard") &&
                    !Objects.equals(secondPlayer.getFaction().name, "nilfgaard")) {
                reduceCrystal(1);
            } else if (!Objects.equals(firstPlayer.getFaction().name, "nilfgaard") &&
                    Objects.equals(secondPlayer.getFaction().name, "nilfgaard")) {
                reduceCrystal(0);
            }

        } else if (firstPlayerScore > secondPlayerScore) {
            reduceCrystal(1);
            if (Objects.equals(firstPlayer.getFaction().name, "realms")) {
                factionAction(0, Factions.NORTHERN_REALMS);
            }
            //monsters already handled in clear match table

        } else {
            reduceCrystal(0);
            if (Objects.equals(secondPlayer.getFaction().name, "realms")) {
                factionAction(1, Factions.NORTHERN_REALMS);
            }
            //monsters already handled in clear match table

        }
        clearMatchTable();
        //SKELLIGE ability
        if (round == 3) {
            if (Objects.equals(firstPlayer.getFaction().name, "skellige")) {
                factionAction(0, Factions.SKELLIGE);
            }
            if (Objects.equals(secondPlayer.getFaction().name, "skellige")) {
                factionAction(1, Factions.SKELLIGE);
            }
        }
    }

    public boolean isRoundFinished() {
        return secondPlayerPassed && firstPlayerPassed;
    }

    public boolean isMatchFinished() {
        return firstPlayerCrystals == 0 || secondPlayerCrystals == 0;
    }

    public void clearMatchTable() {
        int row = 0;
        //for monsterFaction;
        Card savedCard = null;
        boolean isMonster = false;
        if (Objects.equals(firstPlayer.getFaction().name, "monsters")) {


            if (!(getRowByID(0, 0).isEmpty() && getRowByID(0, 1).isEmpty() && getRowByID(0, 2).isEmpty())) {
                isMonster = true;
                row = Game.random.nextInt(0, 3);
                while (getRowByID(0, row).isEmpty()) row = Game.random.nextInt(0, 3);
                if (!getRowByID(0, row).isEmpty())
                    savedCard = getRowByID(0, row).get(Game.random.nextInt(0, getRowByID(0, row).size()));
            }
        }
        for (int i = 0; i < 3; i++) {
            Card replaceCard = null;
            Card deleteCard = null;
            for (Card card : getRowByID(0, i)) {
                if (card instanceof Hero hero) {
                    if (hero.getAbility() == Ability.TRANSFORMER) {
                        replaceCard = UnitCardInfo.getRegularCardByName("Zoltan Chivay");
                        deleteCard = card;
                        break;
                    }
                }
                if (card instanceof UnitCard unitCard) {
                    if (unitCard.getAbility() == Ability.TRANSFORMER) {
                        replaceCard = UnitCardInfo.getRegularCardByName("Zoltan Chivay");
                        deleteCard = card;
                        break;
                    }
                }

            }
            if (deleteCard != null) getRowByID(0, i).remove(deleteCard);
            Origin origin = switch (i) {
                case 0 -> Origin.FIRSTPLAYER_CLOSECOMBAT;
                case 1 -> Origin.FIRSTPLAYER_RANGED;
                case 2 -> Origin.FIRSTPLAYER_SIEGE;
                default -> null;
            };
            ArrayList<Card> d = new ArrayList<>(getRowByID(0, i));
            for (Card card : d) {
                if (isMonster && card == savedCard) {

                } else {
                    addToDeadCards(0, new CardWrapper(card, origin));
                }
            }
            if (replaceCard != null) getRowByID(0, i).add(replaceCard);
        }


        isMonster = false;
        if (Objects.equals(secondPlayer.getFaction().name, "monsters")) {
            if (!(getRowByID(1, 0).isEmpty() && getRowByID(1, 1).isEmpty() && getRowByID(1, 2).isEmpty())) {
                isMonster = true;
                row = Game.random.nextInt(0, 3);
                while (getRowByID(1, row).isEmpty()) row = Game.random.nextInt(0, 3);
                if (!getRowByID(1, row).isEmpty())
                    savedCard = getRowByID(1, row).get(Game.random.nextInt(0, getRowByID(1, row).size()));
            }
        }
        for (int i = 0; i < 3; i++) {
            Card replaceCard = null;
            Card deleteCard = null;
            for (Card card : getRowByID(1, i)) {
                if (card instanceof Hero hero) {
                    if (hero.getAbility() == Ability.TRANSFORMER) {
                        replaceCard = UnitCardInfo.getRegularCardByName("Zoltan Chivay");
                        deleteCard = card;
                        break;
                    }

                }
                if (card instanceof UnitCard unitCard) {
                    if (unitCard.getAbility() == Ability.TRANSFORMER) {
                        replaceCard = UnitCardInfo.getRegularCardByName("Zoltan Chivay");
                        deleteCard = card;
                        break;
                    }
                }
            }
            if (deleteCard != null) getRowByID(1, i).remove(deleteCard);
            Origin origin = switch (i) {
                case 0 -> Origin.SECONDPLAYER_CLOSECOMBAT;
                case 1 -> Origin.SECONDPLAYER_RANGED;
                case 2 -> Origin.SECONDPLAYER_SIEGE;
                default -> null;
            };
            ArrayList<Card> d = new ArrayList<>(getRowByID(1, i));
            for (Card card : d) {
                if (isMonster && card == savedCard) {

                } else {
                    addToDeadCards(0, new CardWrapper(card, origin));
                }

            }
            if (replaceCard != null) getRowByID(1, i).add(replaceCard);
        }
        if (isMonster) addToDeadCards(1, new CardWrapper(savedCard, Origin.SECONDPLAYER_DEAD));

        //boost cards:
        firstPlayerCloseCombatBoostCard = null;
        firstPlayerSiegeBoostCard = null;
        firstPlayerRangedBoostCard = null;
        secondPlayerCloseCombatBoostCard = null;
        secondPlayerSiegeBoostCard = null;
        secondPlayerRangedBoostCard = null;
        secondPlayerPassed = false;
        firstPlayerPassed = false;
        isFirstPlayerTurn = !isFirstPlayerTurn;
        clearSpellCards();

    }

    public void clearSpellCards() {
        spellCards.clear();
    }

    public void reduceCrystal(int playerID) {
        switch (playerID) {
            case 0:
                firstPlayerCrystals--;
                break;
            case 1:
                secondPlayerCrystals--;
                break;

        }
    }

    public void doMedic(CardWrapper cardWrapper) {
        if (isFirstPlayerTurn) {
            firstPlayerInPlayCards.add(cardWrapper.getCard());
        } else {
            secondPlayerInPlayCards.add(cardWrapper.getCard());
        }
        removeCard(cardWrapper);
    }

    public static int getRowPower(LeaderEffects leaderEffects,
                                  boolean weather,
                                  boolean boost,
                                  ArrayList<Card> row) {
        int[] nums = new int[row.size()];
        ArrayList<Card> tightBondCards = getCardsWithAbility(Ability.TIGHT_BOND, row);
        ArrayList<Card> moralBoostCards = getCardsWithAbility(Ability.MORALE_BOOST, row);
        for (int i = 0; i < nums.length; i++) {
            Card card = row.get(i);
            if (card instanceof UnitCard unitCard) {
                nums[i] = unitCard.getConstantPower();
            }
            if (card instanceof Hero hero) {
                nums[i] = hero.getConstantPower();
            }
        }
        if (weather) {
            if (leaderEffects.isKingBran()) {
                for (Card card : row) {
                    nums[row.indexOf(card)] = nums[row.indexOf(card)] / 2;
                }
            } else {
                Arrays.fill(nums, 1);
            }

        }
        if (leaderEffects.isSpyDoublePower()) {
            for (Card card : row) {
                if (card instanceof UnitCard unitCard) {
                    if (unitCard.getAbility() == Ability.SPY) {
                        nums[row.indexOf(card)] = nums[row.indexOf(card)] * 2;
                    }
                }
                if (card instanceof Hero hero) {
                    if (hero.getAbility() == Ability.SPY) {
                        nums[row.indexOf(card)] = nums[row.indexOf(card)] * 2;
                    }
                }
            }
        }
        for (Card card : row) {
            if (tightBondCards.contains(card)) {
                nums[row.indexOf(card)] = nums[row.indexOf(card)] * getNumberOfCards(card, tightBondCards);
            }
        }
        for (Card card : row) {
            if (moralBoostCards.contains(card)) {
                nums[row.indexOf(card)] = nums[row.indexOf(card)] + moralBoostCards.size() - 1;
            } else {
                nums[row.indexOf(card)] = nums[row.indexOf(card)] + moralBoostCards.size();
            }
        }
        if (boost) {
            for (Card card : row) {
                nums[row.indexOf(card)] = nums[row.indexOf(card)] * 2;
            }
        }
        int retVal = 0;
        for (int num : nums) {
            retVal += num;
        }
        return retVal;
    }

    public static int getRowPowerNoHero(LeaderEffects leaderEffects,
                                        boolean weather,
                                        boolean boost,
                                        ArrayList<Card> row) {
        System.out.println(row.size() + ":size");
        int[] nums = new int[row.size()];
        ArrayList<Card> tightBondCards = getCardsWithAbility(Ability.TIGHT_BOND, row);
        ArrayList<Card> moralBoostCards = getCardsWithAbility(Ability.MORALE_BOOST, row);
        System.out.println(Arrays.toString(nums));
        for (int i = 0; i < nums.length; i++) {
            Card card = row.get(i);
            if (card instanceof UnitCard unitCard) {
                nums[i] = unitCard.getConstantPower();
            }
            if (card instanceof Hero hero) {
                nums[i] = hero.getConstantPower();
            }
        }
        System.out.println(Arrays.toString(nums));
        if (weather) {
            if (leaderEffects.isKingBran()) {
                for (Card card : row) {
                    nums[row.indexOf(card)] = nums[row.indexOf(card)] / 2;
                }
            } else {
                Arrays.fill(nums, 1);
            }

        }
        System.out.println(Arrays.toString(nums));

        if (leaderEffects.isSpyDoublePower()) {
            for (Card card : row) {
                if (card instanceof UnitCard unitCard) {
                    if (unitCard.getAbility() == Ability.SPY) {
                        nums[row.indexOf(card)] = nums[row.indexOf(card)] * 2;
                    }
                }
                if (card instanceof Hero hero) {
                    if (hero.getAbility() == Ability.SPY) {
                        nums[row.indexOf(card)] = nums[row.indexOf(card)] * 2;
                    }
                }
            }
        }
        for (Card card : row) {
            if (tightBondCards.contains(card)) {
                nums[row.indexOf(card)] = nums[row.indexOf(card)] * getNumberOfCards(card, tightBondCards);
            }
        }
        System.out.println(Arrays.toString(nums));

        for (Card card : row) {
            if (moralBoostCards.contains(card)) {
                nums[row.indexOf(card)] = nums[row.indexOf(card)] + moralBoostCards.size() - 1;
            } else {
                nums[row.indexOf(card)] = nums[row.indexOf(card)] + moralBoostCards.size();
            }
        }
        System.out.println(Arrays.toString(nums));

        if (boost) {
            for (Card card : row) {
                nums[row.indexOf(card)] = nums[row.indexOf(card)] * 2;
            }
        }
        System.out.println(Arrays.toString(nums));

        int retVal = 0;
        for (int i = 0; i < nums.length; i++) {
            if (row.get(i) instanceof UnitCard) {
                retVal += nums[i];
            }
        }
        return retVal;
    }

    public void shufleDeck(int playerID) {
        switch (playerID) {
            case 0:
                Collections.shuffle(firstPlayerDeckCards);
                break;
            case 1:
                Collections.shuffle(secondPlayerDeckCards);

                break;
        }

    }

    //gives a row by player and row id
    public ArrayList<Card> getRowByID(int user_id, int rowID) {
        ArrayList<Card> row = null;
        row = switch (user_id) {
            case 0 -> switch (rowID) {
                case 0 -> firstPlayerCloseCombatRow;
                case 1 -> firstPlayerRangedRow;
                case 2 -> firstPlayerSiegeRow;
                default -> row;
            };
            case 1 -> switch (rowID) {
                case 0 -> secondPlayerCloseCombatRow;
                case 1 -> secondPlayerRangedRow;
                case 2 -> secondPlayerSiegeRow;
                default -> row;
            };
            default -> null;
        };
        return row;
    }

    // closeCombat:0 Range:1 Siege:2
    public boolean isRowUnderWeather(int rowID) {
        for (Card card : spellCards) {
            switch (card.getName()) {
                case "Biting Frost":
                    if (rowID == 0) return true;
                    break;
                case "Impenetrable fog":
                    if (rowID == 1) return true;
                    break;
                case "Torrential Rain":
                    if (rowID == 2) return true;
                    break;
                case "SKELLIGE Storm":
                    if (rowID == 2 || rowID == 1) return true;
                    break;
            }
        }
        return false;
    }

    //checks for commander horn in boost card place and
    // for specials cards in the given row that give the commanders horn effect
    public boolean isRowUnderBoost(int user_id, int rowID) {
        ArrayList<Card> row = getRowByID(user_id, rowID);
        boolean x;
        switch (user_id) {
            case 0:
                x = isThereCommanderHorn(user_id, rowID);
                if (x) return true;
                break;
            case 1:
                x = isThereCommanderHorn(user_id, rowID);
                if (x) return true;
                break;
        }
        for (Card card : row) {
            if (card != null) {
                if (Objects.equals(card.getName(), "Draig Bon-Dhu") ||
                        Objects.equals(card.getName(), "Dandelion")) {
                    return true;
                }
            }
        }

        return false;
    }

    public void initilizeTable() {
        ArrayList<Card> firstPlayerCards;
        ArrayList<Card> secondPlayerCards;
        firstPlayerLeader = firstPlayer.getLeader();
        secondPlayerLeader = secondPlayer.getLeader();

        if (Objects.equals(firstPlayer.getFaction().name, "scoiatael") && !Objects.equals(secondPlayer.getFaction().name, "scoiatael")) {
            isFirstPlayerTurn = true;
        } else if (!Objects.equals(firstPlayer.getFaction().name, "scoiatael") && Objects.equals(secondPlayer.getFaction().name, "scoiatael")) {
            isFirstPlayerTurn = false;
        } else {
            isFirstPlayerTurn = Game.random.nextBoolean();
        }
        if (Objects.equals(firstPlayerLeader, new Leader(LeaderInfo.DAISY_OF_THE_VALLEY))) {
            firstPlayerCards = randomSelectedCards(firstPlayerDeckCards, 11);
        } else {
            firstPlayerCards = randomSelectedCards(firstPlayerDeckCards, 10);
        }
        if (Objects.equals(secondPlayerLeader, new Leader(LeaderInfo.DAISY_OF_THE_VALLEY))) {
            secondPlayerCards = randomSelectedCards(secondPlayerDeckCards, 11);
        } else {
            secondPlayerCards = randomSelectedCards(secondPlayerDeckCards, 10);
        }
        for (Card card : firstPlayerCards) {
            addToInPlayCards(0, new CardWrapper(card, Origin.FIRSTPLAYER_DECK));
        }
        for (Card card : secondPlayerCards) {
            addToInPlayCards(1, new CardWrapper(card, Origin.SECONDPLAYER_DECK));
        }
    }

    //-----------------------------------------------------private Functions------------------------------------------//


    //checks for commander horn in the given boost card place
    private boolean isThereCommanderHorn(int userID, int rowID) {
        switch (userID) {
            case 0:
                switch (rowID) {
                    case 0:
                        if (firstPlayerCloseCombatBoostCard != null) return true;
                        break;
                    case 1:
                        if (firstPlayerRangedBoostCard != null) return true;
                        break;
                    case 2:
                        if (firstPlayerSiegeBoostCard != null) return true;

                        break;
                }
                break;
            case 1:
                switch (rowID) {
                    case 0:
                        if (secondPlayerCloseCombatBoostCard != null) return true;
                        break;
                    case 1:
                        if (secondPlayerRangedBoostCard != null) return true;
                        break;
                    case 2:
                        if (secondPlayerSiegeBoostCard != null) return true;
                        break;
                }
                break;

        }
        return false;
    }


    private static int getNumberOfCards(Card card, ArrayList<Card> cards) {
        int num = 0;
        for (Card card1 : cards) {
            if (card1.getName().equals(card.getName())) num++;
        }
        return num;
    }

    private static ArrayList<Card> getCardsWithAbility(Ability ability, ArrayList<Card> cards) {
        ArrayList<Card> retVal = new ArrayList<>();
        for (Card card : cards) {
            if (card instanceof UnitCard unitCard) {
                if (unitCard.getAbility() == ability) retVal.add(card);
            }
            if (card instanceof Hero hero) {
                if (hero.getAbility() == ability) retVal.add(card);
            }
        }
        return retVal;
    }

    private void applyMardroeme(int userID, int rowID) {
        ArrayList<Card> row = getRowByID(userID, rowID);
        int berserkerNum = 0;
        ArrayList<Card> toRemove = new ArrayList<>();
        for (Card card : row) {
            if (card instanceof UnitCard unitCard) {
                if (unitCard.getAbility() == Ability.BERSERKER) {
                    berserkerNum++;
                    toRemove.add(unitCard);
                }
            }
            if (card instanceof Hero hero) {
                if (hero.getAbility() == Ability.BERSERKER) {
                    berserkerNum++;
                    toRemove.add(hero);
                }
            }

        }
        row.removeAll(toRemove);
        UnitCard unitCard = new UnitCard(UnitCardInfo.VIDKAARL);
        for (int i = 0; i < berserkerNum; i++) {
            row.add(unitCard);
        }
    }

    private void initializeMatchTable() {
        boolean firstScotail;
        boolean secondScotail;
        firstScotail = Objects.equals(firstPlayer.getFaction().name, "scoiatael");
        secondScotail = Objects.equals(secondPlayer.getFaction().name, "scoiatael");

        if (firstScotail && !secondScotail) {
            isFirstPlayerTurn = true;
        } else if (!firstScotail && secondScotail) {
            isFirstPlayerTurn = false;
        } else {
            isFirstPlayerTurn = Game.random.nextBoolean();
        }
        //todo

    }


}