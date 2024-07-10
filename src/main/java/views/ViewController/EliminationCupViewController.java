package views.ViewController;

import Server.Messages.MessageSubType;
import controllers.Utilities;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import models.Game;

public class EliminationCupViewController {
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
//        if ()
    }

    public void goToLive(MouseEvent mouseEvent) {

    }

    public void joinORStart(MouseEvent mouseEvent) {
        Utilities.sendRequest(Game.getLoggedInUser().getUsername(), "", MessageSubType.JOIN_ELIMINATION);
    }
}
