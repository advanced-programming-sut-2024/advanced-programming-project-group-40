package controllers;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EliminationCupControllerTest {

    @Test
    void checkTeams() {
        ArrayList<String> teams = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            teams.add("team" + i);
        }
        assertTrue(EliminationCupController.checkTeams(teams));
    }
}