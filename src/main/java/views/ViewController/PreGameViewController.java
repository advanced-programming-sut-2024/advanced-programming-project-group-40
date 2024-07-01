package views.ViewController;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import controllers.MenuController.PreGameMenuController;
import enums.Factions;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import views.PreGameMenu;

public class PreGameViewController {
    public ImageView image2;
    public ImageView image3;
    public ImageView image1;
    public ImageView image4;
    public ImageView image5;
    public Label description;
    @FXML
    private Pane changeFactionPane;
    public Pane mainPane;
    public VBox box3;
    public VBox box2;
    public VBox box1;
    public FlowPane selectCardFlowPane;
    private ArrayList<ImageView> images = new ArrayList<ImageView>();
    private int[] tmp = new int[]{0, 1, 2, 3, 4};
    private HashMap<String, ImageView> factions = new HashMap<String, ImageView>();
    private ArrayList<String> factionNames = Game.getAllFactions();

    public void initialize() {
        changeFactionPane.setVisible(false);

        images.add(image1);
        images.add(image2);
        images.add(image3);
        images.add(image4);
        images.add(image5);

        for (String cardName : factionNames) {
            factions.put(cardName, new ImageView(new Image(Objects.requireNonNull(GameView.class.getResource("/Assets/Factions/faction_" + cardName + ".jpg")).toExternalForm())));
        }

        int counter = -1;
        for (ImageView imageFaction : images) {
            counter++;
            if (imageFaction.equals(image3)) {
                imageFaction.setImage(factions.get(Game.getLoggedInUser().getFaction()).getImage());
                description.setText(Game.getLoggedInUser().getFaction());
            } else if (!factionNames.equals(Game.getLoggedInUser().getFaction())) {
                imageFaction.setImage(factions.get(factionNames.get(counter)).getImage());
            }
        }

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

    public void ChangeFaction(MouseEvent mouseEvent) {
//        mainPane.setDisable(true);
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

    public void changeFaction() {

    }

    public void moveRight(MouseEvent mouseEvent) {
        int cnt = tmp[4];
        for (int i = 4; i > 0; i--) {
            tmp[i] = tmp[i - 1];
        }
        tmp[0] = cnt;

        int counter = -1;
        for (ImageView imageFaction : images) {
            counter++;
            imageFaction.setImage(factions.get(factionNames.get(tmp[counter])).getImage());
        }

        description.setText(factionNames.get(tmp[2]));
    }

    public void moveLeft(MouseEvent mouseEvent) {
        int cnt = tmp[0];
        for (int i = 0; i < 4; i++) {
            tmp[i] = tmp[i + 1];
        }
        tmp[4] = cnt;


        int counter = -1;
        for (ImageView imageFaction : images) {
            counter++;
            imageFaction.setImage(factions.get(factionNames.get(tmp[counter])).getImage());
        }

        description.setText(factionNames.get(tmp[2]));
    }

    public void closeFactionPane(MouseEvent mouseEvent) {
        changeFactionPane.setVisible(false);
//        mainPane.setDisable(false);
    }
}
