package Server.Services;

import models.Relation;

import java.util.ArrayList;

public class EliminationCup {
    private static EliminationCup instance;
    private final ArrayList<String> participates = new ArrayList<>();
    private final ArrayList<Relation> hasGame1 = new ArrayList<>();
    private final ArrayList<Relation> hasGame2 = new ArrayList<>();
    private final ArrayList<Relation> hasGame3 = new ArrayList<>();
    private int row;

    private EliminationCup() {
    }

    public static EliminationCup getInstance() {
        if (instance == null) {
            instance = new EliminationCup();
        }
        return instance;
    }

    public ArrayList<Relation> getRow() {
        return switch (row) {
            case 1 -> hasGame1;
            case 2 -> hasGame2;
            case 3 -> hasGame3;
            default -> null;
        };
    }

    public void createRelation(String left, String right, int number) {
        Relation rel = new Relation(left, right);
        rel.setNumber(number);
        getRow().add(rel);
    }


    public void initialSetup() {
        createRelation(participates.get(0), participates.get(1), 1);
        createRelation(participates.get(2), participates.get(3), 2);
        createRelation(participates.get(4), participates.get(5), 3);
        createRelation(participates.get(6), participates.get(7), 4);
    }

    public void addParticipate(String username) {
        participates.add(username);
    }


    public void winnerOfGame(String left) {
        ArrayList<Relation> list = getRow();
        for (Relation relation : list) {
            if (relation.getFirst().equals(left) || relation.getSecond().equals(left)){

            }
        }
    }

    private Relation getRelationByNumber(int number){

    }

}
