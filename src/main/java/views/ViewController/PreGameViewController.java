package views.ViewController;

import enums.cards.UnitCardInfo;
import javafx.scene.Node;
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

import java.util.Comparator;
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
    public FlowPane selectCardFlowPane;
    public FlowPane selectedCardFlowPane;

    public void initialize() {
        selectCardFlowPane.setHgap(10);
        selectCardFlowPane.setVgap(10);
        selectedCardFlowPane.setHgap(10);
        selectedCardFlowPane.setVgap(10);
        for (Card card : Game.getAllCards()) {
            CreateNewCard(card, false);
        }

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

    private void addToSelectedCards(Card card) {
        card.setWidth(113);
        card.setHeight(169.5);
        Pane pane = new Pane();
        pane.getChildren().add(card);
        pane.setOnMouseClicked(e -> {
            User user = Game.getLoggedInUser();
            user.removeCardFromDeck(card);
            card.addToSelected();
            selectedCardFlowPane.getChildren().remove(pane);
            addToSelectCards(card);
        });
        selectedCardFlowPane.getChildren().add(pane);
    }

    private void addToSelectCards(Card card) {
        for (Node pane1 : selectCardFlowPane.getChildren()) {
            pane1 = (Pane) pane1;
            Card card1 = (Card) ((Pane) pane1).getChildren().get(0);
            if (card1.getName().equals(card.getName())) {
                card1.removeFromSelected();
                Label label = (Label) ((HBox) ((Pane) pane1).getChildren().get(1)).getChildren().get(1);
                label.setText(String.valueOf(Integer.parseInt(label.getText()) + 1));
                return;
            }
        }
        Card newCard = Card.getCardByName(card.getName());
        assert newCard != null;
        CreateNewCard(newCard, true);
    }

    private void CreateNewCard(Card newCard, boolean isCardSelected) {
        Pane pane = new Pane();
        HBox hBox = new HBox();
        newCard.setWidth(113);
        newCard.setHeight(169.5);
        pane.getChildren().add(newCard);
        ImageView imageView = new ImageView(new Image(Objects.requireNonNull(GameView.class.getResource("/Assets/Cards/counter.png")).toExternalForm()));
        imageView.setFitWidth(16);
        imageView.setFitHeight(12);
        hBox.getChildren().add(imageView);
        Label label = new Label(Integer.toString(newCard.getMaxCapacity() - newCard.getSelectedCards()));
        if (isCardSelected) {
            newCard.setSelectedCards(newCard.getMaxCapacity() - 1);
            label.setText(Integer.toString(newCard.getMaxCapacity() - newCard.getSelectedCards()));
        }
        label.setLayoutX(16);
        label.setLayoutY(12);
        hBox.getChildren().add(label);
        pane.getChildren().add(hBox);
        hBox.setLayoutX(newCard.getLayoutX() + newCard.getWidth() - 30);
        hBox.setLayoutY(newCard.getLayoutY() + newCard.getHeight() - 32);
        pane.setOnMouseClicked(e -> {
            User user = Game.getLoggedInUser();
            user.getDeckCards().add(Card.getCardByName(newCard.getName()));
            newCard.addToSelected();
            addToSelectedCards(Objects.requireNonNull(Card.getCardByName(newCard.getName())));
            Game.addToSelectedCards(newCard);
            label.setText(Integer.toString(newCard.getMaxCapacity() - newCard.getSelectedCards()));
            if (newCard.getMaxCapacity() == newCard.getSelectedCards()) {
                selectCardFlowPane.getChildren().remove(pane);
            }
        });
        selectCardFlowPane.getChildren().add(pane);
    }
}
