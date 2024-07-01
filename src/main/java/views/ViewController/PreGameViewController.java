package views.ViewController;

import enums.cards.UnitCardInfo;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import models.Game;
import models.User;
import models.cards.Card;
import models.cards.UnitCard;
import views.GameView;

import java.util.Objects;

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

    public FlowPane selectCardFlowPane;

    public void initialize() {
        selectCardFlowPane.setHgap(10);
        for (Card card : Game.getAllCards()) {
            Pane pane = new Pane();
            HBox hBox = new HBox();
            card.setWidth(113);
            card.setHeight(169.5);
            pane.getChildren().add(card);
            ImageView imageView = new ImageView(new Image(Objects.requireNonNull(GameView.class.getResource("/Assets/Cards/counter.png")).toExternalForm()));
            imageView.setFitWidth(16);
            imageView.setFitHeight(12);
            hBox.getChildren().add(imageView);
            Label label = new Label(Integer.toString(card.getMaxCapacity() - card.getSelectedCards()));
            label.setLayoutX(16);
            label.setLayoutY(12);
            hBox.getChildren().add(label);
            pane.getChildren().add(hBox);
            hBox.setLayoutX(card.getLayoutX() + card.getWidth() - 30);
            hBox.setLayoutY(card.getLayoutY() + card.getHeight() - 32);
            pane.setOnMouseClicked(e -> {
                User user = Game.getLoggedInUser();
                user.getDeckCards().add(Card.getCardByName(card.getName()));
                card.addToSelected();
                Game.addToSelectedCards(card);
                label.setText(Integer.toString(card.getMaxCapacity() - card.getSelectedCards()));
                if (card.getMaxCapacity() == card.getSelectedCards()) {
                    selectCardFlowPane.getChildren().remove(pane);
                }
            });
            selectCardFlowPane.getChildren().add(pane);
        }
    }
}
