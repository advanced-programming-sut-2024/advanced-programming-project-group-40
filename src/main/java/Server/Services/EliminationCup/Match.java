package Server.Services.EliminationCup;

public class Match {
    private String player1;
    private String player2;
    private String winner;
    private int groupNumber;
    private int round;



    public Match(String player1, String player2,  int groupNumber, int round) {
        this.player1 = player1;
        this.player2 = player2;
        this.groupNumber = groupNumber;
        this.round = round;
        winner="";
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public int getRound() {
        return round;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }
}
