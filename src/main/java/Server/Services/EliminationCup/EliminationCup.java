package Server.Services.EliminationCup;

import java.util.ArrayList;

public class EliminationCup {
    private static EliminationCup instance;
    private ArrayList<String> players = new ArrayList<String>();
    private ArrayList<Match> matches = new ArrayList<Match>();
    private int round;

    private EliminationCup() {
    }

    public static EliminationCup getInstance() {
        if (instance == null) {
            instance = new EliminationCup();
        }
        return instance;
    }

    public void initialize() {
        round = 1;
        for (int i = 0, g = 1; i < 8; i += 2, g++) {
            Match match = new Match(players.get(i), players.get(i + 1), g, round);
            matches.add(match);
        }
    }

    public void addPlayer(String username) {
        players.add(username);
    }

    public void finishGame(String winner) {
        Match currentMatch = getMatchByPlayer(winner);
        currentMatch.setWinner(winner);

        // check if already match with that number has created
        Match nextMatch = null;
        int groupNumber = getNextRoundGroupNumberByCurrentNumber(currentMatch.getGroupNumber());
        for (Match match : matches) {
            if (match.getRound() == round + 1) {
                if (match.getGroupNumber() == groupNumber) {
                    nextMatch = match;
                }
            }
        }
        if (nextMatch == null) {
            nextMatch = new Match(winner, "", groupNumber, round + 1);
            matches.add(nextMatch);
        } else {
            if (nextMatch.getPlayer2().isEmpty()) {
                nextMatch.setPlayer2(winner);
            } else {
                System.out.println("finish game in elimination: player 2 is full");
            }
        }
        nextRound();
    }


    private Match getMatchByPlayer(String player) {
        for (Match match : matches) {
            if (match.getRound() == round) {
                if (match.getPlayer1().equals(player) || match.getPlayer2().equals(player)) {
                    return match;
                }
            }
        }
        return null;
    }

    public void nextRound() {
        for (Match match : matches) {
            if (match.getWinner().isEmpty())
                return;
        }
        round++;
    }


    private int getNextRoundGroupNumberByCurrentNumber(int currentNumber) {
        switch (currentNumber) {
            case 1, 2:
                return 5;
            case 3, 4:
                return 6;
            case 5, 6:
                return 7;
        }
        return -1;
    }

    public String getCompetitor(String defender) {
        Match match = getMatchByPlayer(defender);
        if (match.getPlayer1().equals(defender))
            return match.getPlayer2();
        else
            return match.getPlayer1();
    }

    public boolean isEliminationStarted() {
        return players.size() == 8;
    }


    public Match getMatchByGroupNumber(int groupNumber) {
        for (Match match : matches) {
            if (match.getGroupNumber() == groupNumber)
                return match;
        }
        return null;
    }
}
