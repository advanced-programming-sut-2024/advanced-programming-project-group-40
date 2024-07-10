package Server.Services;

import models.Relation;

import java.util.ArrayList;

public class EliminationCup {
    private static EliminationCup instance;
    private final ArrayList<String> participates = new ArrayList<>();
    private final ArrayList<Relation> hasGame1 = new ArrayList<>();
    private final ArrayList<Relation> hasGame2 = new ArrayList<>();
    private final ArrayList<Relation> hasGame3 = new ArrayList<>();

    private EliminationCup() {
    }

    public static EliminationCup getInstance() {
        if (instance == null) {
            instance = new EliminationCup();
        }
        return instance;
    }


    public ArrayList<String> getLeftUsernames(int row) {
        ArrayList<Relation> lis = getList(row);
        ArrayList<String> output = new ArrayList<>();
        for (Relation relation : lis) {
            output.add(relation.getFirst());
        }
        return output;
    }

    public ArrayList<String> getRightUsernames(int row) {
        ArrayList<Relation> lis = getList(row);
        ArrayList<String> output = new ArrayList<>();
        for (Relation relation : lis) {
            output.add(relation.getSecond());
        }
        return output;
    }

    private ArrayList<Relation> getList(int row) {
        return switch (row) {
            case 1 -> hasGame1;
            case 2 -> hasGame2;
            case 3 -> hasGame3;
            default -> null;
        };
    }


    public void addParticipate(String username) {
        participates.add(username);
    }

}
