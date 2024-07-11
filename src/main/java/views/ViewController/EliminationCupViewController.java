package views.ViewController;

import Server.Messages.MessageSubType;
import Server.Services.EliminationCup.Match;
import controllers.EliminationCupController;
import controllers.Utilities;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import models.Game;

public class EliminationCupViewController {
    public Button join;
    public Button start;
    @FXML
    private Label CL;
    @FXML
    private Label CR;
    @FXML
    private Label DR;
    @FXML
    private Label BR;
    @FXML
    private Label FL;
    @FXML
    private Label EL;
    @FXML
    private Label HL;
    @FXML
    private Label GL;
    @FXML
    private Label HR;
    @FXML
    private Label GR;
    @FXML
    private Label FR;
    @FXML
    private Label ER;
    @FXML
    private Label A;
    @FXML
    private Label DL;

//    private ArrayList<>

    public void initialize() {
        start.setDisable(true);
        if (EliminationCupController.isStarted()) {
            setText(EL, FL, EliminationCupController.getMatch(1));
            setText(GL, HL, EliminationCupController.getMatch(2));
            setText(ER, FR, EliminationCupController.getMatch(3));
            setText(GR, HR, EliminationCupController.getMatch(4));
            setText(CL, DL, EliminationCupController.getMatch(5));
            setText(CR, DR, EliminationCupController.getMatch(6));

            join.setDisable(true);
            start.setDisable(false);
        }
    }

    private void setText(Label label1, Label label2, Match match) {
        label1.setText(match.getPlayer1());
        label2.setText(match.getPlayer2());
    }

    public void goToLive(MouseEvent mouseEvent) {

    }

    public void join(MouseEvent mouseEvent) {
        EliminationCupController.sendEliminationRequest(MessageSubType.JOIN_ELIMINATION);
        EliminationCupController.sendEliminationRequest(MessageSubType.PREGAME_UPDATE);
    }

    public void start(MouseEvent mouseEvent) {
        Utilities.sendRequest(Game.getLoggedInUser().getUsername(), EliminationCupController.getCompetitorByDefenderName(), MessageSubType.GAME_REQUEST);
    }
}
