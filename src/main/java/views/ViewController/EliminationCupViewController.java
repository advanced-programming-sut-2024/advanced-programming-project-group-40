package views.ViewController;

import Server.Messages.MessageSubType;
import controllers.MenuController.EliminationCupController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

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

    public void join(MouseEvent mouseEvent) {
        EliminationCupController.sendEliminationRequest(MessageSubType.JOIN_ELIMINATION);
    }

    public void start(MouseEvent mouseEvent) {
        EliminationCupController.sendEliminationRequest(MessageSubType.SEND_GAME_REQUEST);
    }
}
