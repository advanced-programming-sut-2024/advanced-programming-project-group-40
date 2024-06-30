package views.ViewController;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class PreGameViewController {
    @FXML
    private Pane changeFactionPane;
    public Pane mainPane;
    public VBox box3;
    public VBox box2;
    public VBox box1;

    public void initialize(){
//        mainPane.setDisable(true);

    }

    public void ChangeFaction(MouseEvent mouseEvent) {
        mainPane.setDisable(true);
        changeFactionPane.setVisible(true);

    }

    @FXML
    private void handleBoxClick(MouseEvent event) {
        VBox selectedBox = (VBox) event.getSource();
        updateBoxSizes(selectedBox);
    }

    private void updateBoxSizes(VBox selectedBox) {
        VBox[] boxes = {box1, box2, box3}; // Add more VBox elements as needed
        for (VBox box : boxes) {
            if (box == selectedBox) {
                box.getStyleClass().add("selected");
                box.getStyleClass().remove("smaller");
            } else {
                box.getStyleClass().add("smaller");
                box.getStyleClass().remove("selected");
            }
        }
    }
}
